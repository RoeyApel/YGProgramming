CREATE DEFINER=`root`@`localhost` TRIGGER `guests_AFTER_DELETE` AFTER DELETE ON `guests` FOR EACH ROW BEGIN
delete from reviews
where guest_id = old.guest_id;
END