def buildApp() {
    echo "building the application..."
    withMaven(maven: 'maven') {
        sh 'mvn package'
    }
} 
def testApp() {
    echo "Testing the application..."

} 
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t aeramzy9/java-maven-app:latest .'
        sh "echo \$PASS | docker login -u \$USER --password-stdin"
        sh 'docker push aeramzy9/java-maven-app:latest'
    }
}

def deployApp() {
    echo 'deploying the application...'
} 

return this