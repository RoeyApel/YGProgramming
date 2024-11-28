import socket

from MyGUI import MyGUI
from server import myServer


class Client:

    def __init__(self):
        self.myGUI = MyGUI(self, "client")

        self.HOST = socket.gethostbyname(socket.gethostname())
        self.PORT = 5050
        self.ADDR = (self.HOST, self.PORT)

        self.client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.client.connect(self.ADDR)

        print(f"[CONNECTED] Connected to server at {self.HOST}:{self.PORT}")

    def sendMessage(self):
        msg = MyGUI.getText()
        self.client.sendall(msg.encode('utf-8'))
        response = self.client.recv(2048).decode('utf-8')
        MyGUI.setText(response)

    def onClose(self):
        self.client.close()


myClient = Client()
