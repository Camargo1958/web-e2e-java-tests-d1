# web-e2e-java-tests-d1

Web app E2E tests with Java+Selenium+TestNG demo 1

mvn clean install
mvn test
mvn test -DbrowserName=firefox
mvn test -Dsurefire.suiteXmlFiles=testng.xml

# Using Docker Compose:
For Selenium Standalone:
$>cd docker
$>docker compose -f docker-compose_standalone.yml up -d
mvn test -Dplatform=remote -Dsurefire.XmlFiles=testng.xml
mvn teste -Dplatform=remote
mvn test -Dplatform=remote -DbrowserName=firefox
mvn test -Dplatform=remote -DbrowserName=chrome
docker compose -f docker-compose_standalone.yml down

# For Selenium Grid:
$>cd docker
$>docker compose -f docker-compose_grid.yml up
mvn test -Dplatform=remote -DbrowserName=chrome (tested)
mvn test -Dplatform=remote -DbrowserName=firefox (tested)

mvn test -Dplatform=remote -Dsurefire.XmlFiles=testng.xml (tested)

mvn test -DbrowserName=chrome -Dsurefire.suiteXmlFiles=testng.xml (ok but Error)

# Using Jenkins Docker image:
https//github.com/jenkinsci/docker
$>docker run -d -p 8080:8080 -p 50000:50000 --restart=on-failure --name jenkins jenkins/jenkins:lts-jdk17
$>docker run -d -p 8080:8080 -p 50000:50000 --restart=on-failure -v jenkins_home:/var/jenkins_home --name jenkins jenkins/jenkins:lts-jdk17

JenkinsContainer logs >
Jenkins initial setup is required. An admin user has been created and a password generated.

Please use the following password to proceed to installation:
password: aed660d9aa3f486f8897afe4f59d93ae

1)localhost:8080 > use given password
2)Install suggested plugins
3)Create first admin user> hazak: hzk123
4)Configure Jenkins URL > http://localhost:8080
