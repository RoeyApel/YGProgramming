import pygame
from pygame import SurfaceType

pygame.init()

SCREEN_WIDTH: float = 1920 * 0.5
SCREEN_HEIGHT: float = 1080 * 0.5

screen: SurfaceType = pygame.display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
while True:
    pygame.display.update()
