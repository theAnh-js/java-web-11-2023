<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa bài viết</title>
</head>
<body>

<div>
<label for="categoryCode">Thể loại</label>
  <select name="categoryCode" id="categoryCode">
  	  <option value="">Chọn loại bài viết</option>
  	  <c:forEach var="item" items="${categories}">
  	  		<option value="${item.code}">${item.name}</option>
  	  </c:forEach>
  </select>
  <br><br>
</div>

<div>
<label>Tiêu đề</label>
<input type="text" id="title" name="title" value="${model.title}">
</div>

<div>
<label>Hình đại diện</label>
<input type="text" id="thumbnail" name="thumbnail" value="${model.thumbnail}">
</div>

<div>
<label>Mô tả ngắn</label>
<input type="text" id="shortDescription" name="shortDescription" value="${model.shortDescription}">
</div>

<div>
<label>Nội dung</label>
<input type="text" id="content" name="content" value="${model.content}">
</div>
<script>
	var editor = '';
	$(document).ready(function(){
		editor = CKEDITOR.replace( 'content');
	});
	
    // khi click vào button có id = btnAddOrUpdateNew
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSubmit').serializeArray();
        $.each(formData, function (i, v) {
            // add các giá trị vào mảng data.
            // giống như khi ta ném 1 cục {...} vào postman.
            data[""+v.name+""] = v.value;
        });
        data["content"] = editor.getData();
        var id = $('#id').val();
        if (id == "") {
            addNew(data);
        } else {
            updateNew(data);
        }
    });
    function addNew(data) {
        $.ajax({
            url: '${APIurl}', //gọi request vào url này, vì bình thường form sẽ có url trên action nhưng khi làm rest api thì ko có nên ta khai báo url đó ở ajax. (url này giống url khi ta làm việc với postman). (url này ta khai báo ở dòng 3, vì nếu truyền trực tiếp thì không chạy, phải thông qua c:url).
            type: 'POST',  // định nghĩa type của request
            contentType: 'application/json', // để server hiểu được kiểu data ta muốn gửi lên từ client là json.

            data: JSON.stringify(data), // tuy nhiên khi ở client truyền sang thì data nó ở dạng javascript object nên ta cần chuyển đổi kiểu  javascript object -> json để server hỉu.
            dataType: 'json', // định nghĩa type của data khi server responce về client là json.

            // khi post data lên thành công
            success: function (result) {
            	window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=insert_success";
            },
            // khi post data lên thất bại
            error: function (error) {
            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            }
        });
    }
    function updateNew(data) {
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            }
        });
    }
</script>
</body>
</html>