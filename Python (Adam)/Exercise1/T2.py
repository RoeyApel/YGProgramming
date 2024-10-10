import random
import sys

rand = random.randint(1, 1000)
tries = 1
guess = 0

print("welcome")
userInput = input("Wanna continue from previous save? (no/yes): ")
if "yes" in userInput.lower():
    saveFile = open("save.txt", "r")
    data = saveFile.readlines()
    rand = int(data[0].strip())
    tries = int(data[1].strip())
    saveFile.close()
    print("jtyhjhjtyjj")

while guess != rand:
    guess = int(input("Guess: "))
    if guess > rand:
        print("lower!")
        tries += 1
    if guess < rand:
        print("higher!")
        tries += 1
    userInput = input("Wanna Quit? (no/yes): ")
    if userInput.lower() == "yes":
        saveFile = open("save.txt", "w+")
        saveFile.writelines([str(tries) + "\n", str(rand) + "\n"])
        saveFile.close()
        sys.exit()

print(f"well done you guessed it in {tries} tries!")
