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

### **Additional Tables Options for the Database**
#### **1. Payment Information**

To handle payments for bookings and services.

- **Columns:**
    - `payment_id` (Primary Key)
    - `booking_id` (Foreign Key)
    - `amount`
    - `payment_date`
    - `payment_method` (e.g., Credit Card, Cash, Online)
    - `status` (e.g., Paid, Pending, Failed)

---

#### **2. Amenities**

To manage the amenities provided by the hotel.

- **Columns:**
    - `amenity_id` (Primary Key)
    - `amenity_name` (e.g., WiFi, Swimming Pool, Gym)
    - `description`
    - `availability` (e.g., Yes/No)

---

#### **3. Room Maintenance**

To track maintenance schedules for rooms.

- **Columns:**
    - `maintenance_id` (Primary Key)
    - `room_id` (Foreign Key)
    - `issue_reported`
    - `report_date`
    - `repair_date`
    - `status` (e.g., Pending, In Progress, Resolved)
    - `maintenance_cost`

---

#### **4. Inventory**

To manage the stock of items used in the hotel.

- **Columns:**
    - `item_id` (Primary Key)
    - `item_name` (e.g., Towels, Bedsheets, Toiletries)
    - `quantity_in_stock`
    - `restock_date`
    - `supplier_name`

---

#### **5. Employee Schedule**

To organize employee shifts.

- **Columns:**
    - `schedule_id` (Primary Key)
    - `staff_id` (Foreign Key)
    - `shift_date`
    - `shift_time` (e.g., Morning, Afternoon, Night)
    - `role` (e.g., Receptionist, Housekeeper)

---

#### **6. Feedback**

To record customer reviews and feedback.

- **Columns:**
    - `feedback_id` (Primary Key)
    - `guest_id` (Foreign Key)
    - `booking_id` (Foreign Key)
    - `rating` (1-5 stars)
    - `comments`
    - `feedback_date`

---

#### **7. Promotions**

To manage discounts and promotional offers.

- **Columns:**
    - `promotion_id` (Primary Key)
    - `promotion_name`
    - `discount_percentage`
    - `start_date`
    - `end_date`
    - `applicable_rooms` (e.g., Suite, Deluxe)

---

#### **8. Event Management**

To handle events hosted at the hotel (e.g., weddings, conferences).

- **Columns:**
    - `event_id` (Primary Key)
    - `event_name`
    - `guest_id` (Foreign Key)
    - `event_date`
    - `venue`
    - `number_of_guests`
    - `total_cost`

---

#### **9. Dining Services**

To track dining orders placed by guests.

- **Columns:**
    - `order_id` (Primary Key)
    - `guest_id` (Foreign Key)
    - `room_id` (Foreign Key)
    - `order_date`
    - `order_details` (e.g., Food Items)
    - `total_cost`
    - `status` (e.g., Delivered, Pending)

---

#### **10. Loyalty Program**

To manage loyal customers and their rewards.

- **Columns:**
    - `loyalty_id` (Primary Key)
    - `guest_id` (Foreign Key)
    - `loyalty_points`
    - `tier` (e.g., Bronze, Silver, Gold)
    - `last_updated`

---

#### **11. Transportation Services**

To manage transportation arrangements for guests.

- **Columns:**
    - `transport_id` (Primary Key)
    - `guest_id` (Foreign Key)
    - `pickup_location`
    - `dropoff_location`
    - `vehicle_type` (e.g., Car, Van)
    - `price`
    - `status` (e.g., Scheduled, Completed)

---

#### **12. Room Packages**

To manage bundled offerings (e.g., honeymoon package, family package).

- **Columns:**
    - `package_id` (Primary Key)
    - `package_name`
    - `included_amenities`
    - `price`
    - `validity_period`

---

#### **13. Reservation Waitlist**

To manage guests on a waitlist when rooms are fully booked.

- **Columns:**
    - `waitlist_id` (Primary Key)
    - `guest_id` (Foreign Key)
    - `room_type`
    - `request_date`
    - `status` (e.g., Pending, Confirmed)

---

#### **14. Room Upgrades**

To manage room upgrade requests.

- **Columns:**
    - `upgrade_id` (Primary Key)
    - `guest_id` (Foreign Key)
    - `current_room_id` (Foreign Key)
    - `requested_room_type`
    - `upgrade_cost`
    - `status` (e.g., Approved, Denied)