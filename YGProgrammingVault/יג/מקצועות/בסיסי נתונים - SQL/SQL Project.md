### **Hotel Management System**

A hotel managing rooms, guests, bookings, and staff.

#### **Tables for the Database**

1. **Guests**
    
    - `guest_id` (Primary Key)
    - `first_name`
    - `last_name`
    - `email`
    - `phone_number`
    - `address`
2. **Rooms**
    
    - `room_id` (Primary Key)
    - `room_number`
    - `type` (e.g., Single, Double, Suite)
    - `price_per_night`
    - `status` (e.g., Available, Occupied)
3. **Bookings**
    
    - `booking_id` (Primary Key)
    - `guest_id` (Foreign Key)
    - `room_id` (Foreign Key)
    - `check_in_date`
    - `check_out_date`
    - `total_amount`
4. **Staff**
    
    - `staff_id` (Primary Key)
    - `first_name`
    - `last_name`
    - `email`
    - `role` (e.g., Manager, Housekeeping)
    - `phone_number`
5. **Services**
    
    - `service_id` (Primary Key)
    - `booking_id` (Foreign Key)
    - `service_name` (e.g., Room Service, Laundry)
    - `price`
    - `service_date`

#### **Relationships**

- A **guest** can have multiple **bookings**, and a **booking** can include multiple **services**.
- **Rooms** are assigned to **bookings** and their status updates dynamically.

----
## 1. Database Tables and Their Structure

### **Guests Table**

This table stores information about every guest staying at the hotel.

sql

CopyEdit

`CREATE TABLE Guests (     guest_id INT AUTO_INCREMENT PRIMARY KEY,     first_name VARCHAR(50) NOT NULL,     last_name VARCHAR(50) NOT NULL,     email VARCHAR(100) UNIQUE,     phone VARCHAR(20),     address VARCHAR(255),     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP );`

- **Usage:** Referenced by the **Reservations** table to link a booking to a specific guest.

---

### **Rooms Table**

This table contains details about the hotel rooms.

sql

CopyEdit

`CREATE TABLE Rooms (     room_id INT AUTO_INCREMENT PRIMARY KEY,     room_number VARCHAR(10) UNIQUE NOT NULL,     room_type VARCHAR(50),     capacity INT,     price DECIMAL(10,2),     status ENUM('available', 'occupied', 'maintenance') DEFAULT 'available' );`

- **Usage:** Linked to reservations via the **ReservationRooms** join table.

---

### **Employees Table**

This table holds information about hotel employees.

sql

CopyEdit

`CREATE TABLE Employees (     employee_id INT AUTO_INCREMENT PRIMARY KEY,     first_name VARCHAR(50) NOT NULL,     last_name VARCHAR(50) NOT NULL,     position VARCHAR(50),     email VARCHAR(100) UNIQUE,     phone VARCHAR(20),     hire_date DATE );`

- **Usage:** Referenced by the **Services** table to record which employee provided a particular service (if applicable).

---

### **Reservations Table**

This table records reservation details and links a reservation to a guest. (Note: Room associations are handled in the join table.)

sql

CopyEdit

`CREATE TABLE Reservations (     reservation_id INT AUTO_INCREMENT PRIMARY KEY,     guest_id INT NOT NULL,     check_in_date DATE NOT NULL,     check_out_date DATE NOT NULL,     number_of_guests INT,     status ENUM('booked', 'checked_in', 'checked_out', 'cancelled') DEFAULT 'booked',     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,     FOREIGN KEY (guest_id) REFERENCES Guests(guest_id) );`

- **Usage:** Each reservation is linked to one guest and may include multiple rooms and additional services.

---

### **ReservationRooms Table (Join Table)**

This join table establishes a many-to-many relationship between **Reservations** and **Rooms**.

sql

CopyEdit

`CREATE TABLE ReservationRooms (     reservation_room_id INT AUTO_INCREMENT PRIMARY KEY,     reservation_id INT NOT NULL,     room_id INT NOT NULL,     FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id),     FOREIGN KEY (room_id) REFERENCES Rooms(room_id),     UNIQUE (reservation_id, room_id)  -- Prevents duplicate room entries for the same reservation );`

- **Usage:** Allows one reservation to include multiple rooms and each room to be associated with multiple reservations over time.

---

### **Services Table**

This table records additional services (e.g., room service, spa treatments) provided during a guest’s stay. It maintains a one-to-many relationship with **Reservations**.

sql

CopyEdit

`CREATE TABLE Services (     service_id INT AUTO_INCREMENT PRIMARY KEY,     reservation_id INT NOT NULL,     employee_id INT,  -- Optional: employee who provided the service     service_name VARCHAR(100) NOT NULL,     description TEXT,     cost DECIMAL(10,2),     service_date DATE,     FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id),     FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) );`

- **Usage:** Each service record is directly linked to one reservation. Optionally, it also records which employee delivered the service.

---

### **Reviews Table**

This table captures guest feedback related to their reservation.

sql

CopyEdit

`CREATE TABLE Reviews (`
    `review_id INT AUTO_INCREMENT PRIMARY KEY,`
    `reservation_id INT NOT NULL,`
    `rating TINYINT NOT NULL,  -- e.g., a rating from 1 to 5`
    `comment TEXT,`
    `review_date DATE NOT NULL,`
    `FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id)`
    `-- Optionally, add UNIQUE(reservation_id) to enforce one review per reservation`
`);`


- **Usage:** Links guest feedback to a specific reservation.

---

## 2. Explanation of Relationships

1. **Guests ↔ Reservations (One-to-Many):**
    
    - **What It Means:** One guest can have multiple reservations.
    - **Implementation:** The **Reservations** table includes a foreign key (`guest_id`) referencing the **Guests** table.
2. **Rooms ↔ Reservations (Many-to-Many):**
    
    - **What It Means:** A reservation can include multiple rooms (e.g., booking several rooms at once), and a single room can appear in multiple reservations at different times.
    - **Implementation:** The **ReservationRooms** join table links `reservation_id` (from **Reservations**) and `room_id` (from **Rooms**).
3. **Reservations ↔ Services (One-to-Many):**
    
    - **What It Means:** Each reservation can have multiple additional service records (such as several room service orders), but each service record is associated with one reservation.
    - **Implementation:** The **Services** table includes a foreign key (`reservation_id`) referencing **Reservations**.
4. **Employees ↔ Services (One-to-Many, Optional):**
    
    - **What It Means:** An employee may provide multiple services across different reservations.
    - **Implementation:** The **Services** table optionally includes an `employee_id` that references **Employees**.
5. **Reservations ↔ Reviews (One-to-One/One-to-Many):**
    
    - **What It Means:** Each review is tied to a reservation to capture guest feedback.
    - **Implementation:** The **Reviews** table includes a foreign key (`reservation_id`) referencing **Reservations**.

---

## 3. How to Use This Schema

- **Creating a New Reservation:**  
    Insert a record into the **Reservations** table with the guest’s `guest_id`, check-in/check-out dates, and other details. For each room booked under that reservation, insert a record into the **ReservationRooms** table linking the reservation with the corresponding `room_id`.
    
- **Recording Service Usage:**  
    When a guest uses an additional service, insert a record into the **Services** table linked to the appropriate `reservation_id`. Optionally, record the `employee_id` if a staff member provided the service.
    
- **Collecting Guest Reviews:**  
    After their stay, guest feedback is recorded in the **Reviews** table linked to the respective `reservation_id`.
    
- **Managing Room Availability:**  
    The **Rooms** table tracks each room's status (available, occupied, or under maintenance). Application logic should prevent overlapping bookings for the same room.
---
---
## 1. Database Tables and Their Structure

### **Guests Table**

This table stores information about every guest staying at the hotel.

sql

CopyEdit

`CREATE TABLE Guests (     guest_id INT AUTO_INCREMENT PRIMARY KEY,     first_name VARCHAR(50) NOT NULL,     last_name VARCHAR(50) NOT NULL,     email VARCHAR(100) UNIQUE,     phone VARCHAR(20),     address VARCHAR(255),     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP );`

- **Key Field:** `guest_id` uniquely identifies each guest.
- **Usage:** Referenced by the Reservations table to link a booking to a specific guest.

---

### **Rooms Table**

This table contains details about the hotel rooms.

sql

CopyEdit

`CREATE TABLE Rooms (     room_id INT AUTO_INCREMENT PRIMARY KEY,     room_number VARCHAR(10) UNIQUE NOT NULL,     room_type VARCHAR(50),     capacity INT,     price DECIMAL(10,2),     status ENUM('available', 'occupied', 'maintenance') DEFAULT 'available' );`

- **Key Field:** `room_id` uniquely identifies each room.
- **Usage:** The Reservations table uses `room_id` to assign a specific room to a reservation.

---

### **Employees Table**

This table holds information about hotel employees.

sql

CopyEdit

`CREATE TABLE Employees (     employee_id INT AUTO_INCREMENT PRIMARY KEY,     first_name VARCHAR(50) NOT NULL,     last_name VARCHAR(50) NOT NULL,     position VARCHAR(50),     email VARCHAR(100) UNIQUE,     phone VARCHAR(20),     hire_date DATE );`

- **Key Field:** `employee_id` uniquely identifies each employee.
- **Usage:** This table is referenced in the **Reservation_Services** table to record which employee provided a particular service (if applicable).

---

### **Reservations Table**

This table records reservation details, linking guests to rooms.

sql

CopyEdit

`CREATE TABLE Reservations (     reservation_id INT AUTO_INCREMENT PRIMARY KEY,     guest_id INT NOT NULL,     room_id INT NOT NULL,     check_in_date DATE NOT NULL,     check_out_date DATE NOT NULL,     number_of_guests INT,     status ENUM('booked', 'checked_in', 'checked_out', 'cancelled') DEFAULT 'booked',     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,     FOREIGN KEY (guest_id) REFERENCES Guests(guest_id),     FOREIGN KEY (room_id) REFERENCES Rooms(room_id) );`

- **Key Fields and Relationships:**
    - **`guest_id`:** Associates each reservation with one guest.
    - **`room_id`:** Assigns a specific room to the reservation.

---

### **Reviews Table**

This table captures guest feedback related to their reservation.

sql

CopyEdit

`CREATE TABLE Reviews (     review_id INT AUTO_INCREMENT PRIMARY KEY,     reservation_id INT NOT NULL,     rating TINYINT NOT NULL,  -- e.g., a rating from 1 to 5     comment TEXT,     review_date DATE NOT NULL,     FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id)     -- Optionally, add UNIQUE(reservation_id) to enforce one review per reservation );`

- **Key Field:** `review_id` uniquely identifies each review.
- **Usage:** Links guest feedback to a specific reservation.

---

### **ServiceCatalog Table**

This table lists all the available service types offered by the hotel.

sql

CopyEdit

`CREATE TABLE ServiceCatalog (     service_catalog_id INT AUTO_INCREMENT PRIMARY KEY,     service_name VARCHAR(100) NOT NULL,     description TEXT,     base_cost DECIMAL(10,2) );`

- **Key Field:** `service_catalog_id` uniquely identifies each service type.
- **Usage:** Defines the available services that can be used in any reservation.

---

### **Reservation_Services Table**

This join table links reservations with services from the catalog, effectively modeling a many-to-many relationship. It also optionally records which employee provided the service and any specific details for that service usage.

sql

CopyEdit

`CREATE TABLE Reservation_Services (     reservation_service_id INT AUTO_INCREMENT PRIMARY KEY,     reservation_id INT NOT NULL,     service_catalog_id INT NOT NULL,     employee_id INT,  -- Optional: employee who provided the service     actual_cost DECIMAL(10,2),     service_date DATE,     FOREIGN KEY (reservation_id) REFERENCES Reservations(reservation_id),     FOREIGN KEY (service_catalog_id) REFERENCES ServiceCatalog(service_catalog_id),     FOREIGN KEY (employee_id) REFERENCES Employees(employee_id) );`

- **Key Field:** `reservation_service_id` uniquely identifies each service usage record.
- **Usage:**
    - **`reservation_id`:** Links the service usage to a specific reservation.
    - **`service_catalog_id`:** Connects the service to the predefined service catalog entry.
    - **`employee_id`:** Optionally records which employee provided the service.

---

## 2. Explanation of Relationships

1. **Guests ↔ Reservations (One-to-Many):**
    
    - **What It Means:** A single guest (identified by `guest_id` in the Guests table) can have multiple reservations over time. Each reservation is linked to exactly one guest.
    - **Implementation:** The Reservations table includes a foreign key (`guest_id`) referencing the Guests table.
2. **Rooms ↔ Reservations (One-to-Many):**
    
    - **What It Means:** Each room (identified by `room_id` in the Rooms table) can be booked many times over different periods. Every reservation is associated with one specific room.
    - **Implementation:** The Reservations table includes a foreign key (`room_id`) referencing the Rooms table.
3. **Reservations ↔ Reviews (One-to-One/One-to-Many):**
    
    - **What It Means:** Each review is associated with a reservation, capturing guest feedback for that stay. Depending on your requirements, you might enforce one review per reservation or allow multiple.
    - **Implementation:** The Reviews table includes a foreign key (`reservation_id`) referencing the Reservations table.
4. **Reservations ↔ Reservation_Services (Many-to-Many via Join Table):**
    
    - **What It Means:** A single reservation can include multiple service usages (for example, multiple room service orders or spa treatments), and each service from the catalog can be applied to many reservations.
    - **Implementation:**
        - The **Reservation_Services** table links a reservation to a service from the **ServiceCatalog** using foreign keys.
        - This join table enables tracking of each service instance during a guest’s stay, including any adjustments to cost or specific service dates.
5. **ServiceCatalog ↔ Reservation_Services (One-to-Many):**
    
    - **What It Means:** A predefined service (such as “Room Service”) can appear in many reservation service records.
    - **Implementation:** The **Reservation_Services** table includes a foreign key (`service_catalog_id`) that references the **ServiceCatalog** table.
6. **Employees ↔ Reservation_Services (One-to-Many, Optional):**
    
    - **What It Means:** An employee (identified by `employee_id` in the Employees table) may provide multiple services. Each record in **Reservation_Services** can optionally record which employee delivered that service.
    - **Implementation:** The **Reservation_Services** table includes an optional foreign key (`employee_id`) that references the Employees table.

---

## 3. How to Use This Schema

- **Creating a New Reservation:**  
    Insert a record into the **Reservations** table with the guest’s `guest_id` and the desired `room_id`, along with check-in and check-out dates.
    
- **Recording Service Usage:**  
    When a guest uses an extra service during their stay, first ensure the service exists in the **ServiceCatalog**. Then, insert a record into **Reservation_Services** linking the relevant `reservation_id` with the corresponding `service_catalog_id`. Optionally, record the `employee_id` if an employee provided the service, along with the actual cost and service date.
    
- **Collecting Guest Reviews:**  
    After their stay, a guest’s feedback is recorded in the **Reviews** table, linked to the specific reservation.
    
- **Managing Room Availability:**  
    The **Rooms** table tracks the status of each room (available, occupied, or under maintenance) to help avoid double-booking.
    
- **Employee Service Tracking:**  
    The link between **Employees** and **Reservation_Services** allows you to monitor which staff members are delivering additional services, aiding in performance tracking and accountability.