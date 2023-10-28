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
                 post {
                             success {
                                 jacoco(
                                     execPattern: '**/target/*.exec',
                                 )
                             }
                         }
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        //  stage('SonarQube Analysis') {
        //      steps {
        //          withSonarQubeEnv(installationName:'sonarqube') {
        //          sh 'chmod +x ./mvnw'
        //               sh 'mvn compile sonar:sonar'
        //          }
        //      }
        //  }
    }
 }
