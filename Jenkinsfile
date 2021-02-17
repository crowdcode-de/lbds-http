pipeline {
    agent none
    options {
        office365ConnectorWebhooks([[
                                            offsetNotification: true,
                                            notifySuccess: true,
                                            notifyFailure: true,
                                            notifyUnstable: true,
                                            notifyBackToNormal: true,
                                            url: "${env.TEAMS_LBDS_WEBHOOK_URL}"
                                    ]]
        )
    }
    stages {
        stage('Prepare') {
            agent { label 'master' }
            steps {
                withCredentials(
                        [usernamePassword(credentialsId: 'CSchemmy',
                                usernameVariable: 'gitUser',
                                passwordVariable: 'gitPwd'
                        )]) {
                    script {
                        sh "git status"
                        sh "git checkout ${env.BRANCH_NAME}"
                        sh "git reset --hard origin/${env.BRANCH_NAME}"
                        pom = readMavenPom file: 'pom.xml'
                        final orginalVersion = pom.version
                        mvn("-DfailOnMissingBranchId=false -Dnamespace=org.hzi -DbranchName=${env.BRANCH_NAME} -Dgituser=${gituser} -Dgitpassword=${gitPwd} io.crowdcode:bgav-maven-plugin:1.0.0:bgav")
                        pom = readMavenPom file: 'pom.xml'
                        final newVersion = pom.version
                        if (!orginalVersion.equals(newVersion)) {
                            sh "mkdir -p target && touch target/DO_NOT_BUILD"
                            env.DO_NOT_BUILD=true
                        } else {
                            env.DO_NOT_BUILD=false
                        }
                    }
                }
            }
        }
        stage('Build jar') {
            agent { label 'Android-SDK-Manager-gradle' }
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps {  mvn("clean install") }
        }
        stage('Deploy jar') {
            agent { label 'Android-SDK-Manager-gradle' }
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps { mvn("deploy -DskipTests=true") }
        }
        stage('Build aar') {
            agent { label 'Android-SDK-Manager-gradle' }
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps {  mvn("clean install -f pom-aar.xml") }
        }
        stage('Deploy aar') {
            agent { label 'Android-SDK-Manager-gradle' }
            when {  environment name: "DO_NOT_BUILD", value: "false" }
            steps { mvn("deploy -DskipTests=true -f pom-aar.xml") }
        }
    }
}
def mvn(param) {
  if ( readMavenPom().getVersion().contains("SNAPSHOT") ) {
     env.docker_registry = "docker-snapshots.crowdcode.io"
  }
  else
  {
     env.docker_registry = "docker-release.crowdcode.io"
  }
  env.docker_registry_read = "docker-repo.crowdcode.io"
  withMaven(
      // globalMavenSettingsConfig: 'GlobalSettingsNexus',
      options: [openTasksPublisher(disabled: true)],
      mavenOpts: '-Xmx1536m -Xms512m',
      maven: 'maven-3.6.0') {
	    sh "mvn -U -B -e -P linux ${param}"
      }
}