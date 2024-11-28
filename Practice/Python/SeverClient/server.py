import socket
import threading

from MyGUI import MyGUI


class Server:
    def __init__(self):
        self.myGUI = MyGUI(self, "server")

        self.HOST = socket.gethostbyname(socket.gethostname())
        self.PORT = 5050
        self.ADDR = (self.HOST, self.PORT)

        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.bind(self.ADDR)

        print("[STARTING] Server is starting...")
        self.start()

    def onClose(self):
        self.server.close()

    def handleClient(self, conn, addr):
        print(f"[NEW CONNECTION] {addr} connected.")
        while True:
            try:
                msg = conn.recv(2048).decode('utf-8')
                if not msg:
                    break
                print(f"[{addr}] {msg}")
                conn.sendall(f"Server received: {msg}".encode('utf-8'))
            except ConnectionResetError:
                print(f"[DISCONNECTED] {addr}")
                break
        conn.close()

    def start(self):
        self.server.listen()
        print(f"[LISTENING] Server is listening on {self.HOST}:{self.PORT}")
        while True:
            conn, addr = self.server.accept()
            thread = threading.Thread(target=self.handleClient, args=(conn, addr))
            thread.start()
            print(f"[ACTIVE CONNECTIONS] {threading.active_count() - 1}")


myServer = Server()
