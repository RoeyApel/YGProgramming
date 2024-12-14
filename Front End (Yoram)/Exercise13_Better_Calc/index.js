let display_targil = document.querySelector("#display-targil");
let display_num_cur = document.querySelector("#display-num-cur");
let display_op_cur = document.querySelector("#display-op-cur");

let needClearing = false;

function addToDisplay(ch) {
  if (needClearing) {
    clearDisplay();
    needClearing = false;
  }

  display_targil.textContent += ch;

  if (isNaN(ch)) {
    display_op_cur.textContent = ch;
  } else {
    display_num_cur.textContent = ch;
    display_op_cur.textContent = "";
  }
}

function deleteFromDisplay() {
  if (needClearing) {
    clearDisplay();
    needClearing = false;
  } else {
    display_targil.textContent = display_targil.textContent.slice(0, -1);
  }
}

function clearDisplay() {
  display_targil.textContent = "";
  display_num_cur.textContent = "";
  display_op_cur.textContent = "";
}

function calc() {
  try {
    let result = Math.round(eval(display_targil.textContent) * 100) / 100;
    display_targil.textContent += "=" + result;
    display_num_cur.textContent = result;
    display_op_cur.textContent = "";
  } catch (error) {
    if (error instanceof SyntaxError) {
      display_targil.textContent += "=???";
      display_num_cur.textContent = "Syntax Error";
      display_op_cur.textContent = "";
    } else {
      throw error;
    }
  }

  needClearing = true;
}
