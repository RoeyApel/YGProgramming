const labels = document.querySelectorAll(".label");
const txtInputs = document.querySelectorAll(".login-input");

for (let i = 0; i < txtInputs.length; i++) {
  txtInputs[i].onfocus = inputOnFocus;
}

function inputOnFocus() {
  for (let i = 0; i < labels.length; i++) {
    const element = labels[i];
  }
}
