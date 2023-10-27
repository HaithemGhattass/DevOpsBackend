pipeline {

    agent any
    
    options {
        durabilityHint 'MAX_SURVIVABILITY'
    }
    

    stages {

        stage('Checkout Backend Repo') {
            steps {
              git branch: 'main',
              url: 'https://github.com/aminemtar/DEVOPS.git'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }
                stage('Unit Tests') {
            steps {
                script {

                    sh 'mvn test'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv(installationName:'sql') {
                sh 'chmod +x ./mvnw'
                    sh 'mvn compile sonar:sonar'
                }
            }
        }
    }
 }