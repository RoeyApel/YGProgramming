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
Mouse Events:
variables: moveSelectionActive, wallSelectionActive, lastSlotClicked. 
do always when clicked - calc col and row, lastSlot update at the end.
1. current player: 
	1. dectivate wallSelection (maybe add if wallSelectionActive is true)
	2. if moveSelectionActive than dectivate moveSelection
	3. else activate moveSelection 
2. marked spot:
	1. dectivate moveSelection
	2. move
	3. end turn
3. normal spot:
	1. if moveSelectionActive than dectivate moveSelection
	2. if lastSlotClicked == current slot than show next Wall Option
	3. else dectivate wallSelection and populate option queue with new options.
4. right click and lastSlotClicked == current slot and wallSelectionActive
	1. placeWall for the head of the wallOption queue
	2. dec number of walls for player 
	3. ****** ***add more checks before placing wall*** 
---
Computer:
1. function: makeMove, return move object.
2. functions: moveBot, placeWall.
3. function: canReachGoal(player), check if player can reach the goal using dfs for O(V+E). 
4. variables: board, bot, player, bot_shortest_path, player_shortest_path(updated with every board change).
5. makeMove- details: find the shortest path then logic block it's sara and then find the 2th shortest path and the the 3th and so on, until he doesn't have any other paths (*if he can't). check for all the paths in order if using one wall you can make it longer than 4 slots(check the first 4 blocks).
6. *hard blocked* - wall that when placed it make the path longer than 4 blocks.
7. totally blocked - can't reach the goal.
8. make move logic 
	1. find the shortest path.
	2. try to block 3 first slots in the path with wall . 
	3. if in one of them he is hard blocked, then store it somewhere.
	4. if there is a hard block then temporarily place the wall to make the hard block.
	5. and then do step 1-5 again.
	6. stop the loop when all the paths are explored meaning, the player is completely blocked and can't reach the goal.
	7. info about all the paths: first is block resistance and length.
	8. *if 0 walls to player than don't make any checks*
	9. *if the block (hard or total) is in the block itself than ignore it*

