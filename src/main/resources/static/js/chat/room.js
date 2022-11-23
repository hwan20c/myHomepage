$(document).ready(function(){
  let username = localStorage.getItem("username");

  if(username === null) {
    const randomnumber = new Uint16Array(1);
    window.crypto.getRandomValues(randomnumber);
    username = `Guest${randomnumber[0]}`;
    localStorage.setItem("username", username);
  }

  console.log(roomName + ", " + roomPassword + ", " + roomId + ", " + username);
  const sockJs = new SockJS("/stomp/chat");
  //1. SockJS를 내부에 들고있는 stomp를 내어줌
  const stomp = Stomp.over(sockJs);

  //2. connection이 맺어지면 실행
  stomp.connect({}, function (){
    console.log("STOMP Connection")

    //4. subscribe(path, callback)으로 메세지를 받을 수 있음
    stomp.subscribe("/sub/chat/room/" + roomId, function (chat) {
    let content = JSON.parse(chat.body);

    let writer = content.writer;
    let str = '';
    let message = content.message;

    if(writer === username) {
      str = "<div class='col-6'>";
      str += "<div class='alert alert-secondary'>";
      str += "<b>" + writer + " : " + message + "</b>";
      str += "</div></div>";
      $("#msgArea").append(str);
    } else {
      str = "<div class='col-6'>";
      str += "<div class='alert alert-warning'>";
      str += "<b>" + writer + " : " + message + "</b>";
      str += "</div></div>";
      $("#msgArea").append(str);
    }

    });

    //3. send(path, header, message)로 메세지를 보낼 수 있음
    stomp.send('/pub/chat/enter', {}, JSON.stringify({roomId: roomId, writer: username}))
  });

  $("#button-send").on("click", function(e){
    send();
    scrollToBottom();
    $("#msg").focus();
  });

  $("#msg").unbind('keypress').bind('keypress', function(e){
		if (e.which == 13) {
      send();
			e.stopPropagation();
			e.preventDefault();
			scrollToBottom();
      $("#msg").focus();
		}
	});

  function scrollToBottom() {
		$('html,body').animate({scrollTop: document.body.scrollHeight}, "fast");
	};

  function send() {
    let msg = document.getElementById("msg");
    console.log(username + ":" + msg.value);
    stomp.send('/pub/chat/message', {}, JSON.stringify({roomId: roomId, message: msg.value, writer: username}));
    msg.value = '';
  }

});

function passwordCheck(){
  let password = prompt("Please enter the password.");
  if (password==="ilikepie"){

  } else if (password!='' && password!=null) {
    while(password !=="ilikepie"){
        password = prompt("Please enter the password.");
    }
    window.location="realpage.html";
  }
}

window.onload=passwordCheck;