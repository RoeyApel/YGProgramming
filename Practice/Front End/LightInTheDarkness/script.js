const circle = document.querySelector("#circle");
const text = document.querySelector("#text");

let mouseX = 0;
let mouseY = 0;

document.addEventListener("mousemove", (event) => {
  mouseX = event.clientX;
  mouseY = event.clientY;

  let circleCenterX = circle.offsetWidth / 2;
  let circleCenterY = circle.offsetHeight / 2;

  circle.style.left = mouseX - circleCenterX + "px";
  circle.style.top = mouseY - circleCenterY + "px";
});

text.addEventListener("mouseover", () => {
  circle.classList.add("circle-active");
  console.log("in");
});

text.addEventListener("mouseout", () => {
  circle.classList.remove("circle-active");
  console.log("out");
});
