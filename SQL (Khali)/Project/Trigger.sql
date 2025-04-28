CREATE DEFINER=`root`@`localhost` TRIGGER `reservations_BEFORE_INSERT` BEFORE INSERT ON `reservations` FOR EACH ROW BEGIN
	if new.reservation_date is null then
        set new.reservation_date = curdate();
    end if;
END