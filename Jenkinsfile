pipeline {

    agent any

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

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv() {
      sh "mvn clean verify sonar:sonar -Dsonar.projectKey=devops -Dsonar.projectName='devops'"
                }
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
    }
 }