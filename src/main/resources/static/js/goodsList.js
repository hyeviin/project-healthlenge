/**
 * 상품을 인덱스페이지에서 보여주겠습니다!
 */
 
 $(function(){
	$.get("/common/item", function(result){
		$("#items-items").html(result);
	});
});