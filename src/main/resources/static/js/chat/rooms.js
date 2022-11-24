$(document).ready(function(){

	if(roomName != null)
		alert(roomName.name + " 방이 개설되었습니다.");

  $(".btn-create").on("click", function (e){
    e.preventDefault();
    const name = $("input[name='roomName']").val();
    if(name == "") {
			alert("Please write the name.");
		} else {
    	$("form").submit();
		}
  });

	function removeRoom() {
    $.ajax({
      type: 'DELETE',
      url: _ctx + '/chat',
      headers: {
        'X-CSRF-TOKEN': token
      },
      data : { 'roomId' : roomId }
    }).done(function(answer) {
      alert("방이 삭제 되었습니다. // " + answer);
    }).fail(function(error) {
      console.log('[ Error Response ]\n' + error)
    });
  };

});