pipeline {
    agent any

    stages {
        
        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    sh './mvnw clean install'
                    sh 'docker build -t backend-image .'
                    sh 'docker push backend-image'
                }
            }
        }
        stage('Deploy to Docker Compose') {
            steps {
                script {
                    sh 'docker-compose up -d'
                }
            }
        }

        stage('Notification on Slack') {
            steps {
                script {
                    // Ajoutez ici les commandes pour la notification Slack
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Ajoutez ici les commandes pour l'analyse SonarQube
                    sh 'sonar-scanner -Dsonar.host.url=http://sonarqube:9000'
                }
            }
        }

        stage('Publish to Nexus') {
            steps {
                script {
                    // Ajoutez ici les commandes pour publier dans Nexus
                    sh 'mvn deploy -Dnexus.url=http://nexus:8081/repository/maven-releases/'
                }
            }
        }

        // ... (stages suivants)
    }

    post {
        always {
            echo "This is always executed"
            // Ajoutez ici les étapes qui doivent être exécutées après chaque build
        }
    }
}
