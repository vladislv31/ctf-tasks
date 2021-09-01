# Hack Me Please: 1

## url: https://www.vulnhub.com/entry/hack-me-please-1,731/

- main.js: ```/seeddms51x/seeddms-5.1.22```
- site using SeedDMS
- with gobuster find /seeddms51x/conf/settings.xml
- find db credentials
- connect to db -> check table users
    - ```
+-------------+---------------------+--------------------+-----------------+
| Employee_id | Employee_first_name | Employee_last_name | Employee_passwd |
+-------------+---------------------+--------------------+-----------------+
|           1 | saket               | saurav             | Saket@#$1337    |
+-------------+---------------------+--------------------+-----------------+
```
    - change admin password(cms using only md5 for encrpyting password)
- login to admin panel
- upload reverse shell as document and connect to server as www-data
- su saket (use password from table users)
- sudo su root
