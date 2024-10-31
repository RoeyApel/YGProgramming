import random
import sys

rand = random.randint(1, 1000)
tries = 0
guess = 0

print("welcome")
userInput = input("Wanna continue from previous save? (no/yes): ")
if "yes" in userInput.lower():
    saveFile = open("save.txt", "r")
    data = saveFile.readlines()
    rand = int(data[0])
    tries = int(data[1])
    saveFile.close()

while guess != rand:
    guess = int(input("Guess(or -1 to quit): "))
    if guess > rand:
        print("lower!")
        tries += 1
    elif guess < rand:
        print("higher!")
        tries += 1
    elif guess == -1:
        saveFile = open("save.txt", "w+")
        saveFile.writelines(f"{rand}\n {tries}")
        saveFile.close()
        sys.exit()


print(f"well done you guessed it in {tries} tries!")

