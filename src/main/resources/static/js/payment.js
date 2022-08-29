/**
 * 
 */
 
 $(function(){
	
	//$("#btn-payment").click(paymentPage);
	$("#btn-payment").click(requestPay);
});

function requestPay() {
	// IMP.request_pay(param, callback) 결제창 호출
	
	let r= confirm("결제하시겠습니까?");
	
	if(!r){
		return;
	}

	var IMP = window.IMP; // 생략 가능
    IMP.init("imp80635570"); // 예: imp00000000
    
    
	IMP.request_pay({ // param
		pg: "kakaopay",
		pay_method: "card",
		merchant_uid: "ORD"+new Date().getTime(),
		name: $(".name").text(),
		amount: $(".totlaPrice").text(),
		buyer_email: "vveny113@gmail.com",
		buyer_name: $("#user").val(),
		buyer_tel: "010-1997-0123",
		buyer_addr: "서울특별시 중랑구 묵동",
		buyer_postcode: "01181"
	}, function (rsp) { //callback
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			beforeSend: function(xhr){xhr.setRequestHeader(header, token);},
			url: "/common/payment/",
			type: "POST",
			data: {
				"uid": rsp.imp_uid,
				"merchant":rsp.merchant_uid,
				"amount": rsp.paid_amount,
				"applyNum": rsp.apply_num,
				"itemCount": $(".count").text(),
				"ino": $(".itemNo").val(),
				"email": $("#user").val(),
				"zipcode": $("#sample4_postcode").val(),
				"address": $("#sample4_roadAddress").val(),
				"detail": $("#sample4_detailAddress").val()
			},
			success: function(){
				console.log("data jandal success!!");
				alert("결제가 완료됐습니다 :}")
				location.replace("/my/order/list");
			}
		})
	});
}