from asyncio import set_event_loop

import customtkinter as ctk
import tkinter as tk

import draw
from constants import Options, Colors
from utils import Point


class Canvas(tk.Canvas):
    def __init__(self, master):
        super().__init__(master=master, bg="white")

        # canvas event binding
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
        self.current_selected_drawing = None
        self.selected_bg_color = Colors.BG_LIGHT
        self.selected_txt_color = Colors.TEXT_MAIN
        self.selected_outline_color = Colors.BORDER_MEDIUM
        self.last_z_index = -1

    def on_mouse_press(self, event):
        if not self.mouse_pressed:
            self.starting_point.x = event.x
            self.starting_point.y = event.y
            self.mouse_pressed = True

        if self.current_selected_drawing:
            self.current_selected_drawing.on_blur()
            self.current_selected_drawing = None

        if self.selected == Options.SELECTOR:
            self.on_select(event)
            return

        if self.selected == Options.LINE:
            self.current_drawing = draw.Line(self, 2, self.selected_outline_color)

        elif self.selected == Options.RECT:
            self.current_drawing = draw.Rect(self, 2, self.selected_bg_color, self.selected_outline_color)

        elif self.selected == Options.OVAL:
            self.current_drawing = draw.Oval(self, 2, self.selected_bg_color, self.selected_outline_color)

        elif self.selected == Options.RIGHT_TRIANGLE:
            self.current_drawing = draw.RightTriangle(self, 2, self.selected_bg_color, self.selected_outline_color)

        elif self.selected == Options.TRIANGLE:
            self.current_drawing = draw.Triangle(self, 2, self.selected_bg_color, self.selected_outline_color)

        elif self.selected == Options.ARROW:
            self.current_drawing = draw.Arrow(self, 2, self.selected_outline_color)

        elif self.selected == Options.TEXT_BOX:
            self.current_drawing = draw.TextBox(self, 25, 2, self.selected_txt_color)

        self.current_drawing.create(event.x, event.y)

    def on_mouse_release(self, event):
        self.mouse_pressed = False

        if self.selected == Options.SELECTOR:
            if self.current_selected_drawing:
                self.current_selected_drawing.on_drop(self.last_z_index + 1)
                self.last_z_index += 1
                print(self.last_z_index)
            return

        self.current_drawing.on_place(self.last_z_index + 1)
        self.last_z_index += 1

        self.drawings.append(self.current_drawing)
        self.current_drawing = None

    def on_mouse_moving(self, event):
        if not self.mouse_pressed:
            return

        if self.selected == Options.SELECTOR:
            if self.current_selected_drawing:
                self.current_selected_drawing.on_move(event)
            return

        self.current_drawing.update_drawing(self.starting_point, event)

    def on_undo(self, event):
        if self.drawings:
            last_drawing = self.drawings.pop()
            if last_drawing.selected:
                last_drawing.on_blur()
            self.delete(last_drawing.id)

    def on_select(self, event):
        selected_items = []
        for drawable in self.drawings:
            if drawable.is_hitbox_clicked(event):
                selected_items.append(drawable)

        if selected_items:
            selected_items.sort(key=lambda item: item.z_index)

            self.current_selected_drawing = selected_items.pop()
            self.current_selected_drawing.on_focus(event)
