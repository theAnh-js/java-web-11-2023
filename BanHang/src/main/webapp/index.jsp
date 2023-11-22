<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- Khi web được đọc, nó sẽ gọi file index.jsp trước, do ta đã định
nghĩa trong file pom.xml rồi.
Khi vào đây thì nó sẽ chuyển hướng luôn đến file home.jsp -->

<c:redirect url="/home.jsp"></c:redirect>