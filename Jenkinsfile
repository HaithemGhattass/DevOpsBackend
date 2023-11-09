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
                                    stage('Build Frontend') {
                                        steps {
                                            // Checkout the Angular frontend repository
                                            git branch: 'main',
                                            url: 'https://github.com/HaithemGhattass/DevOpsFrontend.git'
                                            sh 'npm install -g @angular/cli'
                                            sh 'npm install'
                                            sh 'ng build --configuration=production'
                                        }
                                    }

    }
 }