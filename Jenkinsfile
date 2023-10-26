pipeline {

    agent any

    stages {

        stage('Checkout Backend Repo') {
            steps {
              git branch: 'main',
              url: 'https://github.com/HaithemGhattass/DevOpsBackend.git'
            }
        }
        stage('build') {
            steps {
                sh 'mvn clean compile'
            }
        }
    }
 }
