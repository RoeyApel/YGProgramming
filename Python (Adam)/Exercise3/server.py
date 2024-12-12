import socket
import threading


def send_all_clients(addr: tuple, msg: str):
    id = addr[1] % 10
    for client in clients:
        client.sendall(f"user-{id}: {msg}".encode())


def handle_client(client: socket.socket, addr: tuple):
    while True:
        msg = client.recv(2048).decode()
        if msg == "exit":
            client.close()
            clients.remove(client)
            if len(clients) == 0:
                server.close()

        print(msg)
        send_all_clients(addr, msg)


if __name__ == "__main__":
    HOST = socket.gethostbyname(socket.gethostname())
    PORT = 5050

    clients = []
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind((HOST, PORT))

    server.listen(5)

    while True:
        try:
            client, addr = server.accept()
            thread = threading.Thread(target=handle_client, args=(client, addr))
            thread.start()
            clients.append(client)
        except:
            print("error")
            break
