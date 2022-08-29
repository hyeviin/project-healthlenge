/**
 * wirter: hyevin
 * 회원가입시 이메일 인증 번호 발송 동작
 */
let check;
let createdDate;

 $(function(){
	$("#mailToken").hide();
	$("#find-btn").hide();
	$("#btn-mail").click(function(){
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			beforeSend: function(xhr){xhr.setRequestHeader(header, token);}, 
			url:"/request-key/mail",
			type: "post",
			data: {email:$("#in-mail").val()},
			success:function(result){

				$("#mailToken").show();
				start(result+(1000*60*5));
			}
		});
	});

	$("#btn-mailtoken").click(function(){

		let inputKey= $(this).parent("div").children("input#key").val();

		$.get("/request-key/getKey", function(code){
			let msg= "인증실패";
			
			if(code == inputKey){
				msg= "🎉인증성공";
				check= 1;
				$(".time").hide();
				$("#key").hide();
				$("#btn-mailtoken").hide();
				clearTimeout(myTimeout);
				$.get("/request-key/remove", function(){});
			}
			
			$(".mail-msg").text(msg);
			$("#item-check").val(check);
			$("#find-btn").show();
		});//name생략버전임 get요청
	});
});

function start(targetTime){

	var seconds= (targetTime-new Date().getTime())/1000;
	var minute= Math.floor(seconds/60);
	var second= Math.floor(seconds%60);

	if(seconds > 1){
		$(".time").text(minute +":"+second);
		myTimeout = setTimeout(start, 1000,targetTime);
	}else{
		clearTimeout(myTimeout);
		$(".time").text("00:00");
		$.get("/request-key/remove",function(){});
	}
}