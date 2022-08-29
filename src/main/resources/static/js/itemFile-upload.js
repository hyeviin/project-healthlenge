/**
 * 상품 등록 페이지의 등록될 이미지 파일 보여주는 event
 */
 
 $(function(){
	//파일업로드처리하기 위한 이벤트
	$(".item-fileInput").change(fileUpload);
});

function fileUpload(){
	//파일 데이터 전송
	target= $(this);
	file= $(this)[0].files[0];
	//파일이 아닐경우도 체크해야합니다.
	if(!file.type.includes("image")){
		alert("이미지가 아닙니다🦾");
		return;
	}
	if(file.size > (1024*1024*5)){
		alert("이미지 용량은 5MB 까지만 적용하세요.");
		return;
	}
	//console.log(file.name);
	fileName= file.name;
	data= new FormData();
	data.append("file", file);
	//토큰
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	
	$.ajax({
		beforeSend: function(xhr){xhr.setRequestHeader(header, token);},
		url:"/admin/tempFile",
		type:"post",
		data: data,
		contentType: false,
		processData: false,
		//enctype: 'multipart/form-data',
		success:function(fileUrl){
			target.next().css("background-image", "url("+fileUrl+")");
			target.siblings("[type=hidden]").val(fileName);
		},
		error: function(err){
			alert("파일업롣 오류 서버의 용량이나 경로를 확인하세요.")
		}
	});
}