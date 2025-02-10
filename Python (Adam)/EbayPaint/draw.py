from os import write
from typing import override

import customtkinter as ctk

from constants import Options


class Drawable:
    def __init__(self, canvas):
        self.id = -1
        self.hitbox = Hitbox()
        self.canvas = canvas
        self.selected = False


class Line(Drawable):
    def __init__(self, canvas, thickness, color):
        super().__init__(canvas)
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

    def draw(self, x1, y1, x2, y2):
        self.x1, self.x2 = x1, x2
        self.y1, self.y2 = y1, y2
        self.id = self.canvas.create_line((self.x1, self.y1, self.x2, self.y2),
                                          width=self.thickness,
                                          fill=self.color)

    def on_focus(self):
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        self.hitbox.set(self.x1, self.y1, self.x2, self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)

    def __str__(self):
        return f"x1: {self.x1},y1: {self.y1},x2: {self.x2},y2: {self.y2}"


class Arrow(Line):
    def __init__(self, canvas, thickness, color):
        super().__init__(canvas, thickness, color)

    @override
    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.id = self.canvas.create_line((self.x1, self.y1, self.x2, self.y2),
                                          width=self.thickness,
                                          fill=self.color,
                                          arrow="last")

    @override
    def draw(self, x1, y1, x2, y2):
        self.x1, self.x2 = x1, x2
        self.y1, self.y2 = y1, y2
        self.id = self.canvas.create_line((self.x1, self.y1, self.x2, self.y2),
                                          width=self.thickness,
                                          fill=self.color,
                                          arrow="last")


class Rect(Drawable):
    def __init__(self, canvas, thickness, bg_color, outline_color):
        super().__init__(canvas)
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

    def draw(self, x1, y1, x2, y2):
        self.x1, self.y1 = x1, y1
        self.x2, self.y2 = x2, y2
        self.width = abs(self.x1 - self.x2)
        self.height = abs(self.y1 - self.y2)

        self.id = self.canvas.create_rectangle((self.x1, self.y1, self.x2, self.y2), width=self.thickness,
                                               fill=self.bg_color, outline=self.outline_color)

    def on_focus(self):
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        self.hitbox.set(self.x1, self.y1, self.x2, self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)


class Oval(Drawable):
    def __init__(self, canvas, thickness, bg_color, outline_color):
        super().__init__(canvas)
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

    def draw(self, x1, y1, x2, y2):
        self.x1, self.y1 = x1, y1
        self.x2, self.y2 = x2, y2
        self.radius = abs(self.x1 - self.x2) / 2

        self.id = self.canvas.create_oval((self.x1, self.y1, self.x2, self.y2), width=self.thickness,
                                          fill=self.bg_color,
                                          outline=self.outline_color)

    def on_focus(self):
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        self.hitbox.set(self.x1, self.y1, self.x2, self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)


class Triangle(Drawable):
    def __init__(self, canvas, thickness, bg_color, outline_color):
        super().__init__(canvas)
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

    def draw(self, x1, y1, x2, y2, x3, y3):
        self.x1, self.y1 = x1, y1
        self.x2, self.y2 = x2, y2
        self.x3, self.y3 = x3, y3

        self.id = self.canvas.create_polygon((self.x1, self.y1, self.x2, self.y2, self.x3, self.y3),
                                             width=self.thickness,
                                             fill=self.bg_color,
                                             outline=self.outline_color)

    def on_focus(self):
        self.selected = True
        self.hitbox.draw(self.canvas, min(self.x2, self.x3), self.y1, max(self.x2, self.x3), self.y2)

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        self.hitbox.set(min(self.x2, self.x3), self.y1, max(self.x2, self.x3), self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)


class RightTriangle(Drawable):
    def __init__(self, canvas, thickness, bg_color, outline_color):
        super().__init__(canvas)
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

    def draw(self, x1, y1, x2, y2, x3, y3):
        self.x1, self.y1 = x1, y1
        self.x2, self.y2 = x2, y2
        self.x3, self.y3 = x3, y3

        self.id = self.canvas.create_polygon((self.x1, self.y1, self.x2, self.y2, self.x3, self.y3),
                                             width=self.thickness,
                                             fill=self.bg_color,
                                             outline=self.outline_color)

    def on_focus(self):
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x3, self.y3)

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        self.hitbox.set(self.x1, self.y1, self.x3, self.y3)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)


class TextBox(Drawable):
    def __init__(self, canvas, font_size, thickness, color):
        super().__init__(canvas)
        self.x1 = self.x2 = self.y1 = self.y2 = 0
        self.width = self.height = 0
        self.thickness = thickness
        self.color = color
        self.font = ("Helvetica", font_size)
        self.text_widget = None

    def get_text(self) -> str:
        if self.text_widget is not None:
            return self.text_widget.get("1.0", "end-1c")
        return ""

    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.hitbox.create(self.canvas, x, y)

    def update_drawing(self, starting_point, mouse):
        self.x1 = starting_point.x
        self.y1 = starting_point.y
        self.x2 = mouse.x
        self.y2 = mouse.y
        self.width = abs(self.x1 - self.x2)
        self.height = abs(self.y1 - self.y2)

        new_coords = (self.x1, self.y1, self.x2, self.y2)
        self.canvas.coords(self.hitbox.id, new_coords)

    def draw(self, x1, y1, x2, y2):
        self.text_widget = ctk.CTkTextbox(self.canvas.master, width=self.width, height=self.height,
                                          border_width=self.thickness, font=self.font)

        self.x1, self.y1 = x1, y1
        self.x2, self.y2 = x2, y2
        self.width = abs(self.x1 - self.x2)
        self.height = abs(self.y1 - self.y2)

        self.id = self.canvas.create_window((self.x1 + self.width / 2, self.y1 + self.height / 2),
                                            window=self.text_widget)

    def on_focus(self):
        print("dfsfdfs")
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_move(self):
        pass

    def on_resize(self):
        pass

    def on_place(self):
        self.hitbox.remove(self.canvas)
        self.text_widget = ctk.CTkTextbox(self.canvas.master, width=self.width, height=self.height,
                                          border_width=self.thickness, font=self.font)

        if self.x1 > self.x2:
            self.x1, self.x2 = self.x2, self.x1

        if self.y1 > self.y2:
            self.y1, self.y2 = self.y2, self.y1

        self.id = self.canvas.create_window((self.x1 + self.width / 2, self.y1 + self.height / 2),
                                            window=self.text_widget)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)


class Hitbox:
    def __init__(self):
        self.id = -1
        self.x1 = self.x2 = self.y1 = self.y2 = 0
        self.width = self.height = 0
        self.min_width = self.min_height = 50

    def set(self, x1, y1, x2, y2):
        self.x1, self.y1 = min(x1, x2), min(y1, y2)
        self.x2, self.y2 = max(x1, x2), max(y1, y2)
        self.width = abs(self.x1 - self.x2)
        self.height = abs(self.y1 - self.y2)

    def create(self, canvas, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.id = canvas.create_rectangle((self.x1, self.y1, self.x2, self.y2), outline="black",
                                          dash=(1, 3))
        canvas.lift(self.id)

    def update_drawing(self, canvas, starting_point, mouse):
        self.x1 = starting_point.x
        self.y1 = starting_point.y
        self.x2 = mouse.x
        self.y2 = mouse.y
        self.width = abs(self.x1 - self.x2)
        self.height = abs(self.y1 - self.y2)

        new_coords = (self.x1, self.y1, self.x2, self.y2)
        canvas.coords(self.id, new_coords)

    def draw(self, canvas, x1, y1, x2, y2):
        self.set(x1, y1, x2, y2)

        self.id = canvas.create_rectangle((self.x1, self.y1, self.x2, self.y2), outline="#eeeedd",
                                          dash=(1, 3))
        canvas.lift(self.id)

    def on_move(self, canvas, x, y):
        pass

    def remove(self, canvas):
        canvas.delete(self.id)

    def contains(self, mouse):
        return self.x1 < mouse.x < self.x2 and self.y1 < mouse.y < self.y2
