
def buildPushDocker(String imageName) {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerhub-private-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t $imageName ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $imageName"
    }
} 

def buildPushNexus(String imageName) {
    echo "building the nexus image..."
    withCredentials([usernamePassword(credentialsId: 'nx-cont-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t $imageName ."
        sh "echo $PASS | docker login 164.92.250.242:8083 -u $USER --password-stdin"
        sh "docker push $imageName"
    }
}


def deployApp() {
    echo 'deploying the application...'
} 

return this
