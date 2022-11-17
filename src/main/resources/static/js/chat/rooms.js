$(document).ready(function(){

  if(roomName != null)
      alert(roomName + "방이 개설되었습니다.");

  $(".btn-create").on("click", function (e){
      e.preventDefault();

      var name = $("input[name='name']").val();

      if(name == "")
          alert("Please write the name.")
      else
          $("form").submit();
  });

});