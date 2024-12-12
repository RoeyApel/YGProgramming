import socket
import threading


def get_user_input():
    msg = input("Message: ")
    while len(msg) == 0:
        msg = input("Message: ")
    return msg


def handle_responses(client):
    while True:
        response = client.recv(2048).decode()
        print(f"\n{response}")


if __name__ == "__main__":
    HOST = "192.168.56.1"
    PORT = 5050

    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect((HOST, PORT))
    thread = threading.Thread(target=handle_responses, args=(client,))
    thread.start()

    while True:
        msg = get_user_input()
        if msg.lower() == "exit":
            client.sendall(msg.encode())
            client.close()
            break
        client.sendall(msg.encode())

