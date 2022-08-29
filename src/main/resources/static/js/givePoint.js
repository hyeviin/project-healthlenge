/**
 *  구매결정 버튼 클릭시 포인트 부여
 */
 
 $(function(){
	$("#btn-buyDone").click(givePoint);
});

function givePoint(){
	let price= $("#point-price").text();
	let ono= $("#point-ono").text();
	$.ajax({
			beforeSend: function(xhr){xhr.setRequestHeader(header, token);}, 
			url:"/order/givePoint",
			type: "post",
			data: {
				"price": price,
				"ono": ono
			},
			success:function(result){
				if(result){
					console.log("구매확정 됐습니다! 포인트가 부여됐습니다 :}");
				}
			}
	});
}