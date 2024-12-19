import tkinter as tk
from tkinter import messagebox

def poper():
    result = messagebox.askyesno("Question", "Are you bored?");
    if result:
        text = ent1.get()
        text1.insert(tk.END, text)


def new_window():
    top = tk.Toplevel()
    top.geometry("300x300")
    top.title("this is window child")
    top.resizable(False, False)

def exit():
    win.destroy()


if __name__ == "__main__":
    win = tk.Tk()
    win.title("Roey's App")
    win.geometry("410x300")
    win.resizable(False, False)

    lab1 = tk.Label(win, text="username")
    lab1.place(x=5, y=10)

    ent1 = tk.Entry(win, width="44")
    ent1.place(x=70, y=10)

    btn1 = tk.Button(win, text="submit", command=poper)
    btn1.place(x=350, y=5)

    list1 = tk.Listbox(win, selectmode=tk.MULTIPLE, width=10, height=4)
    list1.insert(1, "python")
    list1.insert(2, "perl")
    list1.insert(1, "c")
    list1.place(x=6, y=35)

    text1 = tk.Text(win, height=10, width=30)
    text1.place(x=80, y=35)

    menu = tk.Menu(win)
    file_item = tk.Menu(menu)

    file_item.add_command(label="new", command=new_window)
    file_item.add_command(label="save")
    file_item.add_separator()
    file_item.add_command(label="exit", command=exit)
    menu.add_cascade(label="file", menu=file_item)
    win.config(menu=menu)

    win.mainloop()
