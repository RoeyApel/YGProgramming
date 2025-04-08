CREATE DATABASE Hotel_DB;
USE Hotel_DB;

CREATE TABLE guests (
    guest_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(10),
    address VARCHAR(100) 
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    job VARCHAR(50) NOT NULL,
    phone VARCHAR(10),
    hire_date DATETIME NOT NULL
);

CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number INT UNIQUE NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE reservations (
	reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_date DATETIME,
	employee_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE services (
    service_id INT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(100) NOT NULL,
    service_description TEXT,
    cost DECIMAL(10,2) NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE review_targets (
    target_id INT PRIMARY KEY,
    target_name VARCHAR(50) NOT NULL
);

CREATE TABLE reviews (
    review_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_id INT NOT NULL,
    rating INT,
    content TEXT,
    review_date DATETIME,
    target_id INT,
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),
    FOREIGN KEY (target_id) REFERENCES review_targets(target_id)
);

CREATE TABLE reservation_guests (
    reservation_id INT NOT NULL,
    guest_id INT NOT NULL, 
    room_id INT NOT NULL,   
    is_payer_guest BOOLEAN NOT NULL,
    PRIMARY KEY (reservation_id, guest_id),
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id),
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

CREATE TABLE reservation_rooms (
    reservation_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in DATETIME, 
    check_out DATETIME, 
    PRIMARY KEY (reservation_id, room_id),
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

CREATE TABLE guest_services (
    guest_id INT NOT NULL,
    service_id INT NOT NULL,
    PRIMARY KEY (guest_id, service_id),
    FOREIGN KEY (guest_id) REFERENCES guests(guest_id),
    FOREIGN KEY (service_id) REFERENCES services(service_id)
);
