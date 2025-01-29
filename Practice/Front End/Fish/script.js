const aquarium = document.querySelector("#aquarium");

const previewFish = document.querySelector("#preview-fish");

const colorPicker = document.querySelector("#color-picker");
const speedPicker = document.querySelector("#speed-picker");
const sizePicker = document.querySelector("#size-picker");
const createBtn = document.querySelector("#create-btn");

let fishs = [];

setInterval(() => {
  fishs.forEach((fish) => {
    move(fish);
  }, 1000 / 60);
});

function move(fish) {
  const efish = fish.element;
  let currentX = parseInt(window.getComputedStyle(efish).left);
  let currentY = parseInt(window.getComputedStyle(efish).top);

  if (
    currentX + fish.vx >= aquarium.clientWidth - 100 ||
    currentX + fish.vy <= 100
  ) {
    efish.style.transform = `rotate(${180}deg)`;
    fish.vx *= -1;
  }
  if (
    currentY + fish.vx >= aquarium.clientHeight - 100 ||
    currentY + fish.vy <= 100
  ) {
    efish.style.transform = `rotate(${180}deg)`;
    fish.vy *= -1;
  }

  efish.style.left = currentX + fish.vx + "px";
  efish.style.top = currentY + fish.vy + "px";
}

createBtn.addEventListener("click", (event) => {
  let speed = parseFloat(speedPicker.value);
  let size = parseFloat(sizePicker.value);
  let color = colorPicker.value;
  let x = getRandom(100, aquarium.clientWidth - 100);
  let y = getRandom(100, aquarium.clientHeight - 100);

  createFish(x, y, size, color, speed);
});

function createFish(x, y, size, color, speed) {
  const fish = document.createElement("div");
  fish.classList.add("fish");
  fish.style.backgroundColor = color;
  fish.style.left = x + "px";
  fish.style.top = y + "px";
  fish.style.transform = `scale(${Math.max(1, size / 5)})`;

  const angle = Math.random() * Math.PI * 2;
  const vx = speed / 10;
  const vy = speed / 10;

  const eye = document.createElement("div");
  eye.classList.add("eye");

  const tail = document.createElement("div");
  tail.classList.add("tail");

  const tailPart1 = document.createElement("div");
  const tailPart2 = document.createElement("div");
  tail.append(tailPart1, tailPart2);

  fish.append(eye, tail);

  aquarium.appendChild(fish);
  fishs.push({ element: fish, vx, vy });
}

function getRandom(min, max) {
  return Math.floor(Math.random() * (max - min)) + min;
}
