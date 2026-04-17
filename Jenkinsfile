pipeline {
    agent any

    stages {
        stage('1. Environment Setup') {
            steps {
                // Deletes the workspace to ensure a clean start
                deleteDir()
                // Forced clone from your specific repository
                bat 'git clone https://github.com .'
            }
        }

        stage('2. Build & Unit Tests') {
            steps {
                dir('oms-core') {
                    // Runs the Java compilation and the 3 JUnit tests
                    bat 'mvn clean package'
                }
            }
        }

        stage('3. Containerize (Docker)') {
            steps {
                dir('oms-core') {
                    // Builds the Docker image locally
                    bat 'docker build -t raashidcodes/oms-core:latest .'
                }
            }
        }

        stage('4. Orchestrate (Kubernetes)') {
            steps {
                dir('oms-core') {
                    // Deploys the 2 replicas to your local K8s cluster
                    bat 'kubectl apply -f deployment.yaml'
                }
            }
        }

        stage('5. Final Verification') {
            steps {
                // Displays the running pods for the professor
                bat 'kubectl get pods'
                bat 'kubectl get svc oms-service'
            }
        }
    }
}
