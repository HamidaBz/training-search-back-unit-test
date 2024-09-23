pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9' // Nom de l'installation Maven dans Jenkins
        jdk 'JDK 21'        // Nom de l'installation JDK dans Jenkins
    }

    //environment {
        // Définir les variables d'environnement si nécessaire
    //}

    stages {
        stage('Checkout') {
            steps {
                // Cloner le code depuis GitHub
                git branch: 'dev', url: 'https://github.com/HamidaBz/training-search-back-unit-test.git', credentialsId: 'github-credentials'
            }
        }
        stage('Build'){
            steps{
                bat 'mvn clean package'
            }
        }
        stage('Test'){
            steps{
                bat 'mvn test'
            }
            post{
                always{
                    // Archiver les rapports de test
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Archive Artifacts') {
            steps {
                // Archiver les artefacts construits
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

}
