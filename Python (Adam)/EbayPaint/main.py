from dataclasses import dataclass
from enum import Enum

import customtkinter as ctk


@dataclass(frozen=True)
class Colors:
    GRAY: str = "#A9A9A9"
    BLACK: str = "#1A1A1A"
    BLACK_LIGHT: str = "#2B2B2B"


@dataclass
class Point:
    x: int
    y: int


class Drawings(Enum):
    LINE = 1
    RECT = 2
    OVAL = 3
    TRIANGLE = 4


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


class NavBar(ctk.CTkFrame):
    def __init__(self, master):
        super().__init__(master=master)

        # variables
        self.app = master
        self.selection_buttons = []

        # components
        self.selection_buttons.append(self.create_btn("Line", Colors.BLACK, Drawings.LINE))
        self.selection_buttons.append(self.create_btn("Rect", Colors.GRAY, Drawings.RECT))
        self.selection_buttons.append(self.create_btn("Circle", Colors.GRAY, Drawings.OVAL))
        self.selection_buttons.append(self.create_btn("Triangle", Colors.GRAY, Drawings.TRIANGLE))

        # grid config
        self.columnconfigure((0, 1, 2, 3), weight=1, uniform="a")
        self.rowconfigure(0, weight=1, uniform="a")

        # grid placement
        for i, button in enumerate(self.selection_buttons):
            button.grid(column=i, row=0, padx=10, pady=10, sticky="nsew")

    def on_select(self, button, drawing):
        self.app.canvas.selected = drawing
        for btn in self.selection_buttons:
            if btn == button:
                btn.configure(fg_color=Colors.BLACK)
            else:
                btn.configure(fg_color=Colors.GRAY)

    def create_btn(self, text, fg_color, drawing):
        draw_btn = ctk.CTkButton(self, width=50, height=40, text=text, corner_radius=5,
                                 fg_color=fg_color, hover_color=Colors.BLACK_LIGHT)
        draw_btn.configure(command=lambda: self.on_select(draw_btn, drawing))
        return draw_btn


class App(ctk.CTk):
    def __init__(self):
        super().__init__()
        # window config
        self.title = "EbayPaint"
        self.center_window(1300, 800)

        # components
        self.nav_bar = NavBar(self)
        self.canvas = Canvas(self)

        # grid config
        self.columnconfigure(0, weight=1, uniform="a")
        self.rowconfigure(0, weight=1, uniform="a")
        self.rowconfigure(1, weight=10, uniform="a")

        # grid placement
        self.nav_bar.grid(column=0, row=0, sticky="nswe")
        self.canvas.grid(column=0, row=1, sticky="nswe")

    def center_window(self, width, height):
        self.update_idletasks()
        screen_width = self.winfo_screenwidth()
        screen_height = self.winfo_screenheight()
        x = (screen_width - width) // 2
        y = (screen_height - height) // 2
        self.geometry(f"{width}x{height}+{x}+{y}")


if __name__ == '__main__':
    app = App()
    app.mainloop()
