<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/" var="root"></c:url> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${root }css/bootstrap/bootstrap.css">
    <!-- Our Custom CSS -->
    <link rel="stylesheet" href="${root }css/admin/main.css">
    <link rel="stylesheet" href="${root }css/admin/order.css">
    <!-- Font Awesome JS -->
	<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
    <title>Companion::관리자::주문내역상세보기</title>
</head>
<body>
<!-- .wrapper [start] -->
<div class="wrapper">
    <!-- Sidebar -->
   	<jsp:include page="../common/admin_sidebar.jsp"/>
	<!-- #content [start] -->
	<div id="content">
		<!-- nav [start] -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<!-- .container-fluid [start] -->
			<div class="container-fluid">
				<!-- menu toggle button [start] -->
			    <button type="button" id="sidebarCollapse" class="btn btn-jacaranda">
			        <i class="fas fa-align-left"></i>
			        <span>메뉴</span>
			    </button>
			    <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
			        data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
			        aria-expanded="false" aria-label="Toggle navigation">
			        <i class="fas fa-align-justify"></i>
			    </button>
			    <!-- menu toggle button [end] -->
				<!-- top menu bar [start] -->
			    <div class="collapse navbar-collapse" id="navbarSupportedContent">
			        <ul class="nav navbar-nav ml-auto">
			            <li class="nav-item active">
			                <a class="nav-link" href="#">주문관리</a>
			            </li>
			        </ul>
			    </div>
			    <!-- top menu bar [end] -->
			</div>
			<!-- .container-fluid [end] -->
		</nav>
		<!-- nav [end] -->
		<!-- .section [start] -->
			<section class="section">
			<div class="clearfix">
				<div class="main--title float--left">
				
					<h1>[Admin] 주문 내역 상세보기</h1>
				</div>
				<div class="float--right">
					<a href="${root }admin/order_list" class="mainBtn">목록으로</a>
				</div>
			</div>
			<form method="POST" role="form">
				<input type = "hidden" name="order_id" value="${bean.order_id }"/>
				<c:choose>
					<c:when test="${bean.order_state_admin eq '주문취소' }">
						<div class="card">
							<img alt="주문취소" src="${root }imgs/admin/cancel.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/bqlqn" title="bqlqn">bqlqn</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>취소된 주문입니다.</strong>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${bean.order_state_admin eq '결제완료' }">
						<div class="card">
							<img alt="결제완료" src="${root }imgs/admin/payment.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>결제가 완료된 주문입니다.</strong><br/>
									<div class="btn-group">
										<button class="Cbtn" type="button" id="btn-cancel">주문취소</button>
										<button class="Cbtn" type="button" id="btn-release">출고요청</button>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${bean.order_state_admin eq '출고처리중' }">
						<div class="card">
							<img alt="출고처리중" src="${root }imgs/admin/releasing.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/eucalyp" title="Eucalyp">Eucalyp</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>출고 처리 중인 주문입니다.</strong><br/>
									<div class="btn-group">
										<button class="Cbtn" type="button" id="btn-releaseDone">출고완료</button>
									</div>
								</div>
							</div>
						</div>					
					</c:when>
					<c:when test="${bean.order_state_admin eq '출고완료' }">
						<div class="card">
							<img alt="출고완료" src="${root }imgs/admin/release.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/monkik" title="monkik">monkik</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>출고가 완료된 주문입니다.</strong><br/>
									<div class="btn-group">
										<button class="Cbtn" type="button" id="btn-deliveryDone">배송완료</button>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${bean.order_state_admin eq '배송완료' }">
						<div class="card">
							<img alt="배송완료" src="${root }imgs/admin/delivery.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>배송 완료된 주문입니다.</strong><br/>
									<div class="btn-group">
										<button class="Cbtn" type="button" id="btn-purchaseConfirm">구매확정</button>
										<button class="Cbtn" type="button" id="btn-exchange">교환</button>
										<button class="Cbtn" type="button" id="btn-return">반품</button>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${bean.order_state_admin eq '구매확정' }">
						<div class="card">
							<img alt="구매확정" src="${root }imgs/admin/soldout.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/flat-icons" title="Flat Icons">Flat Icons</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>구매 확정된 주문입니다.</strong><br/>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${bean.order_state_admin eq '교환' }">
						<div class="card">
							<img alt="교환" src="${root }imgs/admin/exchange.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/flat-icons" title="Flat Icons">Flat Icons</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>교환된 주문입니다.</strong><br/>
									<div class="btn-group">
										<button type="button" id="btn-purchaseConfirm" class="Cbtn">구매확정</button>
									</div>
								</div>
							</div>
						</div>
					</c:when>
					<c:when test="${bean.order_state_admin eq '반품' }">
						<div class="card">
							<img alt="반품" src="${root }imgs/admin/return.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>반품된 주문입니다.</strong><br/>
								</div>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="card">
							<img alt="물음표" src="${root }imgs/admin/mark.png">
							<!-- 아이콘 제작자 <a href="https://www.flaticon.com/kr/authors/freepik" title="Freepik">Freepik</a> from <a href="https://www.flaticon.com/kr/" title="Flaticon"> www.flaticon.com</a> -->
							<div class="container">
								<div class="card-content">
									<strong>현재 주문 상태 : ${bean.order_state_admin }</strong>
									<div class="btn-group">
										<button class="Cbtn" type="button" id="btn-cancel">주문취소</button>
										<button class="Cbtn" type="button" id="btn-purchaseConfirm">구매확정</button>
										<button class="Cbtn" type="button" id="btn-exchange">교환</button>
										<button class="Cbtn" type="button" id="btn-return">반품</button>
									</div>
								</div>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</form>
			<div class="layB clearfix">
			<div class="halfBox float--left">
			<h2>주문자 정보</h2>
			<table class="halfTable">
				<tbody>
					<tr>
						<th>주문번호</th>
						<td>${bean.order_id }</td>
					</tr>
					<tr>
						<th>아이디</th>
						<td>${bean.member_id }</td>
						<th>주문자</th>
						<td>${bean.member_name }</td>
					</tr>
					<tr>
						<th>주문상태</th>
						<td>${bean.order_state_admin }</td>
					</tr>
					<tr>
						<th>주문일자</th>
						<td>${bean.order_date }</td>
					</tr>
					<tr>
						<th>연락처</th>
						<td colspan="3">${bean.member_phone }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td colspan="3">${bean.member_email }</td>
					</tr>
				</tbody>
			</table>
			</div>
			
			<div class="halfLBox float--right">
			<h2>수령인 정보</h2>
			<table class="halfLtable">
				<tbody>
					<tr>
						<th>수령인</th>
						<td>${bean.order_name }</td>
						<th>배송상태</th>
						<td>
						<c:choose>
							<c:when test="${bean.delivery_state_name eq null }">
								출고요청 전
							</c:when>
							<c:otherwise>
								${bean.delivery_state_name }
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<th>연락처1</th>
						<td>${bean.order_phone }</td>
						<th>연락처2</th>
						<td>${bean.order_tel }</td>
					</tr>
					<tr>
						<th>우편번호</th>
						<td>${bean.order_addr1 }</td>
					</tr>
					<tr>
						<th>주소</th>
						<td colspan="3">${bean.order_addr2 } ${bean.order_addr3 }</td>
					</tr>
					<tr>
						<th>택배사</th>
						<td>
						<c:choose>
							<c:when test="${bean.delivery_company eq null }">
								출고요청 전
							</c:when>
							<c:otherwise>
								${bean.delivery_company }
							</c:otherwise>
						</c:choose>
						</td>
						<th>송장번호</th>
						<td>
						<c:choose>
							<c:when test="${bean.delivery_number eq null }">
								출고요청 전
							</c:when>
							<c:otherwise>
								${bean.delivery_number }
							</c:otherwise>
						</c:choose>
						</td>
						
					</tr>
					<tr>
						<th style="letter-spacing:0; font-size:15px;">배송 요청사항</th>
						<td colspan="3">${bean.order_msg }</td>
					</tr>
				</tbody>
			</table>
			</div>
			<!-- .halfBox [end] -->
			
			</div>
			<!-- .layB [end] -->
			
			<div class="layB">
				<h2>주문 상품 정보</h2>
				<table class="table table_layout1">
				<colgroup>
					<col class="col1">
					<col class="col2">
					<col class="col3">
					<col class="col4">
					<col class="col5">
				</colgroup>
					<thead>
						<tr>
							<th scope="row">상품 이름</th>
							<th scope="row">상품 옵션</th>
							<th scope="row">상품 단가</th>
							<th scope="row">수량</th>
							<th scope="row">소 계</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list }" var="product">
						<tr>
							<td>${product.product_name }</td>
							<td>${product.order_detail_option }</td>
							<td>${product.product_price }</td>
							<td>${product.order_detail_quantity }</td>
							<td style="text-align:right; padding-right:40px;">${product.order_detail_price }</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="3"></td>
						<td>택배비</td>
						<td style="text-align:right; padding-right:40px;">2,500</td>
					</tr>
					<tr>
						<td colspan="3"></td>
						<td class="amount">총 계</td>
						<td class="amount" style="text-align:right; padding-right:40px;">${bean.order_amount }</td>
					</tr>
					</tbody>
				</table>
			
			<h2>결제 정보</h2>
			<table class="table">
				<thead>
					<tr>
						<th>결제상태</th>
						<th>결제일</th>
						<th>결제금액</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${bean.payment_state_name }</td>
						<td>${bean.payment_date }</td>
						<td>${bean.payment_amount }</td>
					</tr>
				</tbody>
			</table>
			</div>
			<!-- .layB [end] -->
			</section>
			<!-- .section [end] -->
		<!-- Footer  -->
		<jsp:include page="../common/footer.jsp"/>
	</div>
	<!-- #content [end] -->
</div>
<!-- .wrapper [end] -->



<!-- jQuery -->
<script src="${root }js/jquery-1.12.4.js"></script>
<!-- Popper.JS -->
<script src="${root }js/bootstrap/popper.js"></script>
<!-- Bootstrap JS -->
<script src="${root }js/bootstrap/bootstrap.js"></script>
<!-- MAIN JS -->
<script src="${root }js/main.js"></script>

<script type="text/javascript">
var formObj = $("form[role='form']");
$('#btn-release').click(function(){
	var con = confirm('출고요청을 하시겠습니까?');
	if(con){
		formObj.attr('action','${root}admin/order_release');
		formObj.submit();
	} else {
		return false;
	}
});
$('#btn-releaseDone').click(function(){
	var con = confirm('출고완료를 하시겠습니까?');
	if(con){
		formObj.attr('action','${root}admin/release_done');
		formObj.submit();
	} else {
		return false;
	}
});
$('#btn-deliveryDone').click(function(){
	var con = confirm('배달완료를 하시겠습니까?');
	if(con){
		formObj.attr('action','${root}admin/delivery_done');
		formObj.submit();
	} else {
		return false;
	}
});
$('#btn-purchaseConfirm').click(function(){
	var con = confirm('구매확정을 하시겠습니까?');
	if(con){
		formObj.attr('action','${root}admin/purchase_confirm');
		formObj.submit();
	} else {
		return false;
	}
});
$('#btn-exchange').click(function(){
	var con = confirm('교환하시겠습니까?');
	if(con){
		formObj.attr('action','${root}admin/order_exchange');
		formObj.submit();
	} else {
		return false;
	}
});
$('#btn-return').click(function(){
	var con = confirm('반품하시겠습니까?');
	if(con){
		formObj.attr('action','${root}admin/order_return');
		formObj.submit();
	} else {
		return false;
	}
});
$('#btn-cancel').click(function(){
	var con = confirm('주문취소를 하시겠습니까?');
	if(con){
		formObj.attr('action','${root}admin/order_cancel');
		formObj.submit();
	} else {
		return false;
	}
});
</script>

</body>
</html>