'''
How to get realtime updates from firebase on a raspberry pi using pyrebase

- Willoughby Peppler-Mann
'''

import pyrebase

config = {'apiKey':'<API KEY>',
          'authDomain': '<PROJECT ID>.firebaseapp.com',
          'databaseURL': '<PROJECT ID>-default-rtdb.firebaseio.com/',
          'storageBucket': '<PROJECT ID>.appspot.com'}

firebase = pyrebase.initialize_app(config)
db = firebase.database()

def listener(message):
    '''
    When data gets added to the dataset you are listening to
    you can do something with it here. 
    '''
    print(f"\n\n: {message['data']}")
    
    # ADD FUNCTIONALITY HERE
    

def initialize_listener():
    db.child('DATA SET').stream(listener) # set the data set to what you want to get realtime updates from


def write(data : dict):
    '''
    data is a dictionary {key : value} that you want to write to firebase
    ''' 
    db.child('DATA SET').set(data) # write data to a specific data set 


