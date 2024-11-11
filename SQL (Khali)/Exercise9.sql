select C.country_name, L.location_id, R.region_name
from countries C, locations L, regions R
where C.country_id = L.country_id and R.region_id = C.region_id
order by L.location_id;