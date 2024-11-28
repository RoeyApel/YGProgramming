import  socket
import threading

PORT = 5050
SERVER = socket.gethostbyname(socket.gethostname())
ADDR = (SERVER, PORT)

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind(ADDR)

def start():
    server.listen(5)

    while True:
        conn, addr = server.accept()
        print(f"connected to {addr}")

        msg = conn.recv(2048).decode('utf-8')
        print(f"message from client: {msg}")

        if msg != "exit":
            conn.send("message received!")
        else:
            server.close()
            break


