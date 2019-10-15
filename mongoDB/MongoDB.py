from pymongo import MongoClient
from datetime import datetime

class TestMongo(object):

     def __init__(self):
         self.clint = MongoClient()
         self.db = self.clint['test']

     def add_one(self):
        """データ挿入"""
        post = {
            'title': 'a',
            'content': 'b',
            # 'created_at': datetime.now()
        }
        return self.db.test.insert_one(post)

     def add_src(self):
        """データ挿入"""
        post = {
            'title': 'ハリネズミ',
            'content': 'public static String apply(int num) { if (num % 15 == 0) { return "FizzBuzz"; } else if (num % 3 == 0) { return "Fizz"; } else if (num % 5 == 0) { return "Buzz"; } else { return String.valueOf(num); } }',
            'created_at': datetime.now()
        }
        return self.db.test.insert_one(post)
def main():
    obj = TestMongo()
    rest = obj.add_one()
    print(rest)

if __name__ == '__main__':
    main()