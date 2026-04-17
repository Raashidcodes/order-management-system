pipeline {
    agent any

    stages {
        stage('1. Environment Setup') {
            steps {
                // This clears the workspace and clones fresh from the correct URL
                deleteDir()
                bat 'git clone https://github.com .'
            }
        }

        stage('2. Build & JUnit Tests') {
            steps {
                dir('oms-core') {
                    // Compiles and runs the 3 tests (Success, Out of Stock, Payment Fail)
                    bat 'mvn clean package'
                }
            }
        }

        stage('3. Containerize (Docker)') {
            steps {
                dir('oms-core') {
                    // Builds the image for the local registry
                    bat 'docker build -t raashidcodes/oms-core:latest .'
                }
            }
        }

        stage('4. Orchestrate (Kubernetes)') {
            steps {
                dir('oms-core') {
                    // Deploys the 2 replicas to K8s
                    bat 'kubectl apply -f deployment.yaml'
                }
            }
        }

        stage('5. Final Proof') {
            steps {
                // The logs your professor needs to see
                bat 'kubectl get pods'
                bat 'kubectl get svc oms-service'
            }
        }
    }
}
