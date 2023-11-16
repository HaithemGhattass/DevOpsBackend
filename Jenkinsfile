pipeline {

    agent any
tools { nodejs '19.9.0'}

    stages {

        stage('Checkout Backend Repo') {
            steps {
              git branch: 'main',
              url: 'https://github.com/HaithemGhattass/DevOpsBackend.git'
            }
        }
        stage('Unit Tests') {
            steps {
                script {
                    sh 'mvn test' 
                }
            }
            post{
                always{
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('build') {
            steps {
                sh 'mvn package'
            }
            post {
                success {
                    emailext subject: 'Jenkins build Success',
                              body: 'The Jenkins build  has succeeded. Build URL: ${BUILD_URL}',
                              to: '$DEFAULT_RECIPIENTS'
                        }

                failure {
                    emailext subject: 'Jenkins build Failure',
                              body: 'The Jenkins build has failed. Build URL: ${BUILD_URL}',
                              to: '$DEFAULT_RECIPIENTS'
                }
                }
        }


        stage('JUNit Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                echo "Publishing JUnit reports"
            }
        }
        stage('Jacoco Reports') {
            steps {
                  echo "Publishing Jacoco Code Coverage Reports";
            }
             post {
                 success {
                     jacoco(
                         execPattern: '**/target/*.exec',
                     )
                 }
             }
        }

        stage('SonarQube Analysis') {
            steps {
                 withSonarQubeEnv(installationName:'sonarqube') {
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=devops -Dsonar.projectName="devops"'
                    }
                }
        }
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t haithem2301/spring-boot-docker .'
                }
            }
        }

        stage('Push beckend image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                    sh 'docker login -u haithem2301 -p ${dockerhubpwd}'

                    }
                   sh 'docker push haithem2301/spring-boot-docker'
                }
            }
        }

        stage('Build Frontend and push to hub') {
        agent any
            steps {
                // Checkout the Angular frontend repository
                git branch: 'main',
                url: 'https://github.com/HaithemGhattass/DevOpsFrontend.git'
                sh 'npm install -g @angular/cli'
                sh 'npm install'
                sh 'ng build --configuration=production'
                sh 'docker build -t haithem2301/angular-app -f Dockerfile .'
                withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u haithem2301 -p ${dockerhubpwd}'
                sh 'docker push haithem2301/angular-app'
                    }
                }
            }


           stage('Deploy fullstack app'){
             steps{
                 script{
                     sh 'docker compose -f docker-compose-app.yaml up --build -d'
                        }
                 }
            }
           stage('Deploy Prometheus & Graphana'){
               steps{
                   script{
                       sh 'docker compose  -f docker-compose-prometheus.yaml -f docker-compose-grafana.yaml up --build -d'
                           }
                           }
                        }
      }



    }
        post {
            success {
                emailext subject: 'Jenkins Pipeline Success',
                          body: 'The Jenkins pipeline has succeeded. Build URL: ${BUILD_URL}',
                          to: '$DEFAULT_RECIPIENTS'
            }

            failure {
                emailext subject: 'Jenkins Pipeline Failure',
                          body: 'The Jenkins pipeline has failed. Build URL: ${BUILD_URL}',
                          to: '$DEFAULT_RECIPIENTS'
            }
        }
 }