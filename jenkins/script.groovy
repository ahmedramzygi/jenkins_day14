def buildApp() {
    echo "building the application..."
    sh 'mvn package'
} 
def testApp() {
    echo "Testing the application..."

} 
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t java-maven-app .'
        sh "echo $PASSWORD | docker login -u $USERNAME $--password-stdin"
        sh 'docker push docker push aeramzy9/java-maven-app:latest'
    }
} 

def deployApp() {
    echo 'deploying the application...'
} 

return this