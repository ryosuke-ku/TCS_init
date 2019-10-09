import os
import subprocess, sys

x = input('Input Code → ')
project_list = ['NicadOutputFile_t2_maven']
for i in project_list:
    file = open('NICAD/projects/' + i +'/a.java', 'w')
    file.write(x)
    file.close()

os.chdir('C:Users\\ryosuke-ku\\Desktop\\TCS\\NICAD')

print(os.getcwd())

cp = subprocess.run(['./nicad5','functions','java','projects/NicadOutputFile_t2_maven','default-report'])
# cp = subprocess.run(['ls', '-a'])
print(os.getcwd())

