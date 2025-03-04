import os
import threading

import customtkinter as ctk

from canvas import Canvas
from meta import save_as_json, load_save

from nav_bar import NavBar


class App(ctk.CTk):
    def __init__(self):
        super().__init__()
        # window config
        self.title("EbayPaint")
        self.center_window(1300, 800)

        # events bindings
        self.bind_all("<Control-s>", lambda event: self.save(event))
        self.bind_all("<Control-l>", lambda event: self.load_save(event))

        # components
        self.nav_bar = NavBar(self)
        self.canvas = Canvas(self)

        # grid config
        self.columnconfigure(0, weight=1, uniform="a")
        self.rowconfigure(0, weight=1, uniform="a")
        self.rowconfigure(1, weight=13, uniform="a")

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

    def save(self, event):
        app_directory = os.path.dirname(os.path.abspath(__file__))
        file_path = os.path.join(app_directory, "save.json")

        thread = threading.Thread(target=save_as_json, args=(file_path, self.canvas.drawings, self.canvas.canvas_color))
        thread.start()

    def load_save(self, event):
        app_directory = os.path.dirname(os.path.abspath(__file__))
        file_path = os.path.join(app_directory, "save.json")

        thread = threading.Thread(target=load_save, args=(file_path, self.canvas))
        thread.start()


if __name__ == '__main__':
    app = App()
    app.mainloop()
