<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>File Upload</title>
</head>
<body>

    <h2>File Upload Form</h2>

    <form action="${pageContext.request.contextPath}/admin/users/upload-file" method="post" enctype="multipart/form-data">
        <label for="file">Choose File:</label>
        <input type="file" name="file" id="file" required>
        <br>
        <button type="submit">Upload</button>
    </form>

    <br>

</body>
</html>
