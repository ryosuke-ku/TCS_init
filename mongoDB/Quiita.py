from pymongo import MongoClient

client_1 = MongoClient()
client_2 = MongoClient('localhost',27017)
client_3 = MongoClient('mongodb://localhost:27017/')