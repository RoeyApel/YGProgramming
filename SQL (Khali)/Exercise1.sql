SELECT city FROM employees;

SELECT DISTINCT city FROM employees;

SELECT firstName, lastName, birthDate
FROM employees;

SELECT * FROM suppliers 
order by companyName; 

SELECT * FROM suppliers 
order by companyName desc; 

select SupplierID as "A",
 CompanyName as "B", ContactName as "C",
 ContactTitle as "D", Address as "E",
 City as "F", Region as "G",
 PostalCode as "H", Country as "I", 
 Phone as "J", Fax as "K", HomePage as "L"
 from suppliers;
 
 select * from customers
 where city like "Madrid";
 
 select * from customers
 where country in ("Sweden", "Germany");
 
select * from customers
 where country like "UK" and customerID < 10;
 
