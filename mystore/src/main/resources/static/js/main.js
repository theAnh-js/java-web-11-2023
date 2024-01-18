$(document).ready(function(){
	// Click event for elements with class "btn-send"
	$(".btn-open-modal").click(function() {
		var id = $(this).closest("div").attr("data-id");

		// Log the id to the console
		console.log("btn-send id:", id);

		// AJAX request to "/product/send-email/" + id
		$.ajax({
			url: "/product/get-id-for-email/" + id,
			success: function(response) {
				if (response) {
					// Handle success
				} else {
					// Handle failure
				}
			}
		});
	});

	// Click send button 
	$(".btn-send").click(function() {
		
		alert("you clicked send bnt");
		
		var data = {
			id: $("#myModal #id").val(),
			email:$("#myModal #email").val(),
			comment: $("#myModal #comment").val(),
		};

		$.ajax({
			url: "/product/send-email/",
			data: data,
			success: function(response) {
				if (response) {
					alert("Gửi mail thành công.");
				} else {
					alert("Lỗi!");
				}
			}
		});
	});


	// Click event for elements with class "btn-heart"
	$(".btn-heart").click(function() {
		
		var id = $(this).closest("div").attr("data-id");

		// Log the id to the console
		console.log("btn-heart id:", id);

		// AJAX request to "/product/add-to-favorite/" + id
		$.ajax({
			url: "/product/add-to-favorite/" + id,
			success: function(response) {
				if (response) {
					alert("Đã thêm vào mục yêu thích thành công.");
				} else {
					alert("Đã có sẵn.");
				}
			}
		});
	});
});


/*$(document).ready(function(){
	
	$(".btn-send").click(function(){
		var id = $(this).closest("div").attr("data-id");
	
		$.ajax({
			url: "/product/send-email/" + id,
			success: function(response){
				if(response){
				}else{
				}
			}
		})
		
	})
	
	
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
})*/