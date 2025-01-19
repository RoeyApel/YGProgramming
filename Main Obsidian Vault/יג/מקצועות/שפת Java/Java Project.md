Visuals
1. The board is a panel. game is painted.
2. Game images - Slot, Edge Slot, Characters, Walls.

Gameplay
1. Classes
	- Slot (slotNumber ex: row 2 col 5 = (slotNumber col + row * BoardCols) = 23, 
		Wall, Empty, Player).
	- Character (id, x, y, width, height).
	- Wall (id for identification and storing position ex: wall id = "02|03&01|02" means wall is    place in slots 2 and 3. id first slot number will always be the smallest of the two. also a function that converts id to position and one that does the opposite).
	- Move (Step - currentSlot and targetSlot, PlaceWall - placeableWall).
	- Game.
	- GameFrame.
	- GamePanel.
	- Board (9x9 slots).
	- Computer (Array of legalMoves, function to generate all legalMoves).
1. Enums
	- Images
2. The soldiers moving by dragging them to the right slot.
3. Walls are placed by clicking a slot left click till the wall is displayed in the right place around the slot, than right click to place.
4. The Board is a 2d array of Slots. Slot can be Empty Slot, Player Occupied or a Wall.
5. ArrayList (open to change cause removing suck - O(n)) of possible walls to place composed of walls ids. Every time a wall is being placed the wall is removed from this list.
---
1. 2d array 9x9 of type Slot.
2. Slot - slotNum = col + row * BoardCols. LeftWall, ButtomWall =  (Empty, None, Wall).
3. Board - 2d array of Slots (board), players positions (slotNum), arraylist of WallPositions type WallPosition.
4. WallPosition - startSlotNum, direction.
---

