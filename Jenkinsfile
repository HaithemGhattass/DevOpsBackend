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
        node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Default Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=devOps -Dsonar.projectName='devOps'"
    }
  }
}
    }
 }
