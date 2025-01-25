
#links
1. Tkinter events binding - https://www.pythontutorial.net/tkinter/tkinter-event-binding/


### **Collaborative Whiteboard Tool: Detailed Plan**

#### **Overview**

A collaborative whiteboard tool where multiple users can draw, annotate, and brainstorm together in real time. The project will combine Flask for the backend, JSON files for data persistence, and customTkinter for the graphical user interface.

---

### **Key Features**

1. **Real-Time Collaboration**:
    
    - Multiple users can connect to a shared whiteboard and see each other's changes instantly.
    - Use WebSockets (Flask-SocketIO) for real-time updates.
2. **Drawing Tools**:
    
    - Basic drawing tools like pen, line, rectangle, circle, and eraser.
    - Color and brush size selection.
3. **Annotations**:
    
    - Add text notes or labels to the canvas.
    - Ability to move or resize annotations.
4. **User Management**:
    
    - Allow users to set a nickname when joining a session.
    - Differentiate user actions by assigning unique colors to each user.
5. **Session Management**:
    
    - Create new whiteboard sessions with unique session IDs.
    - Join existing sessions using session IDs.
6. **Data Persistence**:
    
    - Save the whiteboard state as a JSON file for recovery or future editing.
    - Load previously saved sessions from JSON files.
7. **Chat Support**:
    
    - Add an integrated chat feature for real-time communication between collaborators.
8. **Exporting Canvas**:
    
    - Export the whiteboard as an image file (e.g., PNG).

---

### **Technical Architecture**

#### **1. Backend (Flask)**

- **Flask-SocketIO**: Real-time communication between users.
- **APIs**:
    - Create and manage sessions.
    - Save and load whiteboard data (JSON files).
- **WebSocket Events**:
    - Handle drawing updates, user connections, and chat messages.

#### **2. Data Management**

- **JSON Files**:
    - Store canvas states, including shapes, text, colors, and positions.
    - Save user actions and session metadata.

#### **3. GUI (customTkinter)**

- **Canvas for Drawing**:
    - Create a drawing area using `tkinter.Canvas`.
    - Implement tools for shapes, freehand drawing, and text.
- **Toolbar**:
    - Buttons for drawing tools, color picker, brush size, and eraser.
- **Session Management**:
    - GUI elements to input session ID and nickname.
- **Chat Panel**:
    - Chat window to send and receive messages.

#### **4. Real-Time Synchronization**

- **Flask-SocketIO**: Handle broadcast messages to update the canvas for all connected users.
- Events:
    - **Drawing Events**: Synchronize drawing actions in real-time.
    - **User Actions**: Notify others when a new user joins or leaves.
    - **Chat Messages**: Send and receive chat updates.

---
### **Development Plan (8 Weeks)**

1. **Week 1-2**:
    
    - Set up the Flask backend and Flask-SocketIO for real-time updates.
    - Implement basic WebSocket events for user connections and canvas updates.
2. **Week 3-4**:
    
    - Develop the customTkinter GUI:
        - Add the drawing canvas and toolbar.
        - Integrate session management and chat panel.
3. **Week 5**:
    
    - Implement saving and loading whiteboard states using JSON files.
4. **Week 6**:
    
    - Add advanced tools (e.g., shapes, text annotations) and user-specific features (e.g., unique colors).
5. **Week 7**:
    
    - Test the app for real-time performance with multiple users.
    - Optimize WebSocket handling and backend performance.
6. **Week 8**:
    
    - Add polish, like exporting to image files.
    - Final testing and debugging.

---

### **Technologies to Learn**

- Flask & Flask-SocketIO (for backend and real-time updates).
- customTkinter (for the GUI).
- JSON file handling (for data persistence).
- WebSockets (for live updates).