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
