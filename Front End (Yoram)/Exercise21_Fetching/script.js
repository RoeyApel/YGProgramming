let container = document.querySelector("#container");

function createGallery(countries) {
  countries = countries.slice(0, 63);
  countries.forEach((country) => {
    container.appendChild(createCard(country.name, country.flag));
  });
}

function createCard(countryName, flagImageUrl) {
  const card = document.createElement("div");
  card.classList.add("card");

  const flagImage = document.createElement("img");
  flagImage.classList.add("img");
  flagImage.src = flagImageUrl;

  const label = document.createElement("div");
  label.classList.add("label");
  label.textContent = countryName;

  flagImage.onerror = () => {
    flagImage.src =
      "https://d.newsweek.com/en/full/2469632/donald-trump-hugs-us-flag.jpg";
    label.textContent = "Error In Loading Image";
  };

  card.appendChild(flagImage);
  card.appendChild(label);

  return card;
}

(function fetchData() {
  axios
    .get("https://countriesnow.space/api/v0.1/countries/info?returns=flag")
    .then((response) => {
      createGallery(response.data.data);
    })
    .catch((error) => {
      console.error(error);
    });
})();
