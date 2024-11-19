#1
select first_name, last_name, salary
from employees
where salary < (select avg(salary) from employees)
order by salary desc;

#2
select E.first_name, E.last_name, E.salary, E.department_id
from employees E
where salary < 
(select avg(salary) from employees
 group by department_id
 having department_id = E.department_id)
order by salary desc;

#3
select first_name, last_name, salary
from employees
where salary = 
(select salary from employees where last_name = "Kochhar")
and last_name != "Kochhar";

#4
-- שאילתא שתציג את כל העובדים שמרוויחים יותר ממנהלי
-- המכירות בחברה job_id=‘SA_MAN’ ומיינו לפי שכר
-- מגבוה לנמוך

select E1.salary, E2.manager_id, E1.employee_id, E2.job_id
from employees E1 inner join employees E2 on E1.employee_id = E2.manager_id
where E2.job_id = "SA_MAN" 
order by salary;

