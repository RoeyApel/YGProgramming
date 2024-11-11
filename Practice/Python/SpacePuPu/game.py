
import pygame
from sys import exit
import textImage
from textImage import TextImage

pygame.init()

SCREEN_WIDTH: int = 800
SCREEN_HEIGHT: int = 400

display = pygame.display
screen = display.set_mode((SCREEN_WIDTH, SCREEN_HEIGHT))
display.set_caption("Space Pu Pu")
clock = pygame.time.Clock()
font = pygame.font.Font(None, 50)

sky = pygame.image.load("graphics/sky.png")
ground = pygame.image.load("graphics/ground.png")
scoreTxt = TextImage(screen.get_width() /2 -50, 50, None, 50, "Score", "Black", False)


def render() -> None:
    screen.blit(sky, (0, 0))
    screen.blit(ground, (0, sky.get_height()))
    screen.blit(scoreTxt.surface, (scoreTxt.x, scoreTxt.y))


def update() -> None:
    pass


def eventsHandler() -> None:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            exit()


while True:
    render()
    update()
    eventsHandler()
    display.update()
    clock.tick(60)
