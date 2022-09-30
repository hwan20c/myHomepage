const images = [
    "0.jpeg",
    "1.jpeg",
    "2.jpeg",
    "3.jpeg",
    "4.jpeg"
]
const chosenImage = images[Math.floor(Math.random() * images.length)];
const bgImage = document.createElement("img");
const footer = document.querySelector("#base_footer");
const todoBtn = document.getElementById("openBtn");

bgImage.src = `./img/main/${chosenImage}`;
document.body.style.backgroundImage = `url(${bgImage.src})`;
document.body.style.height = "100%";
document.body.style.backgroundPosition = "center";
document.body.style.backgroundRepeat = "no-repeat";
document.body.style.backgroundSize = "cover";

footer.className += " fixed-bottom";
footer.style.zIndex = "1";
todoBtn.style.zIndex = "2";