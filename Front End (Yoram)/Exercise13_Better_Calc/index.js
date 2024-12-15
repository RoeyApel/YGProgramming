let body = document.body;
let display_targil = document.querySelector("#display-targil");
let display_num_cur = document.querySelector("#display-num-cur");
let display_op_cur = document.querySelector("#display-op-cur");

let needClearing = false;
let current_num = "";

function addToDisplay(ch) {
  if (needClearing) {
    clearDisplay();
    needClearing = false;
  }

  display_targil.textContent += ch;

  if (isNaN(ch) && ch != ".") {
    display_op_cur.textContent = ch;
    current_num = "";
  } else {
    current_num += ch;
    display_num_cur.textContent = current_num;
    display_op_cur.textContent = "";
  }
}

function deleteFromDisplay() {
  if (needClearing) {
    clearDisplay();
    needClearing = false;
    return;
  }

  let deletedCh = display_targil.textContent.charAt(
    display_targil.textContent.length - 1
  );

  display_targil.textContent = display_targil.textContent.slice(0, -1);

  if (isNaN(deletedCh) && deletedCh != ".") {
    display_op_cur.textContent = "";
  } else {
    current_num = current_num.slice(0, -1);
    display_num_cur.textContent = current_num;
  }
}

function clearDisplay() {
  current_num = "";
  display_targil.textContent = "";
  display_num_cur.textContent = "";
  display_op_cur.textContent = "";
  display_num_cur.style.color = "#3b3a49";
  display_targil.style.color = "#4a4767";
  body.style.backgroundImage = "url('images/background.jpg')";
}

function calc() {
  try {
    let result = Math.round(eval(display_targil.textContent) * 100) / 100;

    if (result == 666) {
      display_targil.style.color = "#c9324c";
      display_num_cur.style.color = "#c9324c";
      body.style.backgroundImage = "url('images/evil_background.jpg')";
    }

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
