#1
select productID ,ProductName, CategoryID, UnitPrice
from products p1
where p1.UnitPrice < 
(select avg(UnitPrice)
from products p2
where p2.CategoryID = p1.CategoryID
group by CategoryID)
order by CategoryID;

#2
select OrderID, avg(UnitPrice * Quantity)
from order_details od1
group by OrderID
having avg(UnitPrice * Quantity) < 
(select avg(UnitPrice * Quantity)
from order_details od2
where od2.OrderID != od1.OrderID);

#3
select ProductName, UnitPrice
from products p1
where UnitPrice between
(select min(UnitPrice)
from products
where p1.CategoryID = CategoryID
group by CategoryID)
and
(select max(UnitPrice)
from products
where p1.CategoryID = CategoryID
group by CategoryID);

#4
select distinct EmployeeID
from orders O1
group by EmployeeID
having 
count(distinct CustomerID) <
(select count(distinct CustomerID) from orders O2 where O2.EmployeeID != O1.EmployeeID) / 8;

#5
select Country ,count(CustomerID) 
from customers C1
group by Country
having count(CustomerID) >
(select count(CustomerID) from customers where C1.Country != Country) * 0.1;

#6
select ProductName, UnitPrice
from products P
where UnitPrice >=
(select sum(UnitPrice) from products where P.CategoryID = CategoryID group by CategoryID) * 2;





