from dataclasses import dataclass
from enum import Enum


@dataclass(frozen=True)
class Colors:
    GRAY: str = "#A9A9A9"
    BLACK: str = "#1A1A1A"
    BLACK_LIGHT: str = "#2B2B2B"


class Options(Enum):
    LINE = 1
    RECT = 2
    OVAL = 3
    TRIANGLE = 4
    RIGHT_TRIANGLE = 5
    TEXT_BOX = 6
    ARROW = 7
    SELECTOR = 8
