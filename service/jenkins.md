#https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins+on+Ubuntu

wget -q -O - http://pkg.jenkins-ci.org/debian/jenkins-ci.org.key | sudo apt-key add -
sudo sh -c 'echo deb http://pkg.jenkins-ci.org/debian binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt-get update
sudo apt-get install jenkins

#Изменяем префикс для работы не в корне сайта ($PREFIX):
sudo nano /etc/default/jenkins
