import hashlib

dat = 'persol' 

# MD5のハッシュ値
hs = hashlib.md5(dat.encode()).hexdigest()
print(hs)