let body = document.body;
let display = document.querySelector("#display");
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
  disableEvilMode();
}

function on_calc() {
  try {
    let result = evaluate(display_targil.textContent);

    if (result == 666) {
      enableEvilMode();
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

function enableEvilMode() {
  display_targil.style.color = "#d33d4b";
  display_num_cur.style.color = "#d33d4b";
  body.style.backgroundImage = "url('images/evil_background.jpg')";
  display.style.backgroundColor = "#5b2222";
}

function disableEvilMode() {
  display_num_cur.style.color = "#3b3a49";
  display_targil.style.color = "#4a4767";
  body.style.backgroundImage = "url('images/background.jpg')";
  display.style.backgroundColor = "#dddddd";
}

function evaluate(targil) {
  let postfix = infixToPostfix(targil);
  console.log(targil);
  console.log(postfix);

  let stack = [];
  let num1, num2, result;

  for (let i = 0; i < postfix.length; i++) {
    if (!isOperator(postfix[i])) {
      stack.push(postfix[i]);
    } else {
      num2 = stack.pop();
      num1 = stack.pop();
      result = calc(num1, num2, postfix[i]);
      stack.push(result);
    }
  }
  result = stack.pop();
  console.log(result);

  return Math.round(result * 100) / 100;
}

function infixToPostfix(targil) {
  let precedence = { "+": 1, "-": 1, "/": 2, "*": 2 };
  let stack = [];
  let postfix = [];

  let i = 0;
  let num = "";

  while (i < targil.length) {
    while (!isOperator(targil[i]) && i < targil.length) {
      num += targil[i];
      i++;
    }

    if (num != "") {
      postfix.push(parseFloat(num));
      num = "";
    }

    if (i >= targil.length) {
      break;
    }

    while (
      !isEmpty(stack) &&
      precedence[targil[i]] <= precedence[stack[stack.length - 1]]
    ) {
      postfix.push(stack.pop());
    }
    stack.push(targil[i]);

    i++;
  }

  while (!isEmpty(stack)) {
    postfix.push(stack.pop());
  }

  return postfix;
}
function isOperator(ch) {
  return ch == "+" || ch == "-" || ch == "/" || ch == "*";
}
function getLast(array) {
  return array[array.length - 1];
}
function isEmpty(array) {
  return array.length === 0;
}

function calc(num1, num2, operator) {
  if (operator == "+") {
    return num1 + num2;
  } else if (operator == "-") {
    return num1 - num2;
  } else if (operator == "/") {
    return num1 / num2;
  } else if (operator == "*") {
    return num1 * num2;
  }
}
