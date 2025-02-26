from dataclasses import dataclass
from tkinter import messagebox


@dataclass
class Point:
    x: int
    y: int


def show_error(msg):
    messagebox.showerror("Error", msg)
