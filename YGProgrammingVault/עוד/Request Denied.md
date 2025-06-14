1. JavaCodingPack-0.4.2 + visual studio code extention (Extension Pack for Java)
	- In order to write java in visual studio code.

2. visual studio - desktop development c++
	- In order to write c in visual studio.

4. visual studio code extention (live server)
	- For front development
	
1. visual studio code extentions (code runner and C/C++ Extension Pack) + MinGW - Minimalist GNU for Windows + add to enviromaental veriables to path: C:\MinGW\bin 
	```cpp
Z:\myDown\MinGW\bin
gcc -v
```
	- In order to write c in visual studio code. 

import random

def guess_the_number():
    # Generate a random number between 1 and 100
    number_to_guess = random.randint(1, 100)
    attempts = 0

    print("Welcome to Guess the Number!")
    print("I have chosen a number between 1 and 100. Can you guess it?")
    
    while True:
        try:
            # Ask the user for their guess
            guess = int(input("Enter your guess: "))
            attempts += 1

            # Check if the guess is too high, too low, or correct
            if guess < number_to_guess:
                print("Too low! Try again.")
            elif guess > number_to_guess:
                print("Too high! Try again.")
            else:
                print(f"Congratulations! You've guessed the number in {attempts} attempts.")
                break
        except ValueError:
            print("Please enter a valid integer.")

# Run the game
guess_the_number()
