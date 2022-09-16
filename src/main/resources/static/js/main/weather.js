const API_KEY = "a729f855a8412f6352e55d27a5978153";

function onGeoOk(position) {
    const lat = position.coords.latitude;
    const lng = position.coords.longitude;
    const url = `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lng}&appid=${API_KEY}&units=metric`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const weather = document.getElementById("weatherFristSpan");
            const city = document.querySelector("#weather span:last-child");
            const weatherIcon = document.querySelector("#weather img");
            weatherIcon.src = `http://openweathermap.org/img/w/${data.weather[0].icon}.png`
            city.innerText = `${data.name}ㅤ`;
            weather.innerText = ` / ${data.main.temp}℃ ㅤ`;
        });
}
function onGeoError() {
    alert("Can't find you. No weather for you.");
}

navigator.geolocation.getCurrentPosition(onGeoOk, onGeoError);
