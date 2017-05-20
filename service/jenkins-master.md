# Установка

#https://wiki.jenkins-ci.org/display/JENKINS/Installing+Jenkins+on+Ubuntu

```bash
wget -q -O - http://pkg.jenkins.io/debian-stable/jenkins.io.key | sudo apt-key add -
sudo sh -c 'echo deb https://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list'
sudo apt-get update
sudo apt-get install jenkins
```

## Изменяем префикс для работы не в корне сайта ($PREFIX):

```bash
sudo nano /etc/default/jenkins
```
