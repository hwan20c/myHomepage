$(document).ready(function () {
  //local
  const sock = new SockJS("http://b0h1.com/ws/chat", null, {
    transports: ["websocket", "xhr-streaming", "xhr-polling"],
  });

  //aws
  // const sock = new SockJS("/ws/chat", null, {transports: ["websocket", "xhr-streaming", "xhr-polling"]});

  sock.onmessage = onMessage;
  sock.onopen = ipCheck;
  sock.onclose = onClose;

  $("#disconn").on("click", (e) => {
    disconnect();
  });

  $("#button-send").on("click", (e) => {
    send();
    scrollToBottom();
    $("#msg").focus();
  });

  $("#msg")
    .unbind("keypress")
    .bind("keypress", function (e) {
      if (e.which == 13) {
        send();
        e.stopPropagation();
        e.preventDefault();
        scrollToBottom();
      }
    });

  function send() {
    let msg = document.getElementById("msg");
    console.log(username + ":" + msg.value);
    sock.send(username + ":" + msg.value);
    msg.value = "";
  }

  //채팅창에서 나갔을 때
  function onClose(evt) {
    var str = username + ": 님이 방을 나가셨습니다.";
    sock.send(str);
    console.log(str);
  }

  //채팅창에 들어왔을 때
  let username = "";

  function ipCheck(callback) {
    return new Promise(function (resolve, reject) {
      $.getJSON(
        "https://api.ipify.org?format=jsonp&callback=?",
        function (json) {
          var ipArray = json.ip.split(".");
          username = ipArray[0] + "." + ipArray[1];
          resolve(json);
        }
      );
    });
  }

  ipCheck().then(function onOpen(evt) {
    var str = username + ": 님이 입장하셨습니다.";
    waitForSocketConnection(sock, function () {
      sock.send(str);
    });
    // heartbeat();
  });

  function waitForSocketConnection(socket, callback) {
    setTimeout(function () {
      if (socket.readyState === 1) {
        if (callback !== undefined) {
          callback();
        }
        return;
      } else {
        waitForSocketConnection(socket, callback);
      }
    }, 5);
  }

  function onMessage(msg) {
    var data = msg.data;
    var sessionId = null;
    //데이터를 보낸 사람
    var message = null;
    var arr = data.split(":");

    for (var i = 0; i < arr.length; i++) {
      console.log("arr[" + i + "]: " + arr[i]);
    }

    var cur_session = username;

    //현재 세션에 로그인 한 사람
    console.log("cur_session : " + cur_session);
    sessionId = arr[0];
    message = arr[1];

    console.log("sessionID : " + sessionId);
    console.log("cur_session : " + cur_session);

    //로그인 한 클라이언트와 타 클라이언트를 분류하기 위함
    if (sessionId == cur_session) {
      var str = "<div class='col-6'>";
      str += "<div class='alert alert-secondary'>";
      str += "<b>" + sessionId + " : " + message + "</b>";
      str += "</div></div>";
      $("#msgArea").append(str);
    } else {
      var str = "<div class='col-6'>";
      str += "<div class='alert alert-warning'>";
      str += "<b>" + sessionId + " : " + message + "</b>";
      str += "</div></div>";
      $("#msgArea").append(str);
    }
  }

  function scrollToBottom() {
    $("html,body").animate({ scrollTop: document.body.scrollHeight }, "fast");
  }

  function heartbeat() {
    if (!sock) return;
    if (sock.readyState !== 1) return;
    let today = new Date();
    sock.send(
      "heartbeat " +
        today.getHours() +
        "-" +
        today.getMinutes() +
        "-" +
        today.getSeconds()
    );
    console.log("heartbeat signal");
    setTimeout(heartbeat, 30000);
  }
});
