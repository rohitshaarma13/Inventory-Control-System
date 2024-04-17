create database Inventorycontrolsystem;
use Inventorycontrolsystem;
create table login(username varchar(20), password varchar(20));
insert into login values('rohit','sharma');
select*from login;
CREATE TABLE products(
    product_id VARCHAR(20) PRIMARY KEY,
    product_name VARCHAR(100),
    category VARCHAR(50),
    price DOUBLE,
    quantity INT
);
select*from products;

