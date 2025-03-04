from math import log10
from os import write
from typing import override

import customtkinter as ctk
from utils import Point

from constants import Options, Types


class Drawable:
    def __init__(self, canvas):
        self.type = "---"
        self.id = -1
        self.z_index = -1
        self.hitbox = Hitbox()
        self.canvas = canvas
        self.selected = False
        self.resized = False
        self.mouse_x_on_select = -1
        self.mouse_y_on_select = -1


class Line(Drawable):
    def __init__(self, canvas, thickness, color):
        super().__init__(canvas)
        self.type = Types.LINE
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

    def on_focus(self, mouse):
        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_blur(self):
        self.selected = False
        self.hitbox.remove(self.canvas)

    def on_move(self, mouse):
        move_x = mouse.x - self.mouse_x_on_select
        move_y = mouse.y - self.mouse_y_on_select

        self.canvas.move(self.id, move_x, move_y)
        self.hitbox.on_move(self.canvas, move_x, move_y)

        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y

        coords = self.canvas.coords(self.id)
        self.x1, self.y1, self.x2, self.y2 = coords

    def on_drop(self, z_index):
        self.z_index = z_index
        self.canvas.lift(self.id)
        if self.resized:
            self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_resize(self, mouse):
        self.update_drawing(Point(self.x1, self.y1), mouse)
        self.hitbox.remove(self.canvas)

    def on_place(self, z_index):
        self.z_index = z_index
        self.hitbox.set(self.x1, self.y1, self.x2, self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)

    def has_clicked_corner(self, mouse):
        return self.hitbox.has_clicked_corner(self.x2, self.y2, mouse)

    def update_style(self, thickness, color):
        self.thickness = thickness
        self.color = color
        self.canvas.itemconfig(self.id, width=self.thickness,
                               fill=self.color)


class Arrow(Line):
    def __init__(self, canvas, thickness, color):
        self.type = Types.ARROW
        super().__init__(canvas, thickness, color)

    @override
    def create(self, x, y):
        self.x1 = self.x2 = x
        self.y1 = self.y2 = y
        self.id = self.canvas.create_line((self.x1, self.y1, self.x2, self.y2),
                                          width=self.thickness,
                                          fill=self.color,
                                          arrow="last", arrowshape=(64, 80, 24))

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
        self.type = Types.RECT
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

    def on_focus(self, mouse):
        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_blur(self):
        self.selected = False
        self.hitbox.remove(self.canvas)

    def on_move(self, mouse):
        move_x = mouse.x - self.mouse_x_on_select
        move_y = mouse.y - self.mouse_y_on_select

        self.canvas.move(self.id, move_x, move_y)
        self.hitbox.on_move(self.canvas, move_x, move_y)

        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y

        coords = self.canvas.coords(self.id)
        self.x1, self.y1, self.x2, self.y2 = coords

    def on_drop(self, z_index):
        self.z_index = z_index
        self.canvas.lift(self.id)
        if self.resized:
            self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_resize(self, mouse):
        self.update_drawing(Point(self.x1, self.y1), mouse)
        self.hitbox.remove(self.canvas)

    def on_place(self, z_index):
        self.z_index = z_index
        self.hitbox.set(self.x1, self.y1, self.x2, self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)

    def has_clicked_corner(self, mouse):
        return self.hitbox.has_clicked_corner(self.x2, self.y2, mouse)

    def update_style(self, thickness, bg_color, outline_color):
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color
        self.canvas.itemconfig(self.id, width=self.thickness,
                               fill=self.bg_color, outline=self.outline_color)


class Oval(Drawable):
    def __init__(self, canvas, thickness, bg_color, outline_color):
        super().__init__(canvas)
        self.type = Types.OVAL
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

    def on_focus(self, mouse):
        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_blur(self):
        self.selected = False
        self.hitbox.remove(self.canvas)

    def on_move(self, mouse):
        move_x = mouse.x - self.mouse_x_on_select
        move_y = mouse.y - self.mouse_y_on_select

        self.canvas.move(self.id, move_x, move_y)
        self.hitbox.on_move(self.canvas, move_x, move_y)

        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y

        coords = self.canvas.coords(self.id)
        self.x1, self.y1, self.x2, self.y2 = coords

    def on_drop(self, z_index):
        self.z_index = z_index
        self.canvas.lift(self.id)
        if self.resized:
            self.hitbox.draw(self.canvas, self.x1, self.y1, self.x2, self.y2)

    def on_resize(self, mouse):
        self.update_drawing(Point(self.x1, self.y1), mouse)
        self.hitbox.remove(self.canvas)

    def on_place(self, z_index):
        self.z_index = z_index
        self.hitbox.set(self.x1, self.y1, self.x2, self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)

    def has_clicked_corner(self, mouse):
        return self.hitbox.has_clicked_corner(self.x2, self.y2, mouse)

    def update_style(self, thickness, bg_color, outline_color):
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color
        self.canvas.itemconfig(self.id, width=self.thickness,
                               fill=self.bg_color, outline=self.outline_color)


class Triangle(Drawable):
    def __init__(self, canvas, thickness, bg_color, outline_color):
        super().__init__(canvas)
        self.type = Types.TRIANGLE
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

    def on_focus(self, mouse):
        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y
        self.selected = True
        self.hitbox.draw(self.canvas, min(self.x2, self.x3), self.y1, max(self.x2, self.x3), self.y2)

    def on_blur(self):
        self.selected = False
        self.hitbox.remove(self.canvas)

    def on_move(self, mouse):
        move_x = mouse.x - self.mouse_x_on_select
        move_y = mouse.y - self.mouse_y_on_select

        self.canvas.move(self.id, move_x, move_y)
        self.hitbox.on_move(self.canvas, move_x, move_y)

        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y

        coords = self.canvas.coords(self.id)
        self.x1, self.y1, self.x2, self.y2, self.x3, self.y3 = coords

    def on_drop(self, z_index):
        self.z_index = z_index
        self.canvas.lift(self.id)
        if self.resized:
            self.hitbox.draw(self.canvas, min(self.x2, self.x3), self.y1, max(self.x2, self.x3), self.y2)

    def on_resize(self, mouse):
        self.update_drawing(Point(self.x2, self.y1), mouse)
        self.hitbox.remove(self.canvas)

    def on_place(self, z_index):
        self.z_index = z_index
        self.hitbox.set(min(self.x2, self.x3), self.y1, max(self.x2, self.x3), self.y2)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)

    def has_clicked_corner(self, mouse):
        return self.hitbox.has_clicked_corner(self.x3, self.y3, mouse)

    def update_style(self, thickness, bg_color, outline_color):
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color
        self.canvas.itemconfig(self.id, width=self.thickness,
                               fill=self.bg_color, outline=self.outline_color)


class RightTriangle(Drawable):
    def __init__(self, canvas, thickness, bg_color, outline_color):
        super().__init__(canvas)
        self.type = Types.RIGHT_TRIANGLE
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

    def on_focus(self, mouse):
        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y
        self.selected = True
        self.hitbox.draw(self.canvas, self.x1, self.y1, self.x3, self.y3)

    def on_blur(self):
        self.selected = False
        self.hitbox.remove(self.canvas)

    def on_move(self, mouse):
        move_x = mouse.x - self.mouse_x_on_select
        move_y = mouse.y - self.mouse_y_on_select

        self.canvas.move(self.id, move_x, move_y)
        self.hitbox.on_move(self.canvas, move_x, move_y)

        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y

        coords = self.canvas.coords(self.id)
        self.x1, self.y1, self.x2, self.y2, self.x3, self.y3 = coords

    def on_drop(self, z_index):
        self.z_index = z_index
        self.canvas.lift(self.id)
        if self.resized:
            self.hitbox.draw(self.canvas, self.x1, self.y1, self.x3, self.y3)

    def on_resize(self, mouse):
        self.update_drawing(Point(self.x1, self.y1), mouse)
        self.hitbox.remove(self.canvas)

    def on_place(self, z_index):
        self.z_index = z_index
        self.hitbox.set(self.x1, self.y1, self.x3, self.y3)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)

    def has_clicked_corner(self, mouse):
        return self.hitbox.has_clicked_corner(self.x3, self.y3, mouse)

    def update_style(self, thickness, bg_color, outline_color):
        self.thickness = thickness
        self.bg_color = bg_color
        self.outline_color = outline_color
        self.canvas.itemconfig(self.id, width=self.thickness,
                               fill=self.bg_color, outline=self.outline_color)


class TextBox(Drawable):
    def __init__(self, canvas, text, thickness, color):
        super().__init__(canvas)
        self.type = Types.TEXT_BOX
        self.x = self.y = 0
        self.width = self.height = 0
        self.thickness = thickness
        self.color = color
        self.font = ("Helvetica", int(thickness * 5))
        self.text = text

    def create(self, x, y):
        pass

    def update_drawing(self, starting_point, mouse):
        pass

    def draw(self, x, y):
        self.x, self.y = x, y

        self.id = self.canvas.create_text(x, y, text=self.text, font=self.font, fill=self.color)

        box = self.canvas.bbox(self.id)

        if box:
            self.width = box[2] - box[0]
            self.height = box[3] - box[1]
            self.hitbox.set(*box)

    def on_focus(self, mouse):
        print("focus")
        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y
        self.selected = True

        bbox = self.canvas.bbox(self.id)
        if bbox:
            self.hitbox.draw(self.canvas, *bbox)

    def on_blur(self):
        print("blur")
        self.selected = False
        self.hitbox.remove(self.canvas)

    def on_move(self, mouse):
        move_x = mouse.x - self.mouse_x_on_select
        move_y = mouse.y - self.mouse_y_on_select

        self.canvas.move(self.id, move_x, move_y)
        self.hitbox.on_move(self.canvas, move_x, move_y)

        self.mouse_x_on_select = mouse.x
        self.mouse_y_on_select = mouse.y

        coords = self.canvas.coords(self.id)
        self.x, self.y = coords

    def on_drop(self, z_index):
        self.z_index = z_index
        self.canvas.lift(self.id)
        if self.resized:
            self.hitbox.draw(self.canvas, *self.canvas.bbox(self.id))

    def on_resize(self, mouse):
        pass

    def on_place(self, z_index):
        self.z_index = z_index
        self.canvas.lift(self.id)
        if self.hitbox.id in self.canvas.find_all():
            self.hitbox.remove(self.canvas)

    def is_hitbox_clicked(self, mouse):
        return self.hitbox.contains(mouse)

    def has_clicked_corner(self, mouse):
        pass

    def update_style(self, thickness, color):
        self.thickness = thickness
        self.color = color
        self.canvas.itemconfig(self.id, font=("Helvetica", int(self.thickness * 5)),
                               fill=self.color)


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

        self.id = canvas.create_rectangle((self.x1, self.y1, self.x2, self.y2), outline="black",
                                          dash=(1, 3))
        canvas.lift(self.id)

    def on_move(self, canvas, move_x, move_y):
        canvas.move(self.id, move_x, move_y)
        self.x1, self.y1, self.x2, self.y2 = canvas.coords(self.id)

    def on_drop(self, canvas):
        pass

    def remove(self, canvas):
        if self.id != -1:
            canvas.delete(self.id)
            self.id = -1

    def contains(self, mouse):
        print(self.x1 < mouse.x < self.x2 and self.y1 < mouse.y < self.y2)
        return self.x1 < mouse.x < self.x2 and self.y1 < mouse.y < self.y2

    def has_clicked_corner(self, x, y, mouse):
        size = (self.width + self.height) / 10
        return ((x + size >= mouse.x >= x - size) and
                (y + size >= mouse.y >= y - size))

    def __str__(self):
        return (f"Hitbox(id={self.id}, x1={self.x1}, y1={self.y1}, "
                f"x2={self.x2}, y2={self.y2}, width={self.width}, height={self.height})")
