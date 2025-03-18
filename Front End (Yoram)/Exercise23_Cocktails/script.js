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

  createDropdownItem(text) {
    let itemE = document.createElement("div");
    itemE.classList.add("dropdown-item");

    let textE = document.createElement("span");
    textE.classList.add("text");
    textE.textContent = text;

    itemE.appendChild(textE);

    itemE.addEventListener("click", (event) => this.onClickItem(event));
    return itemE;
  }

  onClickItem(event) {
    this.element.removeChild(this.displayItem);

    this.displayItem = event.currentTarget.cloneNode(true);
    this.element.appendChild(this.displayItem);

    this.element.appendChild(this.displayItem);

    let ingredient = event.target.textContent;

    loadRecipes(ingredient);
  }

  addItem(itemE) {
    this.items.push(itemE);
    this.items_container.appendChild(itemE);
  }
}

let dropdown;
const container = document.querySelector("#container");
const maxRows = 15;

function createDropdown() {
  axios
    .get("https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list")
    .then((response) => {
      dropdown = new Dropdown();
      document.body.appendChild(dropdown.element);

      let ingredients = response.data.drinks;
      console.log(ingredients);

      ingredients.sort((a, b) =>
        a.strIngredient1.localeCompare(b.strIngredient1)
      );

      dropdown.displayItem = dropdown.createDropdownItem(
        ingredients[0].strIngredient1
      );
      dropdown.element.appendChild(dropdown.displayItem);

      ingredients.forEach((ingredient) => {
        dropdown.addItem(
          dropdown.createDropdownItem(ingredient.strIngredient1)
        );
      });
    })
    .catch((error) => {
      console.error(error);
    });
}

createDropdown();

function loadRecipes(ingredient) {
  const url = `https://www.thecocktaildb.com/api/json/v1/1/search.php?s=${encodeURIComponent(
    ingredient
  )}`;

  axios
    .get(url)
    .then((response) => {
      let cocktails = response.data.drinks;
      console.log(cocktails);

      createRecipes(cocktails);
    })
    .catch((error) => {
      console.error(error);
    });
}

function createRecipes(recipes) {
  container.innerHTML = "";

  for (let i = 0; i < recipes.length; i++) {
    let recipe = recipes[i];

    let recipeElement = document.createElement("div");
    let inter = ` <div class="recipe">
        <header>${recipe.strDrink}</header>
        <div class="flex-wrapper">
          <img
            class="image"
            src="${recipe.strDrinkThumb}"
            alt="image of ${recipe.strDrink}"
          />
          <table>
            <tr>
              <th>Ingredients</th>
              <th>Measure</th>
            </tr>`;

    for (let j = 0; j < maxRows; j++) {
      if (
        recipe[`strIngredient${j + 1}`] != null &&
        recipe[`strMeasure${j + 1}`] != null
      ) {
        inter += getFormatedRowHtml(
          recipe[`strIngredient${j + 1}`],
          recipe[`strMeasure${j + 1}`]
        ).trim();
      }
    }

    inter += `</table>
        </div>
        <div class="description">
          ${recipe.strInstructions}
        </div>
      </div>`;

    recipeElement.innerHTML = inter;
    container.appendChild(recipeElement);
  }
}

function getFormatedRowHtml(item1, item2) {
  return `<tr>
              <td>${item1}</td>
              <td>${item2}</td>
            </tr>`;
}
