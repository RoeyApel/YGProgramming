select ShipName, ShippedDate
from orders
where ShippedDate > "1997-1-1"
order by ShippedDate;

select ProductId, Quantity 
from order_details
where Quantity > 50
order by Quantity;

select FirstName, LastName
from employees
where LastName like "%n";

select FirstName, LastName
from employees
where LastName like "k%";

select FirstName, LastName
from employees
where LastName like "%l%";

select FirstName, LastName, BirthDate
from employees
where BirthDate between "1958-1-9" and "1963-8-30"
order by BirthDate;

select FirstName, LastName, City
from employees
where City in("London", "Seattle"); 

select FirstName, LastName, Country
from employees
where Country like "USA" and LastName like "%l%"; 