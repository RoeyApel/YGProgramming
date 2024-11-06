#1
select count(distinct job_id) as count, department_id
from employees
group by department_id;

#2
select count(distinct job_id) as countJ, 
count(distinct manager_id) as countM, department_id
from employees
group by department_id;

#3
select sum(salary) as sum, department_id
from employees
group by department_id;

#4
select sum(distinct salary) as sum, department_id
from employees
group by department_id;

#5
select round(avg(salary), 2) as avgS, 
round(avg(distinct salary) - avg(salary),2) as difference, department_id
from employees 
group by department_id
having count(employee_id) > 2
order by difference;

#6
select avg(salary) as avgS, department_id
from employees 
group by department_id
having count(employee_id) > 2;

#7
select department_id, count(employee_id) as count
from employees
group by department_id;

#8
select department_id, count(employee_id) as countE
from employees
group by department_id
having countE = 1;
