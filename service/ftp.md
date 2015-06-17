vsftpd
======

Add new version repository:
  sudo add-apt-repository ppa:thefrontiergroup/vsftpd
  sudo apt-get update

Install vsftpd
  sudo apt-get install vsftpd

Update configuration: `sudo nano /etc/vsftpd.conf`
  # we're running standalone
  listen=YES
  # enable virtual users:
  anonymous_enable=NO
  local_enable=YES
  guest_enable=YES
  # Configure users
  user_config_dir=/etc/vsftpd/user.d/
  # Where the accounts are located..
  guest_username=www-data
  user_sub_token=$USER
  local_root=/srv/vsftpd/$USER
  chroot_local_user=YES
  allow_writeable_chroot=YES
  # allow writing
  write_enable=YES
  local_umask=022
  virtual_use_local_privs=YES
  # some general options
  dirmessage_enable=YES
  hide_ids=YES
  secure_chroot_dir=/var/run/vsftpd
  connect_from_port_20=YES
  # here we define OUR pam config [see below]
  pam_service_name=vsftpd
  # more verbose logging
  xferlog_enable=YES
  log_ftp_protocol=YES
  setproctitle_enable=YES

Configure PAM: `sudo nano /etc/pam.d/vsftpd`
  auth    required    pam_pwdfile.so pwdfile /etc/vsftpd/ftpd.passwd
  account required    pam_permit.so

Add new virtual user:
  ftpuser=new_ftp_user
  sudo mkdir -p /srv/vsftpd/${ftpuser} && sudo chmod +w /srv/vsftpd/${ftpuser} && sudo chown -R vsftpd:nogroup /srv/vsftpd/${ftpuser}
  sudo htpasswd -d /etc/vsftpd/ftpd.passwd ${ftpuser}

Restart server
  sudo service vsftpd restart

Add a local users:
  useradd new_ftp_user
  passwd new_ftp_user
