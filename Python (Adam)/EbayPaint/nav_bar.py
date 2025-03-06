from doctest import master

import customtkinter as ctk
from customtkinter import CTkInputDialog

from constants import Colors, Options

from tkinter import colorchooser

from utils import show_error


class NavBar(ctk.CTkFrame):
    def __init__(self, master):
        super().__init__(master=master)

        # variables
        self.app = master
        self.selection_buttons = []
        self.properties_buttons = []

        # components
        self.selection_buttons.append(self.create_selection_btn("Line", Colors.BLACK, Options.LINE))
        self.selection_buttons.append(self.create_selection_btn("Arrow", Colors.GRAY, Options.ARROW))
        self.selection_buttons.append(self.create_selection_btn("Rect", Colors.GRAY, Options.RECT))
        self.selection_buttons.append(self.create_selection_btn("Circle", Colors.GRAY, Options.OVAL))
        self.selection_buttons.append(self.create_selection_btn("Triangle", Colors.GRAY, Options.TRIANGLE))
        self.selection_buttons.append(self.create_selection_btn("Right Triangle", Colors.GRAY, Options.RIGHT_TRIANGLE))

        self.set_selector_btn = self.create_selection_btn("Selector", Colors.GRAY, Options.SELECTOR)
        self.selection_buttons.append(self.set_selector_btn)

        self.properties_buttons.append(self.create_color_picker_btn("bg_color", Options.BG_COLOR, Colors.BG_LIGHT))
        self.properties_buttons.append(self.create_color_picker_btn("br_color", Options.BR_COLOR, Colors.BORDER_MEDIUM))
        self.properties_buttons.append(self.create_color_picker_btn("txt_color", Options.TXT_COLOR, Colors.TEXT_MAIN))

        self.pick_canvas_color_btn = self.create_color_picker_btn("canvas_color", Options.CANVAS_COLOR, Colors.BG_LIGHT)
        self.properties_buttons.append(self.pick_canvas_color_btn)

        self.add_text_btn = self.create_add_text_btn("Add Text", Options.TXT_COLOR, Colors.TEXT_MAIN)
        self.properties_buttons.append(self.add_text_btn)

        self.change_size_btn = self.create_size_picker_btn("Change Size\nsize: 2", Colors.TEXT_SECONDARY)
        self.properties_buttons.append(self.change_size_btn)
        # events binding
        self.master.bind_all("<w>", lambda event: self.on_select(self.set_selector_btn, Options.SELECTOR))
        self.master.bind_all("<Control-b>", lambda event: self.on_adding_text())
        self.master.bind_all("<Control-q>", lambda event: self.on_size_pick(self.change_size_btn))

        # grid config
        self.columnconfigure(tuple(range(len(self.selection_buttons + self.properties_buttons))), weight=1, uniform="a")
        self.rowconfigure(0, weight=1, uniform="a")

        # grid placement
        for i, button in enumerate(self.selection_buttons + self.properties_buttons):
            button.grid(column=i, row=0, padx=8, pady=10, sticky="nsew")

    def on_select(self, button, option):
        self.app.canvas.selected = option
        for btn in self.selection_buttons:
            if btn == button:
                btn.configure(fg_color=Colors.BLACK)
            else:
                btn.configure(fg_color=Colors.GRAY)

    def create_selection_btn(self, text, fg_color, option):
        draw_btn = ctk.CTkButton(self, width=50, height=10, text=text, corner_radius=5,
                                 fg_color=fg_color, hover_color=Colors.BLACK_LIGHT)
        draw_btn.configure(command=lambda: self.on_select(draw_btn, option))
        return draw_btn

    def create_color_picker_btn(self, text, option, fg_color):
        btn = ctk.CTkButton(self, width=50, height=10, text=text, corner_radius=5,
                            fg_color=fg_color, hover_color=Colors.FLORAL_WHITE, text_color=Colors.BLACK)
        btn.configure(command=lambda: self.on_color_pick(btn, option, text))
        return btn

    def create_size_picker_btn(self, text, fg_color):
        btn = ctk.CTkButton(self, width=50, height=10, text=text, corner_radius=5,
                            fg_color=fg_color, hover_color=Colors.FLORAL_WHITE, text_color=Colors.BLACK)
        btn.configure(command=lambda: self.on_size_pick(btn))
        return btn

    def on_size_pick(self, btn):
        dialog = CTkInputDialog(title="size picker", text="pick a size:")
        try:
            size = dialog.get_input()
            if not size:
                return
            size = int(size)
            self.app.canvas.selected_thickness = size
            btn.configure(text=f"Change Size\nsize: {size}")
        except:
            show_error("Invaild Input!")

    def on_color_pick(self, btn, option, text):
        color_code = colorchooser.askcolor(title=f"Pick a {text}")

        if not color_code[1]:
            return

        btn.configure(fg_color=color_code[1])
        if option == Options.BR_COLOR:
            self.app.canvas.selected_outline_color = color_code[1]
        elif option == Options.BG_COLOR:
            self.app.canvas.selected_bg_color = color_code[1]
        elif option == Options.TXT_COLOR:
            self.app.canvas.selected_txt_color = color_code[1]
        elif option == Options.CANVAS_COLOR:
            self.app.canvas.change_canvas_color(color_code[1])

    def create_add_text_btn(self, text, option, fg_color):
        btn = ctk.CTkButton(self, width=50, height=40, text=text, corner_radius=5,
                            fg_color=fg_color, hover_color=Colors.FLORAL_WHITE, text_color=Colors.BLACK)
        btn.configure(command=self.on_adding_text)
        return btn

    def on_adding_text(self):
        dialog = CTkInputDialog(title="Add text", text="Enter Text:")
        text = dialog.get_input()

        if not text:
            return

        self.on_select(self.set_selector_btn, Options.SELECTOR)
        self.app.canvas.add_text(text)
