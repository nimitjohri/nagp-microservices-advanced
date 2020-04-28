CREATE DATABASE IF NOT EXISTS bestbuydb;
USE bestbuydb;

CREATE TABLE users (
user_id INTEGER AUTO_INCREMENT NOT NULL,
NAME VARCHAR(100) NOT NULL,
email_address VARCHAR(150) NOT NULL,
address VARCHAR(100) NOT NULL,
PRIMARY KEY(user_id)
);

INSERT INTO users VALUES
(1,"nimit johri", "nimit@gmail.com", "xyz, chhattarpur, New Delhi, 110074"),
(2,"Gajodhar Singh", "gajodharsingh@gmail.com","abc, sector 23, gurgaon, 122085"),
(3, "Ram Vilas", "ram@gmail.com", "xyz, Sector 18, Gurgaon, 122015");
