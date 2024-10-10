file = open("file.exercise.txt", "r+")

list = file.readlines()
urls = []

for line in list:
    if "http" in line:
        urls.append(line[line.find("http"):len(line) - 2])

for line in urls:
    print(line)