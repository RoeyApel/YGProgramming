
import socket
import threading

def get_user_input():
    msg = input("Commend: ")
    while len(msg) == 0:
        msg = input("Commend: ")
    return msg


def send_all_clients(commend: str):
    for client in clients:
        client.sendall(commend.encode())


def handle_client(client: socket.socket, addr: tuple):
    while True:
        msg = client.recv(2048).decode()
        if msg == "exit":
            client.close()
            clients.remove(client)
            if len(clients) == 0:
                server.close()

        print(msg)


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

            commend = get_user_input()
            send_all_clients(commend.encode())
        except:
            print("error")
            break
