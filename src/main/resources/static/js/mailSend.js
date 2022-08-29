/**
 * 
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

				//alert(result);
				//cteatedDate= new Date(result);
				//console.log(result);
				//console.log(createdDate);
				$("#mailToken").show();
				start(result+(1000*60*5));
			}
		});
	});
	
	
	
	$("#btn-mailtoken").click(function(){
		//console.log("입력한 코드: " + $(this).parent("div").children("input#key").val());
				
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
	//var targetTime=ctime+(1000*60*5);
	//var currTime=new Date();
	//console.log(targetTime);
	//console.log(currTime.getTime());
	var seconds= (targetTime-new Date().getTime())/1000;
	var minute= Math.floor(seconds/60);
	var second= Math.floor(seconds%60);
	
	//console.log(minute +":"+second);
	//console.log("차이:"+(targetTime-currTime));//300,000
	if(seconds>1){
		$(".time").text(minute +":"+second);
		myTimeout = setTimeout(start, 1000,targetTime);
	}else{
		clearTimeout(myTimeout);
		$(".time").text("00:00");
		$.get("/request-key/remove",function(){});
	}
}
