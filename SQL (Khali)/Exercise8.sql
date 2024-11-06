#1
select products.ProductName, suppliers.ContactName
from products inner join suppliers 
on products.SupplierID = suppliers.SupplierID;

#2
select products.ProductName, categories.CategoryName
from products inner join categories 
on products.CategoryID = categories.CategoryID;