'''
A simple program that stores sense hat data into a thinkspeak database

Author: Willoughby Peppler-Mann

'''

from sense_hat import SenseHat
import requests
import time

send_key = "<KEY>"
url="https://api.thingspeak.com/update"

def main():
    sense = SenseHat()
    
    
    while True:
        temperature = sense.get_temperature()
        humidity = sense.get_humidity()
        pressure = sense.get_pressure()
    
        payload = {'field1': temperature, 'field2': humidity, 'field3': pressure, 'api_key':send_key}
        
        try:
            response = requests.get(url, params=payload)
            response = response.json()
            
            print(response)
        except:
            print("Connection Failed")
        
        time.sleep(120) # wait two minutes

if __name__ == "__main__":
        main()