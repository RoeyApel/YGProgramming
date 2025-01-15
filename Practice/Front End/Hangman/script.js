const loginInputs = document.querySelectorAll(".login-input");
const labels = document.querySelectorAll(".label");

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
