#!/usr/bin/env groovy
@Library('piper-lib') _
import com.sap.icd.jenkins.Utils

def neoDeployWar(parameters = [:]) {

    handleStepErrors (stepName: 'neoDeployWar', stepParameters: parameters) {

        def utils = new Utils()
        def script = parameters.script
        if (script == null){
            script = [globalPipelineEnvironment: globalPipelineEnvironment]
        }

        def archivePath = new File(utils.getMandatoryParameter(parameters, 'archivePath', null))
        if (!archivePath.isAbsolute()) {
            archivePath = new File(pwd(), archivePath.getPath())
        }
        if (!archivePath.exists()){
            error "Archive cannot be found with parameter archivePath: '${archivePath}'."
        }

        def defaultDeployHost = script.globalPipelineEnvironment.getConfigProperty('DEPLOY_HOST')
        def defaultDeployApplication = script.globalPipelineEnvironment.getConfigProperty('CI_DEPLOY_APPLICATION')
        def defaultDeployAccount = script.globalPipelineEnvironment.getConfigProperty('CI_DEPLOY_ACCOUNT')
        def defaultCredentialsId = script.globalPipelineEnvironment.getConfigProperty('neoCredentialsId')
        if (defaultCredentialsId == null) {
            defaultCredentialsId = 'CI_CREDENTIALS_ID'
        }

        def deployHost = utils.getMandatoryParameter(parameters, 'deployHost', defaultDeployHost)
        def deployApplication = utils.getMandatoryParameter(parameters, 'deployApplication', defaultDeployApplication)
        def deployAccount = utils.getMandatoryParameter(parameters, 'deployAccount', defaultDeployAccount)
        def credentialsId = utils.getParameter(parameters, 'neoCredentialsId', defaultCredentialsId)
        def options = utils.getParameter(parameters, 'options', '')

        def neoExecutable = getNeoExecutable(parameters)

        withCredentials([usernamePassword(
                credentialsId: credentialsId,
                passwordVariable: 'password',
                usernameVariable: 'username'
        )]) {
            sh """#!/bin/bash
                    "${neoExecutable}" deploy \
                      --user '${username}' \
                      --host '${deployHost}' \
                      --source "${archivePath.getAbsolutePath()}" \
                      --account '${deployAccount}' \
                      --password '${password}' \
                      --application '${deployApplication}' \
                      ${options}
               """
            sh """#!/bin/bash
                    "${neoExecutable}" restart \
                      --user '${username}' \
                      --host '${deployHost}' \
                      --account '${deployAccount}' \
                      --password '${password}' \
                      --application '${deployApplication}' \
                      --synchronous
               """
        }
    }
}

def neoDeployHTML5(parameters = [:]) {

    handleStepErrors (stepName: 'neoDeployHTML5', stepParameters: parameters) {

        def utils = new Utils()
        def script = parameters.script
        if (script == null){
            script = [globalPipelineEnvironment: globalPipelineEnvironment]
        }

        def archivePath = new File(utils.getMandatoryParameter(parameters, 'archivePath', null))
        if (!archivePath.isAbsolute()) {
            archivePath = new File(pwd(), archivePath.getPath())
        }
        if (!archivePath.exists()){
            error "Archive cannot be found with parameter archivePath: '${archivePath}'."
        }

        def defaultDeployHost = script.globalPipelineEnvironment.getConfigProperty('DEPLOY_HOST')
        def defaultDeployApplication = script.globalPipelineEnvironment.getConfigProperty('CI_DEPLOY_HTML5_APPLICATION')
        def defaultDeployAccount = script.globalPipelineEnvironment.getConfigProperty('CI_DEPLOY_ACCOUNT')
        def defaultCredentialsId = script.globalPipelineEnvironment.getConfigProperty('neoCredentialsId')
        if (defaultCredentialsId == null) {
            defaultCredentialsId = 'CI_CREDENTIALS_ID'
        }

        def deployHost = utils.getMandatoryParameter(parameters, 'deployHost', defaultDeployHost)
        def deployApplication = utils.getMandatoryParameter(parameters, 'deployApplication', defaultDeployApplication)
        def deployAccount = utils.getMandatoryParameter(parameters, 'deployAccount', defaultDeployAccount)
        def credentialsId = utils.getParameter(parameters, 'neoCredentialsId', defaultCredentialsId)
        def options = utils.getParameter(parameters, 'options', '')

        def neoExecutable = getNeoExecutable(parameters)

        withCredentials([usernamePassword(
                credentialsId: credentialsId,
                passwordVariable: 'password',
                usernameVariable: 'username'
        )]) {
            sh """#!/bin/bash
                    "${neoExecutable}" deploy \
                      --user '${username}' \
                      --host '${deployHost}' \
                      --source "${archivePath.getAbsolutePath()}" \
                      --account '${deployAccount}' \
                      --password '${password}' \
                      --application '${deployApplication}' \
                      ${options}
               """
            sh """#!/bin/bash
                    "${neoExecutable}" restart \
                      --user '${username}' \
                      --host '${deployHost}' \
                      --account '${deployAccount}' \
                      --password '${password}' \
                      --application '${deployApplication}' \
                      --synchronous
               """
        }
    }
}

private getNeoExecutable(parameters) {

    def neoExecutable = 'neo' // default, if nothing below applies maybe it is the path.

    if (parameters?.neoHome) {
        neoExecutable = "${parameters.neoHome}/tools/neo.sh"
        echo "[neoDeployWar] Neo executable \"${neoExecutable}\" retrieved from parameters."
        return neoExecutable
    }

    if (env?.NEO_HOME) {
        neoExecutable = "${env.NEO_HOME}/tools/neo.sh"
        echo "[neoDeployWar] Neo executable \"${neoExecutable}\" retrieved from environment."
        return neoExecutable
    }

    echo "Using Neo executable from PATH."
    return neoExecutable
}
return this

