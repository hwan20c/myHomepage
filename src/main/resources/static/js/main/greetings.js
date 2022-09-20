const loginForm = document.querySelector("#login-form");
const loginInput = document.querySelector("#login-form input");
const greeting = document.querySelector("#greeting");
const greetingName = document.querySelector("#greeting-name");
const editBtn = document.querySelector("#edit-btn");

const HIDDEN_CLASSNAME = "hidden";
const USERNAME_KEY = "username";

function onLoginSubmit(event) {
    event.preventDefault();
    const username = loginInput.value;
    localStorage.setItem(USERNAME_KEY, username);
    loginForm.classList.add(HIDDEN_CLASSNAME);
    paintGreetings(username);
}

function paintGreetings(username) {
    greeting.innerText = `Hello, `;
    greetingName.innerText = username;
    greeting.classList.remove(HIDDEN_CLASSNAME);
    greetingName.classList.remove(HIDDEN_CLASSNAME);
    editBtn.classList.remove(HIDDEN_CLASSNAME);
}

const savedUsername = localStorage.getItem(USERNAME_KEY);

if(savedUsername === null) {
    loginForm.classList.remove(HIDDEN_CLASSNAME);
    loginForm.addEventListener("submit", onLoginSubmit);
} else {
    paintGreetings(savedUsername);
}

function editUserName() {
    editBtn.classList.add(HIDDEN_CLASSNAME);
    const editUsername = greetingName.innerText;
    greetingName.innerHTML = `<form id="edit-form"> <input required maxlength=15" id="edit-input" value='${editUsername}'/> </form>`;
    const editInput = document.getElementById('edit-input');
    editInput.focus();
    const val = editInput.value;
    editInput.value = '';
    editInput.value = val;

    const editForm = document.getElementById("edit-form");

    function setUsername(event) {
        event.preventDefault();
        loginForm.classList.remove(HIDDEN_CLASSNAME);
        loginForm.classList.add(HIDDEN_CLASSNAME);
        editForm.classList.add(HIDDEN_CLASSNAME);
        editBtn.classList.remove(HIDDEN_CLASSNAME);
        greetingName.innerText = editInput.value;
        localStorage.setItem(USERNAME_KEY, editInput.value);
    }

    editForm.addEventListener("submit", setUsername);
}

editBtn.addEventListener("click", editUserName);

