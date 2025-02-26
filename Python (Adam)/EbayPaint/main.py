import customtkinter as ctk

from canvas import Canvas

from nav_bar import NavBar


class App(ctk.CTk):
    def __init__(self):
        super().__init__()
        # window config
        self.title("EbayPaint")
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
