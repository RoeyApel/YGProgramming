const clockElement = document.querySelector("#clock");
const popupElement = document.querySelector("#popup");
const closeButton = document.querySelector("#btn-close");
let secondsPassed = 0;

document.addEventListener("DOMContentLoaded", startTimer);

function startTimer() {
  setInterval(updateTimer, 1000);
  setTimeout(openPopup, 5000);
}

function updateTimer() {
  secondsPassed++;

  let hours = Math.floor(secondsPassed / 3600);
  let minutes = Math.floor((secondsPassed % 3600) / 60);
  let seconds = secondsPassed % 60;

  clockElement.textContent = `${hours}:${minutes}:${seconds}`;
}

function openPopup() {
  popupElement.classList.add("open-animation");
  popupElement.style.display = "flex";
}

closeButton.addEventListener("click", closePopup);

function closePopup() {
  popupElement.classList.remove("open-animation");
  popupElement.style.display = "none";
}
