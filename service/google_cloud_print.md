# Публикуем локальный притер в Облако

Источник: http://habrahabr.ru/post/149520/

## Установка с исходников

```bash
sudo apt-get install git-core python python-cups python-setuptools

git clone git://github.com/armooo/cloudprint.git
cd cloudprint
chmod +x setup.py

python setup.py build
sudo python setup.py install
```

## Установка через pip

```bash
sudo apt-get install python-pip
sudo pip install cloudprint
```

## Первый запуск

```bash
cloudprint.py
```

## Настройка демона через Upstart

sudo nano /etc/init/cloudprint.conf
```
start on (started cups and local-filesystems and t-device-up IFACE=eth0)
stop on runlevel [345]

env RUN_AS_USER=valery1707
#which cloudprint.py
env CLOUD_PRINT_HOME=/usr/local/lib/python2.7/dist-packages/cloudprint-0.5-py2.7.egg/cloudprint

respawn
console none

script
	chdir ${CLOUD_PRINT_HOME}/
	exec su -c ${CLOUD_PRINT_HOME}/cloudprint.py - $RUN_AS_USER
end script
```
sudo start cloudprint


# Используем принтер из Облака в Windows

Paperless Printer: http://www.rarefind.com/paperlessprinter/index.html

# Используем принтер из Облака в Unbuntu

```bash
sudo add-apt-repository ppa:simon-cadman/cups-cloud-print
sudo apt-get update
sudo apt-get install cupscloudprint
sudo /usr/lib/cloudprint-cups/setupcloudprint.py
```
