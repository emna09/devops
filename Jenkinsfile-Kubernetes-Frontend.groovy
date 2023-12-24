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
        stage('Pull') {
            steps {
                // Ajoutez ici les commandes pour le pull (si nécessaire)
            }
        }

        stage('Build') {
            steps {
                // Ajoutez ici les commandes pour la construction
            }
        }

        stage('Deploy Kubernetes Frontend') {
            steps {
                script {
                    // Ajoutez ici les commandes pour déployer sur Kubernetes (apply.deployment)
                    sh 'kubectl apply -f frontend-deployment.yaml'
                }
            }
        }

        stage('Notify Slack') {
            steps {
                // Ajoutez ici les commandes pour la notification Slack
                sh 'slack-notifier-command'
            }
        }
    }

    post {
        always {
            // Ajoutez ici les étapes qui doivent être exécutées après chaque build
        }
    }
}
