class Dropdown {
  constructor() {
    this.items = [];
    this.items_container = document.createElement("div");
    this.items_container.classList.add("items-container");

    this.element = document.createElement("div");
    this.element.classList.add("dropdown");

    this.displayItem = null;
    this.element.appendChild(this.items_container);
  }

  createDropdownItem(text, flagUrl) {
    let itemE = document.createElement("div");
    itemE.classList.add("dropdown-item");

    let imageE = document.createElement("img");
    imageE.classList.add("image");
    imageE.src = flagUrl;

    let textE = document.createElement("span");
    textE.classList.add("text");
    textE.textContent = text;

    imageE.onerror = () => {
      imageE.src =
        "https://d.newsweek.com/en/full/2469632/donald-trump-hugs-us-flag.jpg";
      textE.textContent = "Error In Loading Image";
    };

    itemE.appendChild(imageE);
    itemE.appendChild(textE);

    itemE.addEventListener("click", (event) => this.onClickItem(event));
    return itemE;
  }

  onClickItem(event) {
    this.element.removeChild(this.displayItem);

    this.displayItem = event.currentTarget.cloneNode(true);
    this.element.appendChild(this.displayItem);

    this.element.appendChild(this.displayItem);
    console.log(event.target);
  }

  addItem(itemE) {
    this.items.push(itemE);
    this.items_container.appendChild(itemE);
  }
}

let dropdown;

function start() {
  axios
    .get("https://countriesnow.space/api/v0.1/countries/info?returns=flag")
    .then((response) => {
      dropdown = new Dropdown();
      document.body.appendChild(dropdown.element);

      let countries = response.data.data;
      console.log(countries);

      countries.sort((a, b) => a.name.localeCompare(b.name));

      dropdown.displayItem = dropdown.createDropdownItem(
        countries[0].name,
        countries[0].flag
      );
      dropdown.element.appendChild(dropdown.displayItem);

      countries.forEach((country) => {
        dropdown.addItem(
          dropdown.createDropdownItem(country.name, country.flag)
        );
      });
    })
    .catch((error) => {
      console.error(error);
    });
}

start();
