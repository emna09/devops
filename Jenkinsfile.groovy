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

        stage('Build Backend') {
            steps {
                script {
                    // Ajoutez ici les commandes de construction du backend
                    sh './mvnw clean install'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                script {
                    // Ajoutez ici les commandes de construction du frontend
                    sh 'cd angular-16-client && npm install && ng build'
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    // Ajoutez ici les commandes de tests
                    sh './mvnw test'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Ajoutez ici les commandes pour construire l'image Docker
                    sh 'docker build -t backend-image .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Ajoutez ici les commandes pour pousser l'image Docker sur Docker Hub
                    sh 'docker push backend-image'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Ajoutez ici les commandes pour déployer l'application
                    sh 'docker-compose up -d'
                }
            }
        }
    }

    post {
        always {
            // Ajoutez ici les étapes qui doivent être exécutées après chaque build
        }
    }
}
