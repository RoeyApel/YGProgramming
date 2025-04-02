let teams;
let players;

const teamsDropdown = document.querySelector("#teams-selection");
const playersDropdown = document.querySelector("#players-selection");
const cardContainer = document.querySelector("#container-card");

teamsDropdown.addEventListener("change", (event) => {
  setPlayers(event.target.value);
});

playersDropdown.addEventListener("change", (event) => {
  createPlayerCard(event.target.value);
});

(function setTeams(url = "https://api.balldontlie.io/v1/teams") {
  axios(url, {
    headers: { Authorization: "976a404c-2e92-4f07-8a06-3df86e30925e" },
  })
    .then((response) => {
      teams = response.data.data.sort(function (a, b) {
        return a.full_name > b.full_name ? 1 : -1;
      });
      fillTeamsDropdown();
    })
    .catch((error) => {
      console.log(error);
    });
})();

function fillTeamsDropdown() {
  let options = "";

  options += "<option disabled selected value>select team...</option>";

  teams.forEach((team) => {
    options += `<option value="${team.id}">${team.full_name}</option>`;
  });

  teamsDropdown.innerHTML = options;
}

function fillPlayersDropdown() {
  let options = "";

  options += "<option disabled selected value>select player...</option>";

  players.forEach((player) => {
    options += `<option value="${player.first_name + " " + player.last_name}">
    ${player.first_name + " " + player.last_name}
    </option>`;
  });

  playersDropdown.innerHTML = options;
}

function setPlayers(teamId) {
  axios("https://api.balldontlie.io/v1/players?team_ids[]=" + teamId, {
    headers: { Authorization: "976a404c-2e92-4f07-8a06-3df86e30925e" },
  })
    .then((response) => {
      players = response.data.data.sort(function (a, b) {
        return a.first_name > b.first_name ? 1 : -1;
      });
      fillPlayersDropdown();
    })
    .catch((error) => {
      console.log(error);
    });
}

function createPlayerCard(player_name) {
  let player = findPlayerByName(player_name);

  let card = "";

  let url =
    "https://www.thesportsdb.com/api/v1/json/3/searchplayers.php?p=" +
    player_name;

  axios(url)
    .then((response) => {
      let imgUrl = response.data.player[0].strThumb;

      card = `<div class="card">
        <div class="header">${player_name}</div>
        <img
          src="${imgUrl}"
          alt="player image"
        />
        <div class="info">
          <div>Height: ${player.height}</div>
          <div>Jersey number: ${player.jersey_number}</div>
          <div>Position: ${player.position}</div>
        </div>
      </div>`;

      cardContainer.innerHTML = card;
    })
    .catch((error) => {
      console.log(error);
    });
}

function findPlayerByName(name) {
  let myPlayer;
  players.forEach((player) => {
    if (name == player.first_name + " " + player.last_name) {
      myPlayer = player;
    }
  });

  return myPlayer;
}
