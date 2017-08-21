# Pricing module for my Hotel application

This module gives the price details for the other services and for the planned native application.

##Database:
Table prices

Name | Type 
------------ | -------------
hotelid | int NOT NULL
roomid | int NOT NULL
roomname | varchar(50)
price | int NOT NULL


###Endpoints:
####List Prices

***Endpoint:*** {host}/v1/hotels/prices

***Example:*** http://localhost:2223/v1/hotels/prices
####List Prices for a certain hotel
***Endpoint:*** {host}/v1/hotels/price  

Params | Type  
------------ | -------------   
id | int   

***Example:*** http://localhost:2223/v1/hotels/price?id=1