pipeline {
  agent {
    docker {
      image 'maven:3.9.9-eclipse-temurin-21'
      // mount /tmp so mvn can use it; everything else stays default
      args '-v /tmp:/tmp'
      reuseNode true
    }
  }
  options { skipDefaultCheckout(true) }
  stages {
    stage('Checkout') {
      steps { checkout scm }
    }
    stage('Build') {
      steps {
        // Use a repo inside the workspace that is always writable
        sh 'mkdir -p .m2'
        sh 'mvn -B -Dmaven.repo.local=$WORKSPACE/.m2 -Dmaven.test.failure.ignore=true clean package'
      }
    }
  }
  post {
    success {
      junit '**/target/surefire-reports/TEST-*.xml'
      archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
    }
  }
}
