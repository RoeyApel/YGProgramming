import tkinter as tk
from turtledemo.nim import COLOR

import draw
from constants import Options, Colors, Types
from meta import save_as_json
from utils import Point


class Canvas(tk.Canvas):
    def __init__(self, master):
        super().__init__(master=master, bg="white")

        # canvas event binding
        self.bind("<Button>", lambda event: self.on_mouse_press(event))
        self.bind("<ButtonRelease>", lambda event: self.on_mouse_release(event))
        self.bind("<Motion>", lambda event: self.on_mouse_moving(event))
        self.master.bind_all("<Control-z>", lambda event: self.on_undo(event))
        self.master.bind_all("<Delete>", lambda event: self.delete_drawing(event))
        self.master.bind_all("<Control-a>", lambda event: self.update_selected_drawing(event))
        self.master.bind_all("<Control-d>", lambda event: self.lower_selected_z_index(event))
        self.master.bind_all("<Control-c>", lambda event: self.clear_canvas(event))

        # variables
        self.drawings = []
        self.selected = Options.LINE
        self.mouse_pressed = False
        self.starting_point = Point(0, 0)
        self.current_drawing = None
        self.current_selected_drawing = None
        self.selected_thickness = 2
        self.selected_bg_color = Colors.BG_LIGHT
        self.selected_txt_color = Colors.TEXT_MAIN
        self.selected_outline_color = Colors.BORDER_MEDIUM
        self.canvas_color = "#ffffff"
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
            if self.current_selected_drawing and self.current_selected_drawing.has_clicked_corner(event):
                self.current_selected_drawing.resized = True
                self.current_selected_drawing.hitbox.remove(self)
            return

        if self.selected == Options.LINE:
            self.current_drawing = draw.Line(self, self.selected_thickness, self.selected_bg_color)

        elif self.selected == Options.RECT:
            self.current_drawing = draw.Rect(self, self.selected_thickness, self.selected_bg_color,
                                             self.selected_outline_color)

        elif self.selected == Options.OVAL:
            self.current_drawing = draw.Oval(self, self.selected_thickness, self.selected_bg_color,
                                             self.selected_outline_color)

        elif self.selected == Options.RIGHT_TRIANGLE:
            self.current_drawing = draw.RightTriangle(self, self.selected_thickness, self.selected_bg_color,
                                                      self.selected_outline_color)

        elif self.selected == Options.TRIANGLE:
            self.current_drawing = draw.Triangle(self, self.selected_thickness, self.selected_bg_color,
                                                 self.selected_outline_color)

        elif self.selected == Options.ARROW:
            self.current_drawing = draw.Arrow(self, self.selected_thickness, self.selected_bg_color)

        self.current_drawing.create(event.x, event.y)

    def on_mouse_release(self, event):
        self.mouse_pressed = False

        if self.selected == Options.SELECTOR:
            if self.current_selected_drawing:
                self.current_selected_drawing.on_drop(self.last_z_index + 1)
                self.last_z_index += 1
                self.lift(self.current_selected_drawing.hitbox.id)
                self.current_selected_drawing.resized = False
            return

        if self.current_drawing:
            self.current_drawing.on_place(self.last_z_index + 1)
            self.last_z_index += 1

            self.drawings.append(self.current_drawing)
            self.current_drawing = None

    def on_mouse_moving(self, event):
        if not self.mouse_pressed:
            return

        if self.selected == Options.SELECTOR:
            if self.current_selected_drawing and self.current_selected_drawing.resized:
                self.current_selected_drawing.on_resize(event)
                return

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

    def add_text(self, text):
        self.current_drawing = draw.TextBox(self, text, self.selected_thickness, self.selected_txt_color)
        self.current_drawing.draw(self.winfo_width() / 2, self.winfo_height() / 2)
        self.drawings.append(self.current_drawing)
        self.current_drawing.on_place(self.last_z_index + 1)
        self.last_z_index += 1

        self.selected = Options.SELECTOR
        if self.current_selected_drawing:
            self.current_selected_drawing.on_blur()

        self.current_selected_drawing = self.current_drawing
        self.current_selected_drawing.on_focus(Point(0, 0))
        print(self.current_selected_drawing)

    def delete_drawing(self, event):
        if self.current_selected_drawing:
            self.current_selected_drawing.on_blur()
            self.drawings.remove(self.current_selected_drawing)
            self.delete(self.current_selected_drawing.id)

    def load_drawings(self, drawings):
        drawings = sorted(drawings, key=lambda item: item["z_index"])
        self.last_z_index = drawings[len(drawings) - 1]["z_index"] + 1

        for drawing in drawings:
            self.draw(drawing)

    def draw(self, drawing):
        if drawing["type"] == Types.LINE:
            self.current_drawing = draw.Line(self, drawing["thickness"], drawing["color"])
            self.current_drawing.draw(drawing['x1'], drawing['y1'], drawing['x2'], drawing['y2'])
        elif drawing["type"] == Types.ARROW:
            self.current_drawing = draw.Arrow(self, drawing["thickness"], drawing["color"])
            self.current_drawing.draw(drawing['x1'], drawing['y1'], drawing['x2'], drawing['y2'])
        elif drawing["type"] == Types.RECT:
            self.current_drawing = draw.Rect(self, drawing["thickness"], drawing["bg_color"], drawing["outline_color"])
            self.current_drawing.draw(drawing['x1'], drawing['y1'], drawing['x2'], drawing['y2'])
        elif drawing["type"] == Types.OVAL:
            self.current_drawing = draw.Oval(self, drawing["thickness"], drawing["bg_color"], drawing["outline_color"])
            self.current_drawing.draw(drawing['x1'], drawing['y1'], drawing['x2'], drawing['y2'])
        elif drawing["type"] == Types.TRIANGLE:
            self.current_drawing = draw.Triangle(self, drawing["thickness"], drawing["bg_color"],
                                                 drawing["outline_color"])
            self.current_drawing.draw(drawing['x1'], drawing['y1'], drawing['x2'], drawing['y2'], drawing['x3'],
                                      drawing['y3'])
        elif drawing["type"] == Types.RIGHT_TRIANGLE:
            self.current_drawing = draw.RightTriangle(self, drawing["thickness"], drawing["bg_color"],
                                                      drawing["outline_color"])
            self.current_drawing.draw(drawing['x1'], drawing['y1'], drawing['x2'], drawing['y2'], drawing['x3'],
                                      drawing['y3'])
        elif drawing["type"] == Types.TEXT_BOX:
            self.current_drawing = draw.TextBox(self, drawing["text"], drawing["thickness"], drawing["color"])
            self.current_drawing.draw(drawing['x'], drawing['y'])

        self.current_drawing.on_place(drawing["z_index"])
        print(f"-----{self.current_drawing}------")
        self.drawings.append(self.current_drawing)

    def __str__(self):
        return f'Canvas: {self.__dict__}'

    def update_selected_drawing(self, event):
        if self.current_selected_drawing:
            # Call update_style for all drawing types with appropriate parameters
            if self.current_selected_drawing.type == Types.LINE:
                self.current_selected_drawing.update_style(self.selected_thickness, self.selected_bg_color)

            elif self.current_selected_drawing.type == Types.ARROW:
                self.current_selected_drawing.update_style(self.selected_thickness, self.selected_outline_color)

            elif self.current_selected_drawing.type == Types.RECT:
                self.current_selected_drawing.update_style(self.selected_thickness, self.selected_bg_color,
                                                           self.selected_outline_color)

            elif self.current_selected_drawing.type == Types.OVAL:
                self.current_selected_drawing.update_style(self.selected_thickness, self.selected_bg_color,
                                                           self.selected_outline_color)

            elif self.current_selected_drawing.type == Types.TRIANGLE:
                self.current_selected_drawing.update_style(self.selected_thickness, self.selected_bg_color,
                                                           self.selected_outline_color)

            elif self.current_selected_drawing.type == Types.RIGHT_TRIANGLE:
                self.current_selected_drawing.update_style(self.selected_thickness, self.selected_bg_color,
                                                           self.selected_outline_color)

            elif self.current_selected_drawing.type == Types.TEXT_BOX:
                self.current_selected_drawing.update_style(self.selected_thickness, self.selected_txt_color)
                self.current_selected_drawing.hitbox.remove(self)
                self.current_selected_drawing.on_focus(Point(0, 0))

    def lower_selected_z_index(self, event):
        if self.current_selected_drawing:
            self.lower(self.current_selected_drawing.id)
            self.current_selected_drawing.on_blur()

            self.drawings.sort(key=lambda item: item.z_index)
            current_z_index = self.current_selected_drawing.z_index
            updated_z_index = self.drawings[0].z_index

            i = 0
            while i < len(self.drawings) - 1 and self.drawings[i].z_index < current_z_index:
                self.drawings[i].z_index = self.drawings[i + 1].z_index
                i += 1

            self.current_selected_drawing.z_index = updated_z_index
            self.lower(self.current_selected_drawing.id)
            self.current_selected_drawing.on_blur()
            self.current_selected_drawing = None

    def clear_canvas(self, event):
        if self.current_selected_drawing:
            self.current_selected_drawing.on_blur()

        for drawing in self.drawings:
            self.delete(drawing.id)

        self.drawings = []
        self.change_canvas_color("#ffffff")

    def change_canvas_color(self, color):
        self.configure(bg=color)
        self.canvas_color = color
        self.master.nav_bar.pick_canvas_color_btn.configure(fg_color=color)
