#1
select products.ProductName, suppliers.SupplierID
from products inner join suppliers 
on products.SupplierID = suppliers.SupplierID;

#2
select products.ProductName, categories.CategoryName
from products inner join categories 
on products.CategoryID = categories.CategoryID;

#3
select products.ProductName, categories.CategoryName, suppliers.ContactName
from products 
inner join categories 
on products.CategoryID = categories.CategoryID
inner join suppliers 
on products.SupplierID = suppliers.SupplierID;

#4
select distinct shippers.CompanyName
from orders inner join shippers
on orders.ShipVia = shippers.ShipperID
where orders.ShippedDate > "1997-1-1";

#5
select products.ProductName, order_details.Quantity
from products inner join order_details
on products.ProductID = order_details.ProductID
where order_details.Quantity > 50;

#6
select products.ProductName
from products inner join categories
on products.CategoryID = categories.CategoryID
where categories.CategoryName = "Beverages" and products.UnitPrice < 10;

#7
select categories.CategoryName, count(products.ProductID) as countP
from products inner join categories
on products.CategoryID = categories.CategoryID
group by categories.CategoryID
having countP > 10;
 
 #8
select distinct categories.CategoryName
from products inner join categories
on products.CategoryID = categories.CategoryID
where products.UnitPrice between 10 and 20
order by categories.CategoryName;

#9
select p1.ProductName, p1.UnitPrice,
p2.ProductName, p2.UnitPrice
from products p1 inner join products p2
on p1.ProductID < p2.ProductID
where p1.UnitPrice = p2.UnitPrice
order by p1.UnitPrice;