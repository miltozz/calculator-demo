#!/usr/bin/env groovy

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
                    BRANCH_NAME == 'main'
                }
            }            
            steps{
                script{
                    echo "building..."
                }
                
            }
        }
        stage("deploy"){
            when {
                expression{
                    BRANCH_NAME == 'main'
                }
            }            
            steps{
                echo "deploying..."
            }
        }
    }
}

