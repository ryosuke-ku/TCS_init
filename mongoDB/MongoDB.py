from pymongo import MongoClient
from datetime import datetime

class TestMongo(object):

     def __init__(self):
         self.clint = MongoClient()
         self.db = self.clint['test']

     def add_one(self):
        """データ挿入"""
        post = {
            'title': 'ハリネズミ',
            'content': 'ハリネズミ可愛い~',
            'created_at': datetime.now()
        }
        return self.db.test.insert_one(post)

     def add_clone(self):
        """データ挿入"""
        clone1 = "aaaaaa"
        clone2 = "bbbbbb"
        post2 = {
            'clone1': clone1,
            'clone2': clone2,
        }
        return self.db.test.insert_one(post2)        

def main():
    obj = TestMongo()
    rest = obj.add_clone()
    print(rest)

if __name__ == '__main__':
    main()