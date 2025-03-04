import copy
import json
import os

from utils import show_error


def load_save(file_path, canvas):
    try:
        with open(file_path, "r") as save_file:
            data = json.load(save_file)

            canvas.change_canvas_color(data["background_color"])
            if "drawings" in data:
                drawings = data["drawings"]
                canvas.load_drawings(drawings)
            else:
                print("Error: No 'drawing' key found in the save file.")
    except FileNotFoundError:
        show_error("Can't load. Save is not found")
    except Exception as e:
        print(f"Error in file handling: {e}")


def save_as_json(file_path, drawings, canvas_color):
    drawings_dict = []

    for drawing in drawings:
        drawing_dict = copy.copy(drawing.__dict__)

        del drawing_dict["hitbox"]
        del drawing_dict["canvas"]
        del drawing_dict["id"]
        del drawing_dict["selected"]
        del drawing_dict["resized"]
        del drawing_dict["mouse_x_on_select"]
        del drawing_dict["mouse_y_on_select"]
        drawings_dict.append(drawing_dict)

    save_object = {"drawings": drawings_dict, "background_color": canvas_color}

    try:
        with open(file_path, "w") as save_file:
            json.dump(save_object, save_file, indent=4)
    except Exception as e:
        print(f"An error occurred while saving: {e}")
