'''
This program creates a graph of the data in sensorDB.db

'''
from matplotlib import pyplot as plt
import sqlite3
import pandas as pd

# connect to the db
dbconnect = sqlite3.connect("sensorDB.db")

# read from the db
data = pd.read_sql('SELECT temperature, pressure, humidity,ttime FROM sensordata', dbconnect)

print(data)
# graph the data (temperature, pressure, and humidity with respect to time

fig, ax = plt.subplots(constrained_layout=True)

# on left axis set up temperature and humidity
# left axis is ax
ax.set_title('Sensor Data over time')
ax.set_xlabel('time')
ax.set_ylabel(f"temperature ({chr(176)}C), humidity(%)")
ax.scatter(data['ttime'], data['humidity'], color='blue',label='humidity')
ax.scatter(data['ttime'], data['temperature'],color='green',label='temperature')
ax.legend()

# on right axis set up pressure
# ax2 is right axis
ax2 = ax.twinx()
ax2.set_ylabel('pressure (millibars)')
ax2.scatter(data['ttime'], data['pressure'].round(2), color='red', label='pressure')
ax2.legend()

ax.xaxis.set_major_locator(plt.MaxNLocator(6))
plt.show()


print('Done')
