import socket
import threading

def handle_client(client: socket.socket, addr: tuple):
    while True:
        data = client.recv(2048).decode()
        if data == "exit":
            client.close()
            clients.remove(client)
            if len(clients) == 0:
                server.close()
            break
        print(data)
        client.sendall("Thanks!".encode())



if __name__ == "__main__":
    HOST_IP = socket.gethostbyname(socket.gethostname())
    PORT = 5050
    ADDR = (HOST_IP, PORT)

    clients = []
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind(ADDR)

    server.listen(5)

    while True:
        try:
            client, addr = server.accept()
            thread = threading.Thread(target=handle_client, args=(client, addr))
            clients.append(client)
            thread.start()

        except:
            print("client disconect")
            break
