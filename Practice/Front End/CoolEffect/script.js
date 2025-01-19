const gridElement = document.querySelector("#grid");
const circleElement = gridElement.querySelector("#circle");
const circleWidth = 150;
const circleHeight = 150;

const alphabet = "abcdefghijklmnopqrstuvwxyz";
const evilAlphabet = "קראטוןםפשדגכעחלךתצמהנבסז";

let lettersElements = [];
let numOfEvilLetters = 0;
let mouseX;
let mouseY;
let cols = 12;
let rows = 8;

for (let i = 0; i < cols * rows; i++) {
  createLetter();
}

function createLetter() {
  let letter = getRandomLetter();

  const letterElement = document.createElement("div");

  if (Math.floor(Math.random() * 60) < 5) {
    numOfEvilLetters++;
    letterElement.classList.add("evil-letter");
    letter = getRandomEvilLetter();
    letterElement.addEventListener("click", () =>
      onClickEvilLetters(letterElement)
    );
  }

  letterElement.classList.add("letter");
  letterElement.textContent = letter;
  gridElement.appendChild(letterElement);
  lettersElements.push(letterElement);
}

function onClickEvilLetters(letterElement) {
  numOfEvilLetters--;

  letterElement.classList.remove("evil-letter");
  letterElement.textContent = getRandomLetter();
  letterElement.removeEventListener("click", () =>
    onClickEvilLetters(letterElement)
  );

  if (numOfEvilLetters == 0) {
    document.documentElement.style.setProperty("--blur-amount", "0px");
  }
}

function getRandomLetter() {
  return alphabet[Math.floor(Math.random() * alphabet.length)];
}

function getRandomEvilLetter() {
  return evilAlphabet[Math.floor(Math.random() * evilAlphabet.length)];
}

gridElement.addEventListener("mouseover", () => {
  circleElement.style.transform = "scale(1)";
});

gridElement.addEventListener("mouseout", () => {
  circleElement.style.transform = "scale(0.1)";
});

document.addEventListener("mousemove", (e) => {
  mouseX = e.clientX;
  mouseY = e.clientY;

  circleElement.style.left = mouseX - circleWidth / 2.0 + "px";
  circleElement.style.top = mouseY - circleHeight / 2.0 + "px";
});
