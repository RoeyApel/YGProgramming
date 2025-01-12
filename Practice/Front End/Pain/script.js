const WIDTH = window.innerWidth;
const HEIGHT = window.innerHeight;
const EDGES_MARGIN = 100;

let flyingButton = document.querySelector("#flying-btn");

let initialLeft = getComputedStyle(flyingButton).left;
let initialTop = getComputedStyle(flyingButton).top;

let timeOut = false;

flyingButton.addEventListener("mouseover", (e) => {
  if (timeOut) {
    return;
  }
  timeOut = true;
  setTimeout(() => {
    timeOut = false;
  }, 300);

  initialLeft = parseInt(getComputedStyle(flyingButton).left, 10);
  initialTop = parseInt(getComputedStyle(flyingButton).top, 10);

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

  let currentLeft = initialLeft;
  let currentTop = initialTop;

  const interval = 10;
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
});

function limit(num, min, max) {
  num = Math.max(min, num);
  num = Math.min(max, num);
  return num;
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
