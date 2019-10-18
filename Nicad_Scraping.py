import urllib.request as req
from bs4 import BeautifulSoup as bs4
from collections import defaultdict
import collections
import re
import csv
import os

ProjectName = 'ant'

url = "file:///C:/Users/ryosuke-ku/Desktop/TCS/NICAD/projects/" + ProjectName + "_functions-blind-clones/" + ProjectName + "_functions-blind-clones-0.30.xml"

# os.mkdir('NicadOutputFile_' + ProjectName)
res = req.urlopen(url)
#詳しくは省略、上のXMLが返ってくるものと思ってください
print(res)

startlist = [] #コード片の開始行番号を格納
endlist = [] #コード片の修了行番号を格納
startdic = defaultdict(list) #コード片の開始行番号を格納
enddic = defaultdict(list) #コード片の修了行番号を格納
startdicPath = defaultdict(list) #コード片の開始行番号を格納
enddicPath = defaultdict(list) #コード片の修了行番号を格納

allpathlist = []
NotestPath =[] #ファイルパスを格納(同じファイルパスを区別しない)
numNotestPath =[] #ファイルパスを格納(同じファイルパスを区別する)
soup = bs4(res,'lxml-xml')
data = defaultdict(list)
delTestdata = defaultdict(list)
numdelTestdata = defaultdict(list)

filePaths = soup.find('clones')
count = 0
cnt = 1
num = 1
for filePath in filePaths.find_all(['clone','source']):
	 
	if filePath.name == 'clone':
		count+=1
		key = "clone pairs:" + str(count) + ":"+ filePath.get('similarity') + "%"
		# print(key)
	if key and filePath.name == 'source':
		path = filePath.get('file') #例 systems/apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java
		path = path[8:] #例 systems/apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java →　apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java	
		allpathlist.append(path)
		data[key].append(path)
		cnt+=1

		cutFtestpath = path[-9:] #pathの文字列の末尾から９文字を取得 Test.java かどうかを判定に使う
		cutBtestpathnum = path.rfind("/")
		cutBtestpath = path[cutBtestpathnum + 1:]
		if cutFtestpath == 'Test.java':
			pass
		elif cutBtestpath[:4] == 'Test':
			pass
		else:
			registerdPath = str(num) + ':' + path # 3:apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java 同じファイルパスを区別するため
			NotestPath.append(path)
			numNotestPath.append(registerdPath)
			startline = filePath.get('startline') #コード片の開始行番号を取得
			endline = filePath.get('endline') #コード片の修了行番号を取得
			startlist.append(startline)
			endlist.append(endline)
			startdicPath[registerdPath].append(startline) # 3:apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java :80(startline)
			enddicPath[registerdPath].append(endline) # 3:apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java :100(endline)
			startdic[key].append(startline) # clone pairs:3:97% :80(startline)
			enddic[key].append(endline) # clone pairs:3:97% :100(endline)
			delTestdata[key].append(path) # clone pairs:3:97% :apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java
			numdelTestdata[key].append(registerdPath) # clone pairs:3:97% : 3:apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java ＊パスが区別されている
			num += 1


production = open(r'C:\Users\ryosuke-ku\Desktop\Path\newProductionPath.txt','r',encoding="utf-8_sig")
ProductionPath = production.readlines()
PPath = [Pline.replace('\n', '') for Pline in ProductionPath]
production.close()

Test = open(r'C:\Users\ryosuke-ku\Desktop\Path\newTestPath.txt','r',encoding="utf-8_sig")
TestPath = Test.readlines()
TPath = [Tline.replace('\n', '') for Tline in TestPath]
Test.close()


dic = dict(zip(PPath,TPath))
data2 = defaultdict(list)



delPairs = []
for t in numdelTestdata: # t = clone pairs:3:97%
	for h in numdelTestdata[t]: # h = 3:apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java
		if len(numdelTestdata[t]) != 2: # クローンペアが２つのコード片からならないものを取得する
			delPairs.append(t)
	

for pairs in delPairs:
	startkey = numdelTestdata[pairs]
	startdicPath.pop(startkey[0]) # プロダクションコード片とテストコード片からなるクローンペアのプロダクションコードの開始行を削除
	endkey = numdelTestdata[pairs]
	enddicPath.pop(endkey[0]) # プロダクションコード片とテストコード片からなるクローンペアのプロダクションコードの修了行を削除
	# print(numdelTestdata[pairs])
	numdelTestdata.pop(pairs)

print(numdelTestdata)
print(len(numdelTestdata))


onlyhasTestdata = defaultdict(list)
onlyhasTestPdata = defaultdict(list)

for i in numdelTestdata:
	for j in numdelTestdata[i]:
		delnum = re.sub(r".*?:", "", j) # 3:apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java → apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java
		path = dic.get(delnum) 
		data2[i].append(path) # clone pairs:3:97% : apache_ant/ant/src/test/org/apache/tools/ant/AntClassLoaderTest.java
		if path is not None:
			onlyhasTestdata[i].append(path) # clone pairs:3:97% : apache_ant/ant/src/test/org/apache/tools/ant/AntClassLoaderTest.java
			onlyhasTestPdata[i].append(j) # clone pairs:3:97% : apache_ant/ant/src/main/org/apache/tools/ant/AntClassLoader.java

# print(onlyhasTestPdata)

nt = 0
t1 = 0
t2 = 0
has2noMap = defaultdict(list)
no2hasMap = defaultdict(list)
allhasTestPairs = defaultdict(list)

for u in numdelTestdata:
	p1 = numdelTestdata[u][0]
	c1 = re.sub(r".*?:", "", p1)
	path1 = dic.get(c1)
	# print(path1)
	p2 = numdelTestdata[u][1]
	c2 = re.sub(r".*?:", "", p2)
	path2 =dic.get(c2)
	# print(path2)

	if path1 is not None and path2 is None:
		allhasTestPairs[u].append(p1)
		allhasTestPairs[u].append(path1)
		allhasTestPairs[u].append(p2)
		allhasTestPairs[u].append('None')
		t1 += 1
	if path1 is None and path2 is not None:
		allhasTestPairs[u].append(p1)
		allhasTestPairs[u].append('None')
		allhasTestPairs[u].append(p2)
		allhasTestPairs[u].append(path2)
		t1 += 1
	if path1 is not None and path2 is not None:
		allhasTestPairs[u].append(p1)
		allhasTestPairs[u].append(path1)
		allhasTestPairs[u].append(p2)
		allhasTestPairs[u].append(path2)
		t2 += 1
	if path1 is None and path2 is None:
		nt += 1

print('nt : ' + str(nt))
print('t1 : ' + str(t1))
print('t2 : ' + str(t2))
print('total : ' + str(nt + t1 +t2))

# print(allhasTestPairs)
print(len(allhasTestPairs))

num = 0
filenum = 1
cc = 0
testPath = open('C:/Users/ryosuke-ku/Desktop/getMapMethods/Nicad_' + ProjectName + '.txt','w') # Nicad_3.javaのファイルを開く
for i in allhasTestPairs:
	cc += 1
	os.mkdir('NicadOutputFile_' + ProjectName + '/Clone Pairs ' + str(cc))
	p1 = allhasTestPairs[i][0]
	ep1 = re.sub(r".*?:", "", p1)
	print(ep1)
	p1t = allhasTestPairs[i][1]
	p2 = allhasTestPairs[i][2]
	ep2 = re.sub(r".*?:", "", p2)
	p2t = allhasTestPairs[i][3]
	print(ep2)
	if p1t == 'None':
		testPath.write("None" + '\n')
	else:
		testPath.write("C:/Users/ryosuke-ku/Desktop/NiCad-5.1/systems/" + p1t + '\n')

	if p2t == 'None':
		testPath.write("None" + '\n')
	else:
		testPath.write("C:/Users/ryosuke-ku/Desktop/NiCad-5.1/systems/" + p2t + '\n')
	
	
	file = open('NicadOutputFile_' + ProjectName + '/Clone Pairs ' + str(cc) + '/Nicad_' + ProjectName + str(filenum) + '.java','w') # Nicad_3.javaのファイルを開く
	f = open("C:/Users/ryosuke-ku/Desktop/NiCad-5.1/systems/" + ep1, "r", encoding="utf-8")
	lines2 = f.readlines() # 1行毎にファイル終端まで全て読む(改行文字も含まれる)
	f.close()


	startline1 = int(startdicPath[p1][0])-1
	endline1 = int(enddicPath[p1][0])


	file.write('//' + p1 + '\n')
	file.write('//' + p1t + '\n')
	file.write('\n')
	file.write('public class Nicad_' + ProjectName + str(filenum) + '\n')
	file.write('{' + '\n')
	for x in range(startline1,endline1):
		try:
			print(lines2[x].replace('\n', ''))
			file.write(lines2[x].replace('\n', '') + '\n')
		except UnicodeEncodeError:
			pass
	file.write('}')
	filenum += 1

	file = open('NicadOutputFile_' + ProjectName + '/Clone Pairs ' + str(cc) + '/Nicad_' + ProjectName + str(filenum) + '.java','w') # Nicad_3.javaのファイルを開く
	f = open("C:/Users/ryosuke-ku/Desktop/NiCad-5.1/systems/" + ep2, "r", encoding="utf-8")
	lines2 = f.readlines() # 1行毎にファイル終端まで全て読む(改行文字も含まれる)
	f.close()

	startline2 = int(startdicPath[p2][0])-1
	endline2 = int(enddicPath[p2][0])

	file.write('//' + p2 + '\n')
	file.write('//' + p2t+ '\n')
	file.write('\n')
	file.write('public class Nicad_' + ProjectName + str(filenum) + '\n')
	file.write('{' + '\n')
	for x in range(startline2,endline2):
		try:
			print(lines2[x].replace('\n', ''))
			file.write(lines2[x].replace('\n', '') + '\n')
		except UnicodeEncodeError:
			pass
	file.write('}')
	filenum += 1

testPath.close()

