'''
A simple program that reads data from thinkspeak and displays the data on a sense hat diaplay

Author: Willoughby Peppler-Mann
'''

from sense_hat import SenseHat
import requests


#my key 
readKey = "<KEY>" # insert your key here
#my channel
channel_number = "<CHANNEL NUMBER>" # insert your channel number here


url = "https://api.thingspeak.com/channels/"+channel_number+"/feeds.json"

results = 2

def main():
    
    payload = {'api_key': readKey, 'results':results}
    
    # Get data from thingspeak
    response = requests.get(url, params=payload)
    # format response as json
    response = response.json()
    
    
    enteries = response['feeds']
    
    # for each entry returned from database output entry to sense hat
    sense = SenseHat() 
    for feed in enteries:
        print(feed)
        sense.show_message(f"{feed}",0.05)
    
    
    
if __name__ == '__main__':
    main()
    
