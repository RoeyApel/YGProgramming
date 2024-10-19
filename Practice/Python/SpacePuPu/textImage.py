import pygame.font


class TextImage:
    def __init__(self, x, y, font, fontSize, text, textColor, antialiasing):
        self.x = x
        self.y = y
        self.font = font
        self.fontSize = fontSize
        self.text = text
        self.textColor = textColor
        self.antialiasing = antialiasing
        font = pygame.font.Font(self.font, self.fontSize)
        self.surface = font.render(self.text, self.antialiasing, self.textColor)
