USE Hotel_DB;

#1


#2
select concat(room_type, " room type") as what, sum(price) as profit
from rooms as r 
inner join reservation_rooms as rr on rr.room_id = r.room_id
group by room_type

union all
select service_name as what, sum(cost) as profit
from services as s
inner join guest_services as gs on s.service_id = gs.service_id
group by s.service_id
having profit > 0

union 
select "Total", get_total_profit() as profit;

#3
select floor(avg(rating)) as average_rating,
 (select count(*) from reviews where rating > 3) as good_reviews,
 (select count(*) from reviews where rating <= 3) as bad_reviews
from reviews;

#4
select service_name, count(gs.service_id) as users_count
from services as s 
inner join guest_services as gs on s.service_id = gs.service_id
group by gs.service_id
order by users_count;

#5
select reservation_id, timestampdiff(hour, curdate(), check_out) as hours_til_check_out
from reservation_rooms
having hours_til_check_out between 0 and 24
order by hours_til_check_out;

#6
select concat(e.first_name, " ", e.last_name) as full_name, e.job as job, sum(cost) as profit 
from services as s inner join employees as e on e.employee_id = s.employee_id
group by s.employee_id
order by profit desc;

#7
select concat(g.first_name , " ", g.last_name) as guest_name, 
count(distinct rg.reservation_id) as total_reservations
from guests as g 
inner join reservation_guests as rg on g.guest_id = rg.guest_id
group by g.guest_id
order by total_reservations desc;

#8
select month(reservation_date) as month, count(month(reservation_date)) as total_reservations
from reservations
group by month(reservation_date);

#9

#10