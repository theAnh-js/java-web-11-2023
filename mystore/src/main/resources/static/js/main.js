$(document).ready(function(){
	
	$(".btn-heart").click(function(){
		var id = $(this).closest("div").attr("data-id");
		$.ajax({
			url: "/product/add-to-favorite/" + id,
			success: function(response){
				if(response){
					alert("Đã thêm vào mục yêu thích thành công.");
				}else{
					alert("Đã có sẵn.")
				}
				
			}
		})
	})
})