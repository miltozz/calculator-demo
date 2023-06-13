#!/usr/bin/env groovy

//def gv

pipeline{
    agent any

    stages{
        stage ("init"){
            steps{
                script{
                    echo "Init...."
                }
            }
        }
        stage ("test"){
            steps{
                script{
                    echo "testing...."
                    echo "Testing branch $BRANCH_NAME"
                }
            }
        }        
        stage("build"){
            when {
                expression{
                    BRANCH_NAME == 'master'
                }
            }            
            steps{
                script{
                    echo "building..."
                    //gv.buildApp()
                }
                
            }
        }
        stage("deploy"){
            when {
                expression{
                    BRANCH_NAME == 'master'
                }
            }            
            steps{
                echo "deploying..."
            }
        }
    }
}

