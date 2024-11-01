#1
select FirstName, LastName
from employees
where length(FirstName) = 4;

#2
select FirstName, LastName
from employees
where length(FirstName) = length(LastName);

#3
select FirstName, LastName, 
if(length(FirstName) < length(LastName), "short", "not short") as len 
from employees;

#4
select *
from suppliers
where left(ContactName, 1) = left(City, 1);

#5
select replace(Phone, '-',' ') as PhoneNumber
from suppliers;

#6
select *
from orders
where month(OrderDate) = 10;

#7
select *, year(curdate()) - year(BirthDate) as age
from employees
where year(curdate()) - year(BirthDate) > 60;

#8
select *, month(BirthDate) as monthy
from employees
where month(BirthDate) between 1 and 4;

#9
select * 
from orders
where month(OrderDate) = day(OrderDate);

#10
select *
from products
where locate("." , UnitPrice) != 0;

#13
select ID, OrderID, ProductID, UnitPrice, Quantity,
round(UnitPrice - floor(UnitPrice), 1) as Discount
from order_details
where locate("." , UnitPrice) != 0;

#15
select * , ucase(concat(left(FirstName, 1), right(LastName, 1))) as FNCode
from employees;

#16
select concat(right(FirstName, 2), substring(LastName, 3)) as LastNameE
from employees;