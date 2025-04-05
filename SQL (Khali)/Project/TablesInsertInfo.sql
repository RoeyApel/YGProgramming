USE Hotel_DB;

INSERT INTO guests (first_name, last_name, email, phone, address) VALUES
('John', 'Doe', 'john.doe@example.com', '5551234567', '123 Main St'),
('Jane', 'Smith', 'jane.smith@example.com', '5552345678', '456 Oak Ave'),
('Alice', 'Johnson', 'alice.johnson@example.com', '5553456789', '789 Pine Rd'),
('Bob', 'Williams', 'bob.williams@example.com', '5554567890', '321 Maple Dr'),
('Carol', 'Brown', 'carol.brown@example.com', '5555678901', '654 Cedar Ln'),
('David', 'Jones', 'david.jones@example.com', '5556789012', '987 Birch Blvd'),
('Eve', 'Davis', 'eve.davis@example.com', '5557890123', '159 Spruce St'),
('Frank', 'Miller', 'frank.miller@example.com', '5558901234', '753 Elm St'),
('Grace', 'Wilson', 'grace.wilson@example.com', '5559012345', '852 Walnut Ave'),
('Henry', 'Moore', 'henry.moore@example.com', '5550123456', '951 Chestnut Rd');

INSERT INTO employees (first_name, last_name, job, phone, hire_date) VALUES
('Samantha', 'Lee', 'Manager', '5551112222', '2022-01-10 09:00:00'),
('Michael', 'Clark', 'Receptionist', '5552223333', '2021-05-15 08:30:00'),
('Linda', 'Walker', 'Housekeeping', '5553334444', '2020-07-20 07:45:00'),
('Robert', 'Hall', 'Chef', '5554445555', '2019-03-12 10:00:00'),
('Patricia', 'Allen', 'Concierge', '5555556666', '2023-02-01 11:15:00'),
('William', 'Young', 'Maintenance', '5556667777', '2018-11-05 06:30:00'),
('Barbara', 'Hernandez', 'Security', '5557778888', '2020-09-17 08:00:00'),
('James', 'King', 'Receptionist', '5558889999', '2021-12-25 09:15:00'),
('Elizabeth', 'Wright', 'Manager', '5559990000', '2017-08-08 08:45:00'),
('Charles', 'Lopez', 'Housekeeping', '5550001111', '2022-06-30 07:00:00');

INSERT INTO rooms (room_number, room_type, price, capacity) VALUES
(101, 'Single', 75.00, 1),
(102, 'Double', 120.00, 2),
(103, 'Suite', 250.00, 4),
(104, 'Single', 80.00, 1),
(105, 'Double', 130.00, 2),
(106, 'Suite', 260.00, 4),
(107, 'Single', 70.00, 1),
(108, 'Double', 125.00, 2),
(109, 'Suite', 255.00, 4),
(110, 'Double', 135.00, 2);

INSERT INTO reservations (reservation_date, employee_id) VALUES
('2023-04-01 14:00:00', 1),
('2023-04-02 15:30:00', 2),
('2023-04-03 12:00:00', 3),
('2023-04-04 16:45:00', 4),
('2023-04-05 10:15:00', 5),
('2023-04-06 11:00:00', 6),
('2023-04-07 09:30:00', 7),
('2023-04-08 13:20:00', 8),
('2023-04-09 17:10:00', 9),
('2023-04-10 08:50:00', 10);

INSERT INTO services (service_name, service_description, cost, employee_id) VALUES
('Room Cleaning', 'Daily room cleaning service', 25.00, 3),
('Laundry', 'Clothes washing and ironing', 15.00, 3),
('Breakfast', 'In-room breakfast service', 20.00, 2),
('Spa', 'Relaxing spa treatments', 50.00, 1),
('Airport Shuttle', 'Transport to/from the airport', 40.00, 5),
('Concierge', 'Personal assistance with bookings', 30.00, 5),
('Extra Towels', 'Provision of additional towels', 5.00, 7),
('Mini Bar', 'Restocking mini bar items', 10.00, 6),
('Room Service', 'In-room dining service', 35.00, 2),
('WiFi Setup', 'High-speed internet access setup', 0.00, 8);

INSERT INTO reviews (guest_id, rating, content, review_date) VALUES
(1, 5, 'Excellent service and clean rooms.', '2023-04-02 10:00:00'),
(2, 4, 'Great location and friendly staff.', '2023-04-03 11:00:00'),
(3, 3, 'Room was average; could be better.', '2023-04-04 12:00:00'),
(4, 5, 'Wonderful experience overall.', '2023-04-05 13:00:00'),
(5, 4, 'Comfortable stay with minor issues.', '2023-04-06 14:00:00'),
(6, 2, 'Not very clean, disappointed.', '2023-04-07 15:00:00'),
(7, 5, 'Amazing service and cozy rooms.', '2023-04-08 16:00:00'),
(8, 3, 'Okay stay, nothing exceptional.', '2023-04-09 17:00:00'),
(9, 4, 'Very good, would come back again.', '2023-04-10 18:00:00'),
(10, 5, 'Perfect experience and top-notch amenities.', '2023-04-11 19:00:00');

INSERT INTO reservation_guests (reservation_id, guest_id, room_id, is_payer_guest) VALUES
(1, 1, 1, TRUE),
(2, 2, 2, TRUE),
(3, 3, 3, TRUE),
(4, 4, 4, TRUE),
(5, 5, 5, TRUE),
(6, 6, 6, TRUE),
(7, 7, 7, TRUE),
(8, 8, 8, TRUE),
(9, 9, 9, TRUE),
(10, 10, 10, TRUE);

INSERT INTO reservation_rooms (reservation_id, room_id, check_in, check_out) VALUES
(1, 1, '2023-04-01 14:00:00', '2023-04-05 11:00:00'),
(2, 2, '2023-04-02 15:30:00', '2023-04-06 10:00:00'),
(3, 3, '2023-04-03 12:00:00', '2023-04-07 12:00:00'),
(4, 4, '2023-04-04 16:45:00', '2023-04-08 09:00:00'),
(5, 5, '2023-04-05 10:15:00', '2023-04-09 10:00:00'),
(6, 6, '2023-04-06 11:00:00', '2023-04-10 11:00:00'),
(7, 7, '2023-04-07 09:30:00', '2023-04-11 12:00:00'),
(8, 8, '2023-04-08 13:20:00', '2023-04-12 10:30:00'),
(9, 9, '2023-04-09 17:10:00', '2023-04-13 09:45:00'),
(10, 10, '2023-04-10 08:50:00', '2023-04-14 10:15:00');

INSERT INTO guest_services (guest_id, service_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);
