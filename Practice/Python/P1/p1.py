from io import TextIOWrapper


def isPoly(string):
    return string == string[::-1]


def count_file_content(file):
    # Read the entire content of the file
    content = file.read()

    # Count characters, words, and lines
    char_count = len(content)
    word_count = len(content.split())
    line_count = len(content.splitlines())

    # Print the results
    print(f"Characters: {char_count}")
    print(f"Words: {word_count}")
    print(f"Lines: {line_count}")


def getSortedNoD(lst):
    return list(sorted(set(lst)))


l = ["you", "Tom", "hello"]
with open("story.txt", "w+") as file:
    file.write(" ".join(l))
