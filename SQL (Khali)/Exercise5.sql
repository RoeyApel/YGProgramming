select count(employee_id) as empolyees_count,
max(salary) as max, min(salary) as min, 
round(avg(salary),2) as average, 
round(sum(salary),2) as sum
from employees;