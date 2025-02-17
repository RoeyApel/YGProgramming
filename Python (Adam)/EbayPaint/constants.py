from dataclasses import dataclass
from enum import Enum


@dataclass(frozen=True)
class Colors:
    GRAY = "#A9A9A9"
    BLACK = "#1A1A1A"
    BLACK_LIGHT = "#2B2B2B"

    WHITE = "#FFFFFF"
    WHITE_SMOKE = "#F5F5F5"
    SNOW = "#FFFAFA"
    IVORY = "#FFFFF0"
    SEASHELL = "#FFF5EE"
    GHOST_WHITE = "#F8F8FF"
    FLORAL_WHITE = "#FFFAF0"
    ANTIQUE_WHITE = "#FAEBD7"

    # Background Colors (light tones):
    BG_LIGHT = "#F5F5F5"  # Soft off-white background
    BG_MEDIUM = "#E0E0E0"  # Neutral light gray
    BG_ACCENT = "#D3D3D3"  # Slightly darker for depth

    # Border Colors (subtle contrast):
    BORDER_LIGHT = "#C0C0C0"  # Very light gray border
    BORDER_MEDIUM = "#A9A9A9"  # Medium gray for definition
    BORDER_DARK = "#808080"  # Visible but not harsh

    # Accent Colors (muted pastels for highlights):
    ACCENT_BLUE = "#9AC8D3"  # Soft sky blue
    ACCENT_GREEN = "#B8D8BA"  # Muted sage
    ACCENT_PINK = "#E6C4D5"  # Dusty rose

    # Text Colors:
    TEXT_MAIN = "#404040"  # Dark gray for readability
    TEXT_SECONDARY = "#606060"  # Medium gray


class Options(Enum):
    LINE = 1
    RECT = 2
    OVAL = 3
    TRIANGLE = 4
    RIGHT_TRIANGLE = 5
    TEXT_BOX = 6
    ARROW = 7
    SELECTOR = 8
    BG_COLOR = 9
    BR_COLOR = 10
    TXT_COLOR = 11
