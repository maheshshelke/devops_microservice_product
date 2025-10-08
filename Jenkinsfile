pipeline {
    agent docker {
        // Image name and tag
        image 'maven:3.9.11-jdk-21'
        // Always pull the image to ensure it's the latest version.
        alwaysPull true
        // Set up a Docker volume for Maven local repository.
        args '-v /tmp/.m2/repository:/root/.m2/repository'
    }

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3911"
    }

    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'develop', url: 'https://github.com/maheshshelke/devops_microservice_product.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
