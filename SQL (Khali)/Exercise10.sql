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
select *
from employees
where salary < 
(select max(salary) from employees where job_id = "SA_MAN")
order by salary desc;

#5
select employee_id from employees E
where (select city from departments D inner join locations L on L.location_id = D.location_id
where department_id = E.department_id)
like "T%";

#6
select city from locations 
where postal_code = 
(select max(postal_code) from locations);

#7
select job_title from jobs where min_salary = 
(select min(min_salary) from jobs);

#8
select department_id  from employees
group by department_id having avg(year(curdate()) - year(hire_date)) <
(select avg(year(curdate()) - year(hire_date)) from employees);

#9
select employee_id from employees
where employee_id not in(select distinct manager_id from employees where manager_id is not null)
order by employee_id;

#10
select D.department_name, D.department_id 
from employees E right join departments D on E.department_id = D.department_id
where E.employee_id is null;

#11
select department_id 
from employees 
group by department_id 
having max(salary) - min(salary) = (
	select max(m)
	from (
		select max(salary) - min(salary) as m
        from employees 
        group by department_id
		) as diff
	)
;

#12
select D.department_name, department_id
from employees E inner join departments D on E.department_id = D.department_id
group by department_id 
having max(salary) - min(salary) = (
	select max(m)
	from (
		select max(salary) - min(salary) as m
        from employees 
        group by department_id
		) as diff
	)
;

#13


select * from employees;
# select * from departments order by location_id;
# select * from locations order by city;
