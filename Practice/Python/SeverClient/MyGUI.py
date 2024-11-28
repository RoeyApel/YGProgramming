import tkinter as tk
from socket import socket


class MyGUI:
    def __init__(self, mysocket, name):
        self.mysocket = mysocket
        self.name = name
        self.root = tk.Tk()
        self.root.title("TextMe.com")
        self.root.geometry("550x600")
        self.root.protocol("WM_DELETE_WINDOW", self.onClosing)

        self.sendLabel = tk.Label(self.root, text=f"Send A Message Here! {self.name}", font=("Arial", 20))
        self.sendLabel.pack(pady=30)

        self.textArea = tk.Text(self.root, width=35, height=3, font=("Arial", 18))
        self.textArea.pack(pady=15)

        self.sendBtn = tk.Button(self.root, text="Send", font=("Arial", 20), command=self.sendMessage)
        self.sendBtn.pack(pady=15)

        self.receivedLabel = tk.Label(self.root, text="Receive A Message Here!", font=("Arial", 20))
        self.receivedLabel.pack(pady=30)

        self.receivedMessageText = tk.StringVar()
        self.receivedMessageText.set("NoN")
        self.receivedMessageLabel = tk.Label(self.root, textvariable=self.receivedMessageText, font=("Arial", 20))
        self.receivedMessageLabel.pack(pady=30)

        self.root.mainloop()

    def setText(self, msg):
        self.receivedMessageText.set(msg)

    def getText(self):
        return self.textArea.get("1.0", tk.END)

    def sendMessage(self):
        self.mysocket.sendMessage()

    def onClosing(self):
        self.mysocket.onClose()


file = open("Total_Fail2.txt", "w")
file.write("kill me")
file.close()
