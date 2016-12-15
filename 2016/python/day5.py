import time
from hashlib import md5

salt = "ffykfhsq"
password = {}

i = 0
current = time.time()
while len(password) < 8:
    digest = md5((salt + str(i)).encode('utf-8')).hexdigest()
    if digest.startswith("00000"):
        pos = int(digest[5], 16)
        val = digest[6]

        if pos in range(8) and pos not in password:
            password[pos] = val

            pass_str = ['_'] * 8
            for key, val in password.items():
                pass_str[key] = val
            print(''.join(pass_str))

    i += 1

end = time.time()
diff = end - current
print(diff)
