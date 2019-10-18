from urllib import request
from bs4 import BeautifulSoup
import re
from pymongo import MongoClient
#url
url = "file:///C:/Users/ryosuke-ku/Desktop/TCS/NICAD/projects/utility_functions-blind-clones/utility_functions-blind-clones-0.30-classes-withsource.html"

#get html
html = request.urlopen(url)

#set BueatifulSoup
soup = BeautifulSoup(html, "html.parser")
# print(soup.find("pre").text)

clint = MongoClient()
db = clint['newDataBase']

codeArray = []
codePathArray = []
startLine = []
stopLine = []

tableCode = soup.find('table')
for item in tableCode.find_all('td'):
    if item.name == 'td':
        srccode = item.find('pre')
        # print(srccode.text)
        codeArray.append(srccode.text)
        item.find('pre').decompose()
        # print(item.text)
        codePathArray.append(item.text)


# print(codeArray)
# print('\n')
# print(codePathArray)
for codePath in codePathArray:
    # print(codePath)
    codePath_cutFront = re.sub(r"Lines ", "", codePath)
    # print(codePath_cutFront)
    num = codePath_cutFront.find('o')
    codePath_cutEnd = codePath_cutFront[:num]
    codePath_rmSpace = codePath_cutEnd.replace(' ','')
    print(codePath_rmSpace)
    num_hyphen = codePath_rmSpace.find('-')
    # print(num_hyphen)
    startLine = codePath_rmSpace[:num_hyphen]
    print(startLine)
    endLine = codePath_rmSpace[num_hyphen+1:]
    print(endLine)

    items = db.testList.find({'startline1':startLine,'endline1':endLine})
    for item in items:
        print(item)