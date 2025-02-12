import customtkinter as ctk

from constants import Colors, Options


class NavBar(ctk.CTkFrame):
    def __init__(self, master):
        super().__init__(master=master)

        # variables
        self.app = master
        self.selection_buttons = []

        # components
        self.selection_buttons.append(self.create_btn("Line", Colors.BLACK, Options.LINE))
        self.selection_buttons.append(self.create_btn("Arrow", Colors.GRAY, Options.ARROW))
        self.selection_buttons.append(self.create_btn("Rect", Colors.GRAY, Options.RECT))
        self.selection_buttons.append(self.create_btn("Circle", Colors.GRAY, Options.OVAL))
        self.selection_buttons.append(self.create_btn("Triangle", Colors.GRAY, Options.TRIANGLE))
        self.selection_buttons.append(self.create_btn("Right Triangle", Colors.GRAY, Options.RIGHT_TRIANGLE))
        self.selection_buttons.append(self.create_btn("Text Box", Colors.GRAY, Options.TEXT_BOX))
        self.selection_buttons.append(self.create_btn("Selector", Colors.GRAY, Options.SELECTOR))

        # events binding
        self.master.bind_all("<w>", lambda event: self.on_select(self.selection_buttons[7], Options.SELECTOR))

        # grid config
        self.columnconfigure(tuple(range(len(self.selection_buttons))), weight=1, uniform="a")
        self.rowconfigure(0, weight=1, uniform="a")

        # grid placement
        for i, button in enumerate(self.selection_buttons):
            button.grid(column=i, row=0, padx=10, pady=10, sticky="nsew")

    def on_select(self, button, option):
        self.app.canvas.selected = option
        for btn in self.selection_buttons:
            if btn == button:
                btn.configure(fg_color=Colors.BLACK)
            else:
                btn.configure(fg_color=Colors.GRAY)

    def create_btn(self, text, fg_color, option):
        draw_btn = ctk.CTkButton(self, width=50, height=40, text=text, corner_radius=5,
                                 fg_color=fg_color, hover_color=Colors.BLACK_LIGHT)
        draw_btn.configure(command=lambda: self.on_select(draw_btn, option))
        return draw_btn
