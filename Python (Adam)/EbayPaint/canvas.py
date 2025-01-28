import customtkinter as ctk

from constants import Drawings
from utils import Point


class Canvas(ctk.CTkCanvas):
    def __init__(self, master):
        super().__init__(master=master, bg="white")

        # canvas config
        self.bind("<Button>", lambda event: self.on_mouse_press(event))
        self.bind("<ButtonRelease>", lambda event: self.on_mouse_release(event))
        self.bind("<Motion>", lambda event: self.on_mouse_moving(event))
        self.master.bind_all("<Control-z>", lambda event: self.on_undo(event))

        # variables
        self.drawings = []
        self.selected = Drawings.LINE
        self.mouse_pressed = False
        self.starting_point = Point(0, 0)
        self.current_drawing = None

    def on_mouse_press(self, event):
        if not self.mouse_pressed:
            self.starting_point.x = event.x
            self.starting_point.y = event.y
            self.mouse_pressed = True

        if self.selected == Drawings.LINE:
            self.current_drawing = self.create_line((event.x, event.y, event.x, event.y))
        elif self.selected == Drawings.RECT:
            self.current_drawing = self.create_rectangle((event.x, event.y, event.x, event.y))
        elif self.selected == Drawings.OVAL:
            self.current_drawing = self.create_oval((event.x, event.y, event.x, event.y))
        elif self.selected == Drawings.TRIANGLE:
            self.current_drawing = self.create_polygon((event.x, event.y, event.x, event.y, event.x, event.y),
                                                       fill="", outline="black")

    def on_mouse_release(self, event):
        self.mouse_pressed = False
        self.drawings.append(self.current_drawing)
        self.current_drawing = None

    def on_mouse_moving(self, event):
        if not self.mouse_pressed:
            return

        if self.selected == Drawings.TRIANGLE:
            new_coords = (self.starting_point.x, self.starting_point.y,
                          self.starting_point.x, event.y,
                          event.x, event.y)
        else:
            new_coords = (self.starting_point.x, self.starting_point.y, event.x, event.y)

        self.coords(self.current_drawing, new_coords)

    def on_undo(self, event):
        if self.drawings:
            last_drawing = self.drawings.pop()
            self.delete(last_drawing)
