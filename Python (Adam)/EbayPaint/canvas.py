from asyncio import set_event_loop

import customtkinter as ctk

import draw
from constants import Options, Colors
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
        self.selected = Options.LINE
        self.mouse_pressed = False
        self.starting_point = Point(0, 0)
        self.current_drawing = None

    def on_mouse_press(self, event):
        if not self.mouse_pressed:
            self.starting_point.x = event.x
            self.starting_point.y = event.y
            self.mouse_pressed = True

        if self.selected == Options.SELECTOR:
            for drawable in self.drawings:
                if drawable.is_hitbox_clicked(event):
                    drawable.on_focus()
            return

        if self.selected == Options.LINE:
            self.current_drawing = draw.Line(self, 2, Colors.BLACK)
        elif self.selected == Options.RECT:
            self.current_drawing = draw.Rect(self, 2, Colors.GRAY, Colors.BLACK)
        elif self.selected == Options.OVAL:
            self.current_drawing = draw.Oval(self, 2, Colors.GRAY, Colors.BLACK)
        elif self.selected == Options.RIGHT_TRIANGLE:
            self.current_drawing = draw.RightTriangle(self, 2, Colors.GRAY, Colors.BLACK)
        elif self.selected == Options.TRIANGLE:
            self.current_drawing = draw.Triangle(self, 2, Colors.GRAY, Colors.BLACK)
        elif self.selected == Options.ARROW:
            self.current_drawing = draw.Arrow(self, 2, Colors.BLACK)
        elif self.selected == Options.TEXT_BOX:
            self.current_drawing = draw.TextBox(self, 25, 2, Colors.GRAY, Colors.BLACK, Colors.BLACK)

        self.current_drawing.create(event.x, event.y)

    def on_mouse_release(self, event):
        self.mouse_pressed = False

        if self.selected == Options.SELECTOR:
            return

        self.current_drawing.on_place()
        # print(self.current_drawing.x1, ",", self.current_drawing.y1)
        # print(self.current_drawing.x2, ",", self.current_drawing.y2)
        self.drawings.append(self.current_drawing)
        self.current_drawing = None

    def on_mouse_moving(self, event):
        if not self.mouse_pressed:
            return

        if self.selected == Options.SELECTOR:
            return

        self.current_drawing.update_drawing(self.starting_point, event)

    def on_undo(self, event):
        if self.drawings:
            last_drawing = self.drawings.pop()
            self.delete(last_drawing.id)
