const loginInputs = document.querySelectorAll(".login-input");
const labels = document.querySelectorAll(".label");
const msg = document.querySelector("#msg");
const hangmanContainer = document.querySelector("#hangman-container");
const hangman = hangmanContainer.querySelector("#hangman");
const word = hangmanContainer.querySelector("#word");
let secretWord;

let letters = [];
let isHangmaning = false;
let currentHangmanPhase;
let lettersTyped = new Set();
let correctLetterCount;

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
const redColors = [
  "#8B0000", // Dark Red
  "#B22222", // Firebrick Red
  "#C04E47", // Dark Scarlet Red
  "#A52A2A", // Brown Red
  "#800000", // Maroon
  "#9C1C1C", // Deep Red
  "#5C0F0F", // Dark Burgundy
  "#B03060", // Dark Crimson
  "#990000", // Dark Firebrick
  "#65000B", // Deep Maroon
];

document.addEventListener("keydown", (event) => {
  if (!isHangmaning) return;

  let letterTyped = event.key;

  if (!isALetter(letterTyped)) return;

  if (isCorrectLetter(letterTyped) && isLetterNotFound(letterTyped)) {
    placeCorrectLetter(letterTyped);
    if (correctLetterCount == secretWord.length) {
      hangman.style.backgroundImage = "url('/images/9.svg')";
      hangman.style.backgroundColor = "#ffffff";
      hangman.style.transform = "scale(" + 3.5 + ") rotate(" + 360 + "deg)";
      isHangmaning = false;
    }
  } else if (isLetterNotFound(letterTyped)) {
    placeIncorrectLetter(letterTyped);
    changeHangmanPhase();
    if (currentHangmanPhase == 1) {
      hangman.style.transform = "scale(" + 3.5 + ")";
      hangman.style.backgroundColor = "#ffffff";
      isHangmaning = false;
    }
  }
});

function isCorrectLetter(letterTyped) {
  return secretWord.includes(letterTyped);
}

function isLetterNotFound(letterTyped) {
  return lettersTyped.has(letterTyped) == false;
}

function placeCorrectLetter(letterTyped) {
  lettersTyped.add(letterTyped);

  for (let i = 0; i < secretWord.length; i++) {
    if (secretWord[i] == letterTyped) {
      let color = getRndBlueColor();
      letters[i].textContent = letterTyped;
      letters[i].style.color = color;
      letters[i].style.borderBottomColor = color;
      correctLetterCount++;
    }
  }
}

function placeIncorrectLetter(letterTyped) {
  lettersTyped.add(letterTyped);

  let color = getRndRedColor();
  const incorrectLetter = document.createElement("div");
  incorrectLetter.textContent = letterTyped;
  incorrectLetter.classList.add("incorrect-letter");
  incorrectLetter.style.transform = "rotate(" + getRndAngel() + "deg)";
  incorrectLetter.style.left = getRndXPresentage() + "%";
  incorrectLetter.style.top = getRndYPresentage() + "%";
  incorrectLetter.style.fontSize = getRndSize() + "rem";
  incorrectLetter.style.color = color;
  document.body.appendChild(incorrectLetter);
}

function getRndSize() {
  let min = 1.5,
    max = 4.5;

  return Math.random() * (max - min + 1) + min;
}

function getRndXPresentage() {
  let min1 = 5,
    min2 = 75;
  let max1 = 25,
    max2 = 95;

  let rnd = Math.round(Math.random());

  if (rnd == 0) return Math.floor(Math.random() * (max1 - min1 + 1)) + min1;
  return Math.floor(Math.random() * (max2 - min2 + 1)) + min2;
}

function getRndYPresentage() {
  let min = 20,
    max = 80;

  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function changeHangmanPhase() {
  currentHangmanPhase--;
  hangman.style.backgroundImage = `url('/images/${currentHangmanPhase}.svg')`;
}

function startHangman() {
  secretWord = fetchRandomWord();
  correctLetterCount = 0;
  currentHangmanPhase = 8;
  hangmanContainer.style.display = "flex";
  msg.style.display = "block";
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

loginInputs[0].addEventListener("keydown", (event) => {
  if (event.key == "Enter") {
    loginInputs[1].focus();
  }
});

loginInputs[1].addEventListener("keydown", (event) => {
  if (event.key == "Enter") {
    if (!isHangmaning && loginInputs[1].value.trim() != "") {
      handleInputs();
      isHangmaning = true;
      startHangman();
    }
  }
});

function isALetter(input) {
  return /^[a-zA-Z]+$/.test(input) && input.length == 1;
}

function getRndBlueColor() {
  let min = 0;
  let max = blueColors.length - 1;
  return blueColors[Math.floor(Math.random() * (max - min + 1)) + min];
}

function getRndRedColor() {
  let min = 0;
  let max = redColors.length - 1;
  return redColors[Math.floor(Math.random() * (max - min + 1)) + min];
}

function getRndAngel() {
  let min = -70;
  let max = 70;
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

function fetchRandomWord() {
  let rndWord = wordList[getRandomIndex()];
  while (rndWord.length < 5 || rndWord.length > 7) {
    rndWord = wordList[getRandomIndex()];
  }
  return rndWord;
}
function getRandomIndex() {
  return Math.floor(Math.random() * wordList.length);
}

function handleInputs() {
  loginInputs[0].value = "";
  loginInputs[1].value = "";
  loginInputs[0].focus();
  loginInputs[0].blur();
  loginInputs[1].blur();
  loginInputs[0].style.pointerEvents = "none";
  loginInputs[1].style.pointerEvents = "none";
}
