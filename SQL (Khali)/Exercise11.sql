/* הציגו את שמות העובדים ממויינים לפי שם משפחה בסדר עולה ולפי שם פרטי בסדר יורד */  
select FirstName, LastName from employees order by LastName asc, FirstName desc;

/*הנהלת החברה החליטה לתת בונוס 10% לעובדים שמשכורתם מתחת ל – 10000. */
select first_name, last_name, salary, if(salary < 10000, salary * 1.1, "big salary") as newSalary
from employees;

/* 2.    יש להוציא את כל מספרי ההזמנות שבוצעו ברבעון השלישי של השנה (בין חודשים 6-8 כולל)*/
select OrderID from orders 
where month(OrderDate) between 6 and 8;

/* הציגו את ממוצע העובדים של כל מחלקה אשר כמות העובדים בה גדול מ- 1*/
/* ושם המחלקה מסתיים ב- g */
select avg(salary) as eAvg 
from employees E inner join departments D on D.department_id = E.department_id 
where department_name like "%g"
group by D.department_id having count(employee_id) > 1;

select concat(E1.first_name," ", E1.last_name) as fName1,
concat(E2.first_name," ", E2.last_name) as fName2,
E1.salary as s1 ,E2.salary as s2
from employees E1 inner join employees E2 on E1.employee_id < E2.employee_id 
where E2.salary = E1.salary;