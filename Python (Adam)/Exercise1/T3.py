import os

ipConfigLines = os.popen("ipconfig").read().split("\n")

for line in ipConfigLines:
    if "IPv4" in line:
        ip = line[line.find(":") + 2:]
        print(ip)
