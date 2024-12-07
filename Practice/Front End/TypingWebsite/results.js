const AccuracyTextElement = document.getElementById("accuracy");
const wpmTextElement = document.getElementById("wpm");

let acc = Math.round(localStorage.getItem("accuracy"));
let wpm = Math.round(localStorage.getItem("wpm"));
AccuracyTextElement.innerHTML = `accuracy ${acc}%`;
wpmTextElement.innerHTML = `speed ${wpm} wpm`;

document.addEventListener("keydown", function (event) {
  let key = event.key;

  if (key == "Enter") {
    window.location.href = "./index.html";
  }
});
