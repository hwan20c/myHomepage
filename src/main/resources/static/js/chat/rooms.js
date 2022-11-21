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

});