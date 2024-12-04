select Revon, count(OrderID)
from (select OrderID, year(OrderDate),
case
	when month(OrderDate) between 1 and 3 then "1"
    when month(OrderDate) between 4 and 6 then "2"
    when month(OrderDate) between 7 and 9 then "3"
    when month(OrderDate) between 10 and 12 then "4"
    end as Revon
from orders
where year(OrderDate) = 1996) as sto
group by Revon;