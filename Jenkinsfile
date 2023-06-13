#!/usr/bin/env groovy

//define external groovy script handler
def gv

//specify full image name
//def dockerRepoImageName = 'miltosdev/my-private-repo:calc-1.1.1'
//def nexusRepoImageName = '164.92.250.242:8083/nx-calc:1.1.1'

//specify repo name only
def dockerRepoName = 'miltosdev/my-private-repo'
def nexusRepoName = '164.92.250.242:8083/nx-calc'

pipeline {
    agent any

    stages {
        stage('init') {
            steps {
                script {
                    gv = load 'script.groovy'
                }
            }
        }

        stage('increment pkg version') {
            steps {
                script {
                    echo 'incrementing package.json version...'
                    //increment patch version without git tag(add+commit)
                    sh 'npm --no-git-tag-version version patch'
                }
            }
        }

        stage('get pkg version') {
            steps {
                script {
                    PKG_VERSION = sh(
                    script: 'npm pkg get version | sed \'s/"//g\'',
                    returnStdout: true
                    ).trim()
                    echo "pkg version: ${PKG_VERSION}"
                    env.IMAGE_NAME = "calc-${PKG_VERSION}-build$BUILD_NUMBER"
                    echo "$dockerRepoName:${IMAGE_NAME}"
                }
            }
        }

        stage('build and push Nexus image') {
            steps {
                script {
                    echo 'stage : build and push nexus image'
                //gv.buildPushNexus "$nexusRepoImageName"
                }
            }
        }

        stage('build and push docker image') {
            steps {
                echo 'stage : build and push docker image'
                script {
                    gv.buildPushDocker "$dockerRepoName:${IMAGE_NAME}"
                //buildImage "$dockerRepoImageName"
                //dockerLogin()
                //dockerPush "$dockerRepoImageName"
                }
            }
        }

        stage('Jenkins commit version update') {
            steps {
                script {
                    sh 'git status'
                    sh 'git branch'
                    sh 'git config --list'
                    sh 'git remote set-url origin git@github.com:miltozz/calculator-demo.git'
                    sh 'git add .'
                    sh 'git commit -m "ci jenkins:version bump, package.json"'
                    sh 'git push origin HEAD:jenkins-push-test'
                }
            }
        }

        stage('deploy') {
            steps {
                script {
                    gv.deployApp()
                    echo 'app deployed'
                }
            }
        }
    }
}
