const loginInputs = document.querySelectorAll(".login-input");
const labels = document.querySelectorAll(".label");
const hangmanContainer = document.querySelector("#hangman-container");
const hangman = hangmanContainer.querySelector("#hangman");
const word = hangmanContainer.querySelector("#word");
const secretWord = "batman";

let letters = [];
let isHangmaning = false;
let currentHangmanPhase;
let correctLettersTyped = new Set();
const colors = [
  "#FF6347", // Tomato
  "#FF4500", // Orange Red
  "#FFD700", // Gold
  "#ADFF2F", // Green Yellow
  "#32CD32", // Lime Green
  "#3CB371", // Medium Sea Green
  "#00CED1", // Dark Turquoise
  "#1E90FF", // Dodger Blue
  "#8A2BE2", // Blue Violet
  "#FF1493", // Deep Pink
  "#FF69B4", // Hot Pink
  "#C71585", // Medium Violet Red
  "#D2691E", // Chocolate
  "#8B4513", // Saddle Brown
  "#A52A2A", // Brown
  "#B22222", // Firebrick
  "#FF8C00", // Dark Orange
  "#7FFF00", // Chartreuse
  "#00FA9A", // Medium Spring Green
  "#00BFFF", // Deep Sky Blue
  "#DC143C", // Crimson
  "#9932CC", // Dark Orchid
  "#FF00FF", // Fuchsia
  "#8B0000", // Dark Red
  "#F0E68C", // Khaki
  "#2E8B57", // Sea Green
  "#6495ED", // Cornflower Blue
  "#C71585", // Medium Violet Red
  "#00FF7F", // Spring Green
  "#6A5ACD", // Slate Blue
];
const blueColors = [
  "#1E2A47", // Darker Blue
  "#1D3B8A", // Dark Royal Blue
  "#1F4D89", // Dark Sky Blue
  "#2A4F91", // Darker Light Sky Blue
  "#2B65B9", // Dark Very Light Blue
  "#1A2B7D", // Dark Blue (Mid)
  "#3A3F8D", // Dark Indigo Blue
  "#3A47B9", // Dark Periwinkle Blue
  "#2F56B2", // Dark Sky Blue (Medium)
  "#3E2B97", // Dark Violet Blue
];

document.addEventListener("keydown", (event) => {
  if (!isHangmaning) return;

  let letterTyped = event.key;

  if (!isALetter(letterTyped)) return;

  if (isCorrectLetter(letterTyped) && isLetterNotFound(letterTyped)) {
    placeLetter(letterTyped);
  } else {
    changeHangmanPhase();
  }
});

function isCorrectLetter(letterTyped) {
  return secretWord.includes(letterTyped);
}

function isLetterNotFound(letterTyped) {
  return correctLettersTyped.has(letterTyped) == false;
}

function placeLetter(letterTyped) {
  correctLettersTyped.add(letterTyped);

  for (let i = 0; i < secretWord.length; i++) {
    if (secretWord[i] == letterTyped) {
      letters[i].textContent = letterTyped;
      letters[i].style.color = getRndColor();
    }
  }
}

function changeHangmanPhase() {
  currentHangmanPhase = Math.max(1, currentHangmanPhase - 1);
  hangman.style.backgroundImage = `url('/images/${currentHangmanPhase}.svg')`;
}

function startHangman() {
  currentHangmanPhase = 8;
  hangmanContainer.style.display = "flex";
  createWordTemplate();
}

function createWordTemplate() {
  for (let i = 0; i < secretWord.length; i++) {
    const letter = document.createElement("div");
    letter.classList.add("letter");
    letters.push(letter);
    word.appendChild(letter);
  }
}

loginInputs.forEach((input, index) => {
  const label = labels[index];

  input.addEventListener("focus", () => {
    label.style.animation = "label_animation 0.5s 1 forwards";
  });

  input.addEventListener("blur", () => {
    if (input.value == "") {
      label.style.animation = "";
    }
  });
});

loginInputs[1].addEventListener("keydown", (event) => {
  if (event.key == "Enter") {
    if (!isHangmaning && loginInputs[1].value.trim() != "") {
      loginInputs[1].value = "";
      loginInputs[1].blur();
      isHangmaning = true;
      startHangman();
    }
  }
});

function isALetter(input) {
  return /^[a-zA-Z]+$/.test(input) && input.length == 1;
}

function getRndColor() {
  let min = 0;
  let max = blueColors.length - 1;
  return blueColors[Math.floor(Math.random() * (max - min + 1)) + min];
}
