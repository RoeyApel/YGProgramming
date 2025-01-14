const WIDTH = window.innerWidth;
const HEIGHT = window.innerHeight;
const EDGES_MARGIN = 100;

let flyingButton = document.querySelector("#flying-btn");

let flyingButtonTxt = flyingButton.textContent;
let timeOut = false;
let canFly = true;

flyingButton.addEventListener("mouseover", (e) => {
  if (!canFly || timeOut) return;

  timeOut = true;
  setTimeout(() => {
    timeOut = false;
  }, 300);

  flyingButton.textContent = wordList[getRndNum(0, wordList.length - 1)];

  let initialLeft = parseInt(getComputedStyle(flyingButton).left, 10);
  let initialTop = parseInt(getComputedStyle(flyingButton).top, 10);

  let { newLeft, newTop } = getRndPosition(initialLeft, initialTop);

  flyingButton.style.left = newLeft + "px";
  flyingButton.style.top = newTop + "px";
});

flyingButton.addEventListener("mousedown", function () {
  canFly = false;
  flyingButton.textContent = "X _ X";

  const header = document.getElementById("header");

  header.classList.add("animateTyping");

  header.addEventListener(
    "animationend",
    function () {
      flyingButton.textContent = flyingButtonTxt;
      header.classList.remove("animateTyping");
      canFly = true;
    },
    { once: true }
  );
});

function getRndPosition(initialLeft, initialTop) {
  let rndXJump = getRndNum(140, 400);
  let rndYJump = getRndNum(140, 400);
  let rndXDir = getRndDir();
  let rndYDir = getRndDir();
  let newLeft = initialLeft + rndXJump * rndXDir;
  let newTop = initialTop + rndYJump * rndYDir;

  if (newLeft < EDGES_MARGIN || newLeft > WIDTH - EDGES_MARGIN) {
    newLeft = initialLeft + rndXJump * rndXDir * -1;
  }
  if (newTop < EDGES_MARGIN || newTop > HEIGHT - EDGES_MARGIN) {
    newTop = initialTop + rndYJump * rndYDir * -1;
  }
  return { newLeft, newTop };
}

function getRndNum(min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}
function getRndDir() {
  let rndNum = Math.floor(Math.random() * 9) + 1;

  if (rndNum > 5) {
    return -1;
  }
  return 1;
}

function legacyWay(initialLeft, initialTop, newLeft, newTop) {
  let currentLeft = initialLeft;
  let currentTop = initialTop;
  const interval = 12;
  let intervalID = setInterval(() => {
    currentLeft += (newLeft - initialLeft) / interval;
    currentTop += (newTop - initialTop) / interval;
    console.log(currentLeft + " " + currentTop + "|" + newLeft + " " + newTop);
    flyingButton.style.left = currentLeft + "px";
    flyingButton.style.top = currentTop + "px";
    if (
      Math.abs(currentLeft - newLeft) < 30 &&
      Math.abs(currentTop - newTop) < 30
    ) {
      clearInterval(intervalID);
    }
  }, interval);
}

const wordList = [
  "Nope",
  "Lol",
  "Loser",
  "No",
  "I bored",
  "Whatever",
  "Meh",
  "Sure",
  "Nah",
  "Oops",
  "Wait",
  "Bye",
  "Ugh",
  "Try me",
];
