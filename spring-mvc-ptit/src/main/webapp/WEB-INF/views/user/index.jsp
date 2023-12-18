<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglib.jsp"%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
<section>
	<nav>
		<ul>
			<li><a href="#">London</a></li>
			<li><a href="#">Paris</a></li>
			<li><a href="#">Tokyo</a></li>
		</ul>
	</nav>


	<form:form action="confirm.jsp" method="post" modelAttribute="reservation">
		<label for="fname">First name:</label><br> 
		<input type="text" id="fname" name="fname" value="John"><br> 
		<label for="lname">Last name:</label><br> 
		<input type="text" id="lname" name="lname" value="Doe"><br><br>
		<input type="submit" value="Submit">
	</form:form>

	<article>
		<h1>London</h1>
		<p>London is the capital city of England. It is the most populous
			city in the United Kingdom, with a metropolitan area of over 13
			million inhabitants.</p>
		<p>Standing on the River Thames, London has been a major
			settlement for two millennia, its history going back to its founding
			by the Romans, who named it Londinium.</p>
		<p>London is the capital city of England. It is the most populous
			city in the United Kingdom, with a metropolitan area of over 13
			million inhabitants.</p>
		<p>Standing on the River Thames, London has been a major
			settlement for two millennia, its history going back to its founding
			by the Romans, who named it Londinium.</p>
	</article>
</section>