@Library(['piper-lib', 'piper-lib-os']) _

pipeline {
  environment {
    mainCodelineBranch = 'USSC'
  }

  agent {
    label 'Automation'
  }
  options {
    skipDefaultCheckout()
  }
  stages {

    stage('Checkout') {
      steps {
        lock(resource: "${env.JOB_NAME}/10", inversePrecedence: true) {
          milestone 10
        }
        deleteDir()
        checkout scm
        setupPipelineEnvironment script: this
      }
    }

    stage('Pull-Request Voting') {
      when {
        not {
          anyOf {
            branch "${mainCodelineBranch}"
          }
        }
      }
      steps {
        lock(resource: "${env.JOB_NAME}/20", inversePrecedence: true) {
          milestone 20
        }
        script {
          sh "chmod +x ./getfiles.sh"
          sh(returnStdout: true, script: "./getfiles.sh >> repos.txt")
          def output = readFile(file: 'repos.txt')
          def lines = output.readLines()
          def seperator = " and "
          def tags = ""
          for (int i = 0; i < lines.size(); ++i) {
            if (i == 0) {
              tags += "@${lines[i]}"
            } else {
              tags += "$seperator"
              tags += "@${lines[i]}"
            }
          }
          sh 'mvn -B -U -gs ./selenium/feature_tests/settings.xml clean verify -pl selenium/feature_tests \\"-Dcucumber.filter.tags=$tags\\"'
        }
      }
    }

  }
}