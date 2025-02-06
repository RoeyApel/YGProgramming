from os import write
from typing import override

import customtkinter as ctk

from constants import Options


class Drawable:
    def __init__(self, canvas, drawable_type):
        self.id = -1
        self.hitboxId = -1
        self.type = drawable_type
        self.canvas = canvas


class Line(Drawable):
    def __init__(self, canvas, drawable_type, thickness, color):
        super().__init__(canvas, drawable_type)
        self.x1 = self.x2 = self.y1 = self.y2 = 0
        self.thickness = thickness
        self.color = color

    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.id = self.canvas.create_line((self.x1, self.y1, self.x2, self.y2), width=self.thickness, fill=self.color)

    def update_drawing(self, starting_point, mouse):
        self.x1 = starting_point.x
        self.y1 = starting_point.y
        self.x2 = mouse.x
        self.y2 = mouse.y

        new_coords = (self.x1, self.y1, self.x2, self.y2)
        self.canvas.coords(self.id, new_coords)

    def create_hitbox(self):
        pass

    def on_click(self):
        pass

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        pass

    def is_hitbox_clicked(self):
        pass


class Arrow(Line):
    def __init__(self, canvas, drawable_type, thickness, color):
        super().__init__(canvas, drawable_type, thickness, color)

    @override
    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.id = self.canvas.create_line((self.x1, self.y1, self.x2, self.y2),
                                          width=self.thickness,
                                          fill=self.color,
                                          arrow="last")


class Rect(Drawable):
    def __init__(self, canvas, drawable_type, thickness, bg_color, outline_color):
        super().__init__(canvas, drawable_type)
        self.x1 = self.x2 = self.y1 = self.y2 = 0
        self.width = self.height = 0
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color

    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.id = self.canvas.create_rectangle((self.x1, self.y1, self.x2, self.y2), width=self.thickness,
                                               fill=self.bg_color, outline=self.outline_color)

    def update_drawing(self, starting_point, mouse):
        self.x1 = starting_point.x
        self.y1 = starting_point.y
        self.x2 = mouse.x
        self.y2 = mouse.y
        self.width = abs(self.x1 - self.x2)
        self.height = abs(self.y1 - self.y2)

        new_coords = (self.x1, self.y1, self.x2, self.y2)
        self.canvas.coords(self.id, new_coords)

    def create_hitbox(self):
        pass

    def on_click(self):
        pass

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        pass

    def is_hitbox_clicked(self):
        pass


class Oval(Drawable):
    def __init__(self, canvas, drawable_type, thickness, bg_color, outline_color):
        super().__init__(canvas, drawable_type)
        self.x1 = self.x2 = self.y1 = self.y2 = 0
        self.radius = 0
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color

    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.id = self.canvas.create_oval((self.x1, self.y1, self.x2, self.y2), width=self.thickness,
                                          fill=self.bg_color,
                                          outline=self.outline_color)

    def update_drawing(self, starting_point, mouse):
        self.x1 = starting_point.x
        self.y1 = starting_point.y
        self.x2 = mouse.x
        self.y2 = mouse.y
        self.radius = abs(self.x1 - self.x2) / 2

        new_coords = (self.x1, self.y1, self.x2, self.y2)
        self.canvas.coords(self.id, new_coords)

    def create_hitbox(self):
        pass

    def on_click(self):
        pass

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        pass

    def is_hitbox_clicked(self):
        pass


class Triangle(Drawable):
    def __init__(self, canvas, drawable_type, thickness, bg_color, outline_color):
        super().__init__(canvas, drawable_type)
        self.x1 = self.x2 = self.y1 = self.y2 = self.x3 = self.y3 = 0
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color

    def create(self, x, y):
        self.x1 = self.x2 = self.x3 = x
        self.y1 = self.y2 = self.y3 = y
        self.id = self.canvas.create_polygon((self.x1, self.y1, self.x2, self.y2, self.x3, self.y3),
                                             width=self.thickness,
                                             fill=self.bg_color,
                                             outline=self.outline_color)

    def update_drawing(self, starting_point, mouse):
        self.x1 = (starting_point.x + mouse.x) / 2
        self.y1 = starting_point.y
        self.x2 = starting_point.x
        self.y2 = mouse.y
        self.x3 = mouse.x
        self.y3 = mouse.y

        new_coords = (self.x1, self.y1, self.x2, self.y2, self.x3, self.y3)
        self.canvas.coords(self.id, new_coords)

    def create_hitbox(self):
        pass

    def on_click(self):
        pass

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        pass

    def is_hitbox_clicked(self):
        pass


class RightTriangle(Drawable):
    def __init__(self, canvas, drawable_type, thickness, bg_color, outline_color):
        super().__init__(canvas, drawable_type)
        self.x1 = self.x2 = self.y1 = self.y2 = self.x3 = self.y3 = 0
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color

    def create(self, x, y):
        self.x1 = self.x2 = self.x3 = x
        self.y1 = self.y2 = self.y3 = y
        self.id = self.canvas.create_polygon((self.x1, self.y1, self.x2, self.y2, self.x3, self.y3),
                                             width=self.thickness,
                                             fill=self.bg_color,
                                             outline=self.outline_color)

    def update_drawing(self, starting_point, mouse):
        self.x1 = starting_point.x
        self.y1 = starting_point.y
        self.x2 = starting_point.x
        self.y2 = mouse.y
        self.x3 = mouse.x
        self.y3 = mouse.y

        new_coords = (self.x1, self.y1, self.x2, self.y2, self.x3, self.y3)
        self.canvas.coords(self.id, new_coords)

    def create_hitbox(self):
        pass

    def on_click(self):
        pass

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        pass

    def is_hitbox_clicked(self):
        pass


class TextBox(Drawable):
    def __init__(self, canvas, drawable_type, font_size, thickness, bg_color, outline_color, color):
        super().__init__(canvas, drawable_type)
        self.x1 = self.x2 = self.y1 = self.y2 = 0
        self.width = self.height = 0
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color
        self.color = color
        self.font = ("Helvetica", font_size)

    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.hitboxId = self.canvas.create_rectangle((self.x1, self.y1, self.x2, self.y2), width=self.thickness,
                                                     fill=self.bg_color, outline=self.outline_color)

    def update_drawing(self, starting_point, mouse):
        self.x1 = starting_point.x
        self.y1 = starting_point.y
        self.x2 = mouse.x
        self.y2 = mouse.y
        self.width = abs(self.x1 - self.x2)
        self.height = abs(self.y1 - self.y2)

        new_coords = (self.x1, self.y1, self.x2, self.y2)
        self.canvas.coords(self.hitboxId, new_coords)

    def create_hitbox(self):
        pass

    def on_click(self):
        pass

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        self.canvas.delete(self.hitboxId)
        text_box = ctk.CTkTextbox(self.canvas.master, width=self.width, height=self.height, fg_color=self.bg_color,
                                  border_color=self.outline_color, border_width=self.thickness, font=self.font)

        if (self.x1 > self.x2):
            self.x1, self.x2 = self.x2, self.x1

        if (self.y1 > self.y2):
            self.y1, self.y2 = self.y2, self.y1

        self.id = self.canvas.create_window((self.x1 + self.width / 2, self.y1 + self.height / 2), window=text_box)

    def is_hitbox_clicked(self):
        pass
