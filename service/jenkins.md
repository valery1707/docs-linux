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

# Настройка slave на Ubuntu

```bash
sudo useradd --create-home --home /srv/jenkins-slave --gid nogroup --comment "Login for Jenkins slave" jenkins-slave
sudo -i -u jenkins-slave
ssh-keygen # Без пароля
sudo -u jenkins-slave cp /srv/jenkins-slave/.ssh/id_rsa.pub /srv/jenkins-slave/.ssh/authorized_keys
sudo chmod g-r,o-r /srv/jenkins-slave/.ssh/authorized_keys
sudo less /srv/jenkins-slave/.ssh/id_rsa
```

## Устанавливаем все что нужно для сборки проекта:
[/common-java]
[/vcs/git]

## Не забываем прописать наш ключ (/srv/jenkins-slave/.ssh/id_rsa.pub) для доступа к git-репозиториям

#sudo apt-get install jenkins-slave
