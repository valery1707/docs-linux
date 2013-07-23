Основной источник: http://unbelll.blogspot.com/2011/03/powermust-600-ups-ubuntu.html
Дополнительно:
* http://manpages.ubuntu.com/manpages/precise/man8/nutupsdrv.8.html
* http://manpages.ubuntu.com/manpages/precise/man8/blazer.8.html
* http://www.networkupstools.org/docs/FAQ.html #15
* http://blog.shadypixel.com/monitoring-a-ups-with-nut-on-debian-or-ubuntu-linux/
todo Проверить что за пакет: knutclient

# Установка
```bash
sudo apt-get install nut

# Обновляем правила для udev: они нужны для предоставления доступа NUT к нужному USB порту
sudo udevadm control --reload-rules
sudo udevadm control trigger
# Это может и не помочь - так что лучше перезагрузить весь сервер после установки NUT
```

# Настройка подключения
```bash
sudo nano /etc/nut/ups.conf
```
```ini
[ups]
  driver = blazer_usb
  port = auto
  # todo определить наиболее подходящий subdriver
  desc = "Mustek PowerMust 637 Plus"
```

todo Проверка наличия прав на работу с USB [idVendor:idProduct]
```bash
lsusb
cat /etc/udev/rules.d/52-nut-usbups.rules | grep ...
```

Подключения драйвера
```bash
sudo upsdrvctl start

# Если не работает предыдущая команда можно проверить настройки драйвера командой
# (пользователь root - только для тестов)
sudo /lib/nut/blazer_usb -u root -a ups -DDD
```

# Настройка пользователей (мониторинг)
```bash
sudo nano /etc/nut/upsd.users
```
```ini
[upsmon]
  password = password
  allowfrom = localhost
  upsmon master
```

# Настройка сервиса мониторинга
```bash
sudo nano /etc/nut/upsmon.conf
```
```
MONITOR ups@localhost 1 upsmon password  master
NOTIFYCMD /usr/bin/wall
NOTIFYFLAG ONLINE  SYSLOG+EXEC
NOTIFYFLAG ONBATT  SYSLOG+EXEC
NOTIFYFLAG LOWBATT SYSLOG+EXEC
SHUTDOWNCMD "/sbin/shutdown -h now"
POLLFREQALERT 2
```

Установка режима работы сервера
```bash
sudo nano /etc/nut/nut.conf
```
```ini
mode = standalone
```

Параметры работы сервера
```bash
sudo nano /etc/nut/upsd.conf
```
```
LISTEN 127.0.0.1 3493
```

# Запуск серверов
```bash
sudo service nut restart
sudo service ups-monitor restart
```

# Проверка состояния
```bash
sudo upsc ups@localhost
```

# Проверка сценария выключения
```bash
sudo upsmon -c fsd
```
