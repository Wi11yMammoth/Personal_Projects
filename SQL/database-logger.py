#!/usr/bin/env python
from sense_hat import SenseHat
import sqlite3
import time

# connect to SenseHat
sense = SenseHat() 

#connect to db
dbconnect = sqlite3.connect("sensorDB.db")

#set row_factory to sqlite3.Row to access columns by name
dbconnect.row_factory = sqlite3.Row;

cursor = dbconnect.cursor();

# save 20 reads into the db
for i in range(20):
    cursor.execute(f'''insert into sensordata values ({i}, date('now'), time('now'),
                   {sense.get_temperature()}, {sense.get_humidity()}, {sense.get_pressure()})''');
    dbconnect.commit();
    
    time.sleep(1)

print('Done')
