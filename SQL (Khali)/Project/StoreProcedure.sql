CREATE DEFINER=`root`@`localhost` PROCEDURE `get_occupied_rooms`(in p_date date)
BEGIN

select r.room_id, r.room_number, r.room_type, r.price 
from rooms r
inner join reservation_rooms rr on r.room_id = rr.room_id
where p_date >= date(rr.check_in) and p_date < date(rr.check_out);

END