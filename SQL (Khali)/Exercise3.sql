select * from customers
where city like "Madrid";
 
select * from customers
where country in ("Sweden", "Germany");
 
select * from customers
where country like "UK" and customerID < 10;

select * 
from products
where QuantityPerUnit like "%oz%";

select * 
from products
where QuantityPerUnit like "%jars%" or QuantityPerUnit like "%bottles%";