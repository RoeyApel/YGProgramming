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
job_id
from employees
group by job_id;

#8
select avg(salary) as avgS, year(curdate()) - year(hire_date) as veterancy
from employees
group by veterancy;

#9
select avg((min_salary + max_salary) / 2) as avgS,
length(job_title) as lenT
from jobs
group by lenT;

#10
select left(phone_number, 3) as Kidomet, count(phone_number) as count
from employees
group by Kidomet;

#11
select department_id, count(employee_id) as count
from employees
group by department_id;

#12
select department_id, count(employee_id) as countE
from employees
group by department_id
having countE = 1;

#13
select department_id, concat(min(salary)," - ",max(salary)) as טווח
from employees
group by department_id;
