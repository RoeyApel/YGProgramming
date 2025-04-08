CREATE DEFINER=`root`@`localhost` FUNCTION `get_total_profit`() RETURNS decimal(10,2)
    DETERMINISTIC
BEGIN

declare total_rooms_profit decimal(10,2);
declare total_services_profit decimal(10,2);

select sum(price) into total_rooms_profit
from rooms as r 
inner join reservation_rooms as rr on r.room_id = rr.room_id;

select sum(cost) into total_services_profit
from services as s
inner join guest_services as gs on s.service_id = gs.service_id;

RETURN total_rooms_profit + total_services_profit;
END