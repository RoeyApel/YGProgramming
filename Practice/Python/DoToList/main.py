from sys import maxsize

import customtkinter as ctk


class App(ctk.CTk):
    def __init__(self):
        super().__init__()

        self.title("To Do List")
        self.center_window()

        self.rowconfigure((0, 1), weight=1)
        self.columnconfigure(1, weight=1)

        self.list_frame = ListFrame(self)
        self.list_frame.grid(row=0, column=0, padx=10, pady=10, sticky="nsw", rowspan=2)

        self.add_btn = ctk.CTkButton(self, text="Add A Task", height=40, command=self.on_add_task)
        self.add_btn._font.configure(size=17)
        self.add_btn.grid(row=0, column=1, padx=0, pady=0)

        self.add_btn = ctk.CTkButton(self, text="Remove Checked Tasks", width=250, height=45, fg_color="#a51f35",
                                     hover_color="#821729", command=self.on_delete_tasks)
        self.add_btn._font.configure(size=17)
        self.add_btn.grid(row=1, column=1, padx=0, pady=0)

    def center_window(self):
        window_width = 800
        window_height = 700

        screen_width = self.winfo_screenwidth()
        screen_height = self.winfo_screenheight()

        position_x = int((screen_width / 2) - (window_width / 2))
        position_y = int((screen_height / 2) - (window_height / 2))

        self.geometry(f"{window_width}x{window_height}+{position_x}+{position_y}")

    def on_add_task(self):
        dialog = ctk.CTkInputDialog(text="Enter Task Description", title="New Task")
        task_des = dialog.get_input()
        if not task_des:
            return
        self.list_frame.add_task(task_des)

    def on_delete_tasks(self):
        checked_tasks = self.list_frame.get_checked_tasks()
        if not checked_tasks:
            return
        for ctask in checked_tasks:
            self.list_frame.remove_task(ctask)

        for idx, check_box in enumerate(self.list_frame.tasks):
            check_box.grid(row=idx, column=0, padx=(30, 35), pady=25, sticky="w")


class ListFrame(ctk.CTkFrame):
    def __init__(self, master):
        super().__init__(master,width=380)
        self.grid_propagate(False)

        self.tasks = []
        self.num_of_tasks = 0

    def add_task(self, des):
        print(len(self.tasks))
        check_box = ctk.CTkCheckBox(self, text=des, font=("Roboto", 18))
        check_box.grid(row=self.num_of_tasks, column=0, padx=(30, 35), pady=25, sticky="w")
        self.num_of_tasks += 1
        self.tasks.append(check_box)

    def remove_task(self, check_box):
        if check_box:
            self.tasks.remove(check_box)
            check_box.destroy()
            check_box = None
            self.num_of_tasks -= 1
        else:
            print("remove_task isn't working well!")

    def get_checked_tasks(self):
        checked_tasks = [task for task in self.tasks if task.get() == 1]
        return checked_tasks


if __name__ == "__main__":
    ctk.set_appearance_mode("dark")
    app = App()

    app.mainloop()
