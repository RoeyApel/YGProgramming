import socket


def getUserInput():
    msg = input("Message: ")
    while len(msg) == 0:
        msg = input("Message idiot: ")
    return msg


if __name__ == "__main__":
    HOST_IP = "192.168.56.1"
    PORT = 5050
    ADDR = (HOST_IP, PORT)

    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect(ADDR)

    while True:
        msg = getUserInput()
        if msg == "exit":
            client.sendall(msg.encode())
            client.close()
            break
        client.sendall(msg.encode())
        response = client.recv(2048).decode()
        print(f"server ({HOST_IP}): {response}")
