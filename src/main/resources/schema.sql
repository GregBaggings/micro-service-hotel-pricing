CREATE TABLE prices
(
hotelid int NOT NULL,
roomid int NOT NULL IDENTITY,
roomname varchar(50),
price int
);