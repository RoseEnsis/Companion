<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<c:url value="/" var="root"></c:url>
<head>

</head>
<body>
<!-- Right-Sidebar Start -->




<nav id="scroll-right">
	<div class="scroll-right-header">
		<p>TODAY VIEW</p>
	</div>
	
		<div class="scroll-right-content">
		 	<c:forEach items="${sessionList }" var="bean"  begin="0" end="2" step="1">
					 <div class="goods-content">
		 				<a href="${root }order/productDetail?idx=${bean.product_id}"><img src="../imgs${bean.product_image }" alt="70x70"></a> 
					</div> 
		 	</c:forEach> 
			<div class="scroll-up" id="topBtn">
				<a href="#" role="button"><h3 class="up">↑</h3></a>
			</div>
		</div>
</nav>
<!-- Right-Sidebar End -->
</body>
