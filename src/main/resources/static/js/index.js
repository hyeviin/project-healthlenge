/**
 * wirter: hyevin
 */
 
 $(function(){
	//이벤트 이미지 불러오깅
	$.ajax({
		url:"/index/evnets",
		success:function(result){
			$("#event").html(result);
		}
	});
	
	//카테고리 종류 부러오깅
	$.ajax({
		url:"/index/categorys",
		success:function(result){
			$("#category-items").html(result);
		}
	});
	
	//챌린지 게시판 불러
	$.ajax({
		url:"/index/boards",
		success:function(result){
			$("#topic").html(result);
		}
	});
});