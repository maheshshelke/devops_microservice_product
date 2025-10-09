pipeline {
  agent {
    docker {
      image 'maven:3.9.9-eclipse-temurin-21'   // or your original image
      args  '-v /tmp:/tmp'
    }
  }
  options { skipDefaultCheckout(true) }
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -Dmaven.test.failure.ignore=true clean package'
      }
      post {
        success {
          junit '**/target/surefire-reports/TEST-*.xml'
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
      }
    }
  }
}
