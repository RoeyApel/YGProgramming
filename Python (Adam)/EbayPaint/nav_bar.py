import customtkinter as ctk

from constants import Colors, Drawings


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
