$(document).ready(function(){

	if(roomName != null)
		alert(roomName.name + " 방이 개설되었습니다.");
 
  $('[name=delete_btn]').click(function(){
    const roomId = ($(this).closest('li').data('value'));
    removeRoom(roomId);
    return false;
  });

  function removeRoom(roomId) {
    $.ajax({
      type: 'DELETE',
      url: _ctx + '/chat',
      headers: {
        'X-CSRF-TOKEN': token
      },
      data : { 'roomId' : roomId }
    }).done(function(answer) {
      alert("방이 삭제 되었습니다. // " + answer);
      location.reload();
    }).fail(function(error) {
      console.log('[ Error Response ]\n' + error)
    });
  };

});