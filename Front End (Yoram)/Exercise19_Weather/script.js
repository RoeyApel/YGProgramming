const containerElement = document.createElement("div");
containerElement.classList.add("container");
document.body.appendChild(containerElement);

let cardElement;

createGallery();

function createGallery() {
  arr.forEach((weatherInfo) => {
    cardElement = createCard(
      weatherInfo.timepoint,
      weatherInfo.cloudcover,
      weatherInfo.wind10m.direction,
      weatherInfo.wind10m.speed,
      weatherInfo.temp2m
    );
    containerElement.appendChild(cardElement);
  });
}

function createCard(timepoint, cloudcover, direction, speed, temp2m) {
  const cardElement = document.createElement("div");
  cardElement.classList.add("card");

  let wrapperElement = document.createElement("div");
  wrapperElement.classList.add("wrapper");

  let headerElement = document.createElement("div");
  headerElement.classList.add("header");
  headerElement.textContent = "Wind";
  wrapperElement.appendChild(headerElement);

  let displayElement = document.createElement("div");
  displayElement.textContent = "direction: " + direction;
  wrapperElement.appendChild(displayElement);

  displayElement = document.createElement("div");
  displayElement.textContent = "speed: " + speed;
  wrapperElement.appendChild(displayElement);

  cardElement.appendChild(wrapperElement);

  wrapperElement = document.createElement("div");
  wrapperElement.classList.add("wrapper");

  headerElement = document.createElement("div");
  headerElement.classList.add("header");
  headerElement.textContent = "Rain";
  wrapperElement.appendChild(headerElement);

  displayElement = document.createElement("img");
  let imgName = getRainImg(cloudcover);
  displayElement.src = `images/${imgName}.png`;
  displayElement.style.width = "50px";
  wrapperElement.appendChild(displayElement);

  cardElement.appendChild(wrapperElement);

  wrapperElement = document.createElement("div");
  wrapperElement.classList.add("wrapper");

  headerElement = document.createElement("div");
  headerElement.classList.add("header");
  headerElement.textContent = "Temp";
  wrapperElement.appendChild(headerElement);

  displayElement = document.createElement("div");
  displayElement.textContent = temp2m;
  wrapperElement.appendChild(displayElement);

  cardElement.appendChild(wrapperElement);

  console.log(cardElement);
  return cardElement;
}

function getRainImg(cloudcover) {
  return cloudcover >= 5 ? "rainy" : "dry";
}
