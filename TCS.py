import os
import subprocess, sys
import urllib.request as req
from bs4 import BeautifulSoup as bs4
from collections import defaultdict
import collections

x = input('Input Code → ')
project_list = ['ant']
for i in project_list:
    file = open('NICAD/projects/' + i +'/a.java', 'w')
    file.write(x)
    file.close()

os.chdir('C:Users\\ryosuke-ku\\Desktop\\TCS\\NICAD')

print(os.getcwd())

cp = subprocess.run(['./nicad5','functions','java','projects/ant','default-report'])

os.chdir('C:Users\\ryosuke-ku\\Desktop\\TCS')
print(os.getcwd())

cp2 = subprocess.run(['python','scraping.py'])