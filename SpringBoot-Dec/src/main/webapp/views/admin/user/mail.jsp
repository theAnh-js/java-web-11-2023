<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<form action="/mailer" method="post">
		<h2>Send mail</h2>
		
		<div>
			<label>Gửi đến</label>
			<input type="email" name="txtTo" placeholder="Nhập tên người nhận">
		</div>
		<br>
		<div>
			<label>Tiêu đề</label>
			<input type="text" name="txtSubject" placeholder="Nhập tiêu đề">
		</div>
		<br>
		<div>
			<label>Nội dung</label>
			<textarea rows="4" cols="10" name="txtContent" placeholder="Nhập nội dung thư"></textarea>
		</div>
		<br>
		<div>
			<button type="submit">Gửi</button>
		</div>
	
	</form>

</div>

</body>
</html>