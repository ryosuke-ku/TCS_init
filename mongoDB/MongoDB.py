from pymongo import MongoClient
from datetime import datetime

class TestMongo(object):

     def __init__(self):
         self.clint = MongoClient()
         self.db = self.clint['newDataBase']

     def add_one(self):
        """データ挿入"""
        post = {
            'title': 'a',
            'content': 'b',
            'startline1': '6',
            'endline1': '19',
            'startline2': '43',
            'endline2': '645'
            # 'created_at': datetime.now()
        }
        return self.db.testList.insert_one(post)

     def add_src(self):
        """データ挿入"""
        post = {
            'title': 'ハリマロン',
            'content': 'ゴーリキー',
            'created_at': datetime.now()
        }
        return self.db.testList.insert_one(post)

def main():
    obj = TestMongo()
    rest = obj.add_one()
    print(rest)

if __name__ == '__main__':
    main()