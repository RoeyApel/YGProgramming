#1
select round(avg(salary)) as avg
from employees;

#2
select round(avg(salary)) as avgS
from employees group by job_id;

#3
select count(employee_id) as count, job_id
from employees group by job_id;

#4 
select min(salary) as minS, manager_id
from employees 
where salary > 6000
group by manager_id
order by min(salary) asc;

#5
select sum(salary) as sum,
min(hire_date) as minD,
year(curdate()) - year(min(hire_date)) as years,
sum(salary) / year(min(hire_date)) as bonus,
department_id
from employees
group by department_id;

#6
select year(curdate()) - year(min(hire_date)) as veterancy, department_id
from employees
group by department_id;

#7
select avg(year(curdate()) - year(hire_date)) as avgD,
year(curdate()) - year(avg(hire_date)) as avgD2, 
job_id
from employees
group by job_id;
#job history :>


