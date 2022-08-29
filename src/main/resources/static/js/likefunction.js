/**
 *  챌린지 좋아요 기능!
 */
 
  
 $(function(){
	$("#btn-like").click(function(){
		console.log("result: " + $("#user").val());
		if($("#user").val()=="false"){
			if(confirm("로그인후 이용가능합니다. 로그인페이지 이동!")){
				location.href="/signin";
			}
			return;
		}
		like();
	});
});

function like(){
	
	let no= $(".no").text();
	let topic= $("#topic").text();
	let count= $(".count").val();
	console.log(count);
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({
			beforeSend: function(xhr){xhr.setRequestHeader(header, token);}, 
			url:"/common/"+ topic + "/like/" + no,
			type: "post",
			data: {
				"email": $(".email").text(),
				"count": count
			},
			success:function(result){
				if(result){
					console.log("DB성공!");
					$("#btn-like").css('background-color', '#FF4243');
					$("#btn-like").css('color', 'white');
				}else {
					console.log("삭제성공!");
					$("#btn-like").css('background-color', 'white');
					$("#btn-like").css('color', 'black');
				}
			},
	});
}