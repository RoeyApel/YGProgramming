const SIZE = 8;
const NUM_OF_PIECES = 24;
const slotLightColor = "#c6c660";
const slotDarkColor = "#77a26d";

const gridElement = document.createElement("div");
gridElement.classList.add("grid");
document.body.appendChild(gridElement);

let slots = [];
let pieces = [];

createBoard();
placePieces();

function createBoard() {
  for (let i = 0; i < SIZE; i++) {
    let row = [];

    for (let j = 0; j < SIZE; j++) {
      const slotElement = document.createElement("div");
      slotElement.classList.add("slot");

      slotElement.style.backgroundColor =
        (j + i) % 2 == 0 ? slotLightColor : slotDarkColor;

      gridElement.appendChild(slotElement);
      row.push(slotElement);
    }

    slots.push(row);
  }
}

function placePieces() {
  let j = 0;

  for (let i = 0; i < 3; i++) {
    for (let j = (i + 1) % 2; j < SIZE; j += 2) {
      const pieceElement = document.createElement("div");
      pieceElement.classList.add("white-piece");

      pieces.push(pieceElement);
      slots[i][j].appendChild(pieceElement);
    }
  }

  for (let i = 5; i < SIZE; i++) {
    for (let j = i % 2; j < SIZE; j += 2) {
      const pieceElement = document.createElement("div");
      pieceElement.classList.add("red-piece");

      pieces.push(pieceElement);
      slots[i][j].appendChild(pieceElement);
    }
  }
}
