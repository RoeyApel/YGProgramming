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

#5
