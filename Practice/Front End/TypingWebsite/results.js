import { statistics } from "./script.js";

const AccuracyTextElement = document.getElementById("accuracy");
const wpmTextElement = document.getElementById("wpm");

AccuracyTextElement.innerHTML = "Accuracy: " + statistics.accuracy + "%";
wpmTextElement.innerHTML = "WPM: " + statistics.wpm;

window.addEventListener("keydown", (event) => {
  AccuracyTextElement.innerHTML = "Accuracy: " + statistics.accuracy + "%";
  wpmTextElement.innerHTML = "WPM: " + statistics.wpm;
  console.log("fdgtrfhgfdh");
});
