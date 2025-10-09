pipeline {
  agent {
    docker {
      image 'maven:3.9.9-eclipse-temurin-21'
      // Pull latest tag on every run so you always start from a fresh image
      alwaysPull true
      // Do NOT mount host folders; keep everything ephemeral inside the container
      args ''
      // Don’t reuse the node’s workspace for container state
      reuseNode false
    }
  }

  options { skipDefaultCheckout(true) }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build (ephemeral)') {
      steps {
        // Put Maven local repo inside the container’s /tmp so it vanishes with the container
        sh 'mvn -B -Dmaven.repo.local=/tmp/.m2 -Dmaven.test.failure.ignore=true clean package'
      }
      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        always {
          // Not strictly needed (container is removed), but keeps things tidy if you ever change args
          sh 'rm -rf /tmp/.m2 || true'
        }
      }
    }
  }
}
