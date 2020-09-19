<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/" var ="root"></c:url>
<c:url value="/" var ="root"></c:url>
	<!DOCTYPE html>
	<html>
	
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
		<title>주문 정보를 입력해주세요.</title>
		
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="../css/bootstrap/bootstrap.css">
	    <!-- Our Custom CSS -->
	    <link rel="stylesheet" href="../css/main.css">
	    <link rel="stylesheet" href="../css/mypage/orderPurchase.css">
	
		<!-- Font Awesome JS -->
		<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
		<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
	
		<style type="text/css">
			.purchase{
			}
			.btn-order{
			float:right;
			display:block;
			}
		</style>
	</head>
	<body>
	<div class="wrapper">
	
			 <!-- Sidebar  -->
	 	<jsp:include page="../common/sidebar.jsp"/> 
        <!-- sideBar  -->
			<!-- menu  -->
			
<div id="content">			
				<nav class="navbar navbar-expand-lg navbar-light bg-light">
                   <div class="container-fluid">
   
                       <button type="button" id="sidebarCollapse" class="btn btn-info">
                           <i class="fas fa-align-left"></i>
                           <span>메뉴</span>
                       </button>
                       <button class="btn btn-dark d-inline-block d-lg-none ml-auto" type="button" data-toggle="collapse"
                           data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                           aria-expanded="false" aria-label="Toggle navigation">
                           <i class="fas fa-align-justify"></i>
                       </button>
                       
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                           <c:choose>
                               <c:when test="${sessionScope.memberVo.member_id==null }">
                                   <script>
                                      window.location.href="login";
                                   </script>
                               </c:when>
                               <c:otherwise>
                                   <ul class="nav navbar-nav ml-auto">
                                       <li class="nav-item">
                                           <a class="nav-link" href="${root }mypurchaselist">주문내역</a>
                                       </li>
                                       <li class="nav-item">
                                           <a class="nav-link" href="${root }mypagereserve">예약조회</a>
                                       </li>
                                       <li class="nav-item">
                                           <a class="nav-link" href="${root }mypage/mycart">장바구니</a>
                                       </li>
                                       <li class="nav-item">
                                           <a class="nav-link" href="${root }mypagequestion">문의조회</a>
                                       </li>
                                       <li class="nav-item">
                                           <a class="nav-link" href="${root }mypagechk">나의정보</a>
                                       </li>
                                       <li class="nav-item">
                                           <a class="nav-link" href="${root }logout">로그아웃</a>
                                       </li>
                                   </ul>
                               </c:otherwise>
                               
                           </c:choose>
                       </div>
                   </div>
               </nav>
			<!-- menu -->
				<!--content  -->
					<div class="categories">
					<a href="#homeSubmenu"> <i class="fas fa-shopping-cart"></i> 쇼핑하기</a> <span></span> 
					<a href="#"> <i class="fas fa-clipboard-list"></i> 주문서작성</a>
					<hr class="mb-4">
					</div>
		<!-- .title-group start-->
   <section class="section">
  	 <form action="${root }order/successOrder" role="form" method="post" autocomplete="off" id="payForm">  
  
                <div class="title-group clearfix">
                    <h1 class="main--title float--left">주문서작성/결제</h1>
                    <ul class="route float--right">
                        <li>
                            01 장바구니
                            <span>></span>
                        </li> 
                        <li>
                            02 주문서작성/결제
                            <span>></span>
                        </li> 
                        <li>03 주문완료</li> 
                    </ul>
                </div><!-- .title-group end-->
                
                 
                       <h4>주문상세내역</h4>
                <table class="table table_layout">
	               <colgroup>
	               <col class="col1">
	               <col class="col2">
	               <col class="col3">
	               <col class="col4">
	               <col class="col5">
	               </colgroup>
                    <thead>
                        <tr>
                            <th>상품 이름</th>
                            <th>옵션</th>
                            <th>수량</th>
                            <th>상품금액</th>
                            <th>합계금액</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="imgTbox">
                            	<!-- 이미지 -->
                            	<div class="imgBox">
	                            	<img id="proImg" src="../imgs${orderProductPurchaseOne.product_image }" class="img-fluid" alt="">
                            	</div>
                            	<!-- 상품이름 -->
                            	<input type="hidden" class="form-control" name="product_id" value="${orderProductPurchaseOne.product_id }">
                            	${orderProductPurchaseOne.product_name }
                            <td>
                            	<!-- 옵션 값  -->
                            	<%
                            	String select1= request.getParameter("selectBox");
                            	out.println(select1);
                           		request.setAttribute("cart_option",request.getParameter("selectBox"));
                            	%>
                          		<input type="hidden" class="form-control" name="order_detail_option" id="order_detail_option" value="<%=request.getParameter("selectBox") %>"/> 
	                        </td>
                            <td>
                            	<%int order_detail_quantity= Integer.parseInt(request.getParameter("order_detail_quantity")); 
                            	out.println(order_detail_quantity);%>
                           		<%
                             	request.setAttribute("order_detail_quantity",request.getParameter("order_detail_quantity"));
                            	%>   
                              <input type="hidden" class="form-control" name="order_detail_quantity" id="order_detail_quantity" value="<%=request.getParameter("order_detail_quantity") %>"/>  
                         	  <input type="hidden" class="form-control" name="product_stock" id="product_stock" value="${orderProductPurchaseOne.product_stock }"/>
                            <td><fmt:formatNumber value="${orderProductPurchaseOne.product_price * order_detail_quantity}" pattern="###,###,###"/>원
                            </td>
                            <td><fmt:formatNumber value="${orderProductPurchaseOne.product_price * order_detail_quantity +2500}" pattern="###,###,###"/>원</td>
                            
                        </tr>
                    </tbody>
                </table>
                  <a href="#"><span></span></a>
                  <div class="coast clearfix">
                    <ul class="coast-group float--right">
                        <li>총 <%=order_detail_quantity %>  개의 상품금액<br/><fmt:formatNumber value="${orderProductPurchaseOne.product_price * order_detail_quantity}" pattern="###,###,###"/>원
                        <li><i class="fas fa-plus"></i></li>
                        <li>배송비<br/>2,500원</li>
                        <li><i class="fas fa-equals"></i></li>
                        <li>합계<br/><fmt:formatNumber value="${orderProductPurchaseOne.product_price * order_detail_quantity +2500}" pattern="###,###,###"/>원
                        <input type="hidden" class="form-control" name="order_detail_price" value="${orderProductPurchaseOne.product_price * order_detail_quantity}">
                        <input type="hidden" class="form-control" name="order_amount" value="${orderProductPurchaseOne.product_price * order_detail_quantity +2500}"/>
                        </li> 
                    </ul>
                </div>
				<div class="order_info"><h2>주문자 정보</h2>
					<table class="table-left">
	                    <tbody>
	                        <tr>
	                            <th class="label"><span>주문하시는 분</span></th>
	                            <td><input type="text" class="form-control" name="member_id" id="member_id" value="${orderVo.member_name}" aria-label="Username" aria-describedby="basic-addon1" readonly></td>
	                        </tr>
	                        <tr>
	                            <th>휴대폰 번호</th>
	                            <td><input type="text" class="form-control" name="member_phone" id="member_phone" placeholder="${orderVo.member_phone }" aria-label="phone" aria-describedby="basic-addon1" readonly>
	                           		  <input type="hidden" class="form-control" name="order_tel" id="order_tel" placeholder="${orderVo.member_phone }" />
	                            </td>
	                           
	                        </tr>
	                        <tr>
	                            <th>이메일</th>
	                            <td><input type="text" class="form-control" placeholder="${orderVo.member_email}" aria-label="email" aria-describedby="basic-addon1" readonly></td>
	                        </tr>
	                    </tbody>
          			     </table>
				</div>
				<div class="delivery_info">
				   		 <h4>배송지 정보</h4>
					<table class="table-left">
						<tbody>
							<tr>
								<th class="label"><span>배송지 확인</span></th>
								<td><label class="form-check-label" for="defaultCheck1">
										주문자 정보와 동일 </label> <input class="form-check-input" type="checkbox"
									value="" id="defaultCheck1" name="defaultCheck1"></td>
							</tr>
							<tr>
								<th class="label"><span>받으실 분</span></th>
								<td><input type="text" class="form-control"
									name="order_name" id="order_name" placeholder="이름을 입력해주세요."
									aria-label="Username" aria-describedby="basic-addon1"></td>
							</tr>
							<tr>
								<th>받으실 곳</th>
								<td><input type="text" class="input--text" name="order_addr1" id="sample6_postcode" placeholder="우편번호">
									<button type="button" id="postSearch" class="cBtn">우편 검색</button> <br /> 
									<input type="text" class="input--text" name="order_addr2" id="sample6_address" placeholder="주소"> 
									<input type="text" class="input--text" name="order_addr3" id="sample6_detailAddress" placeholder="상세주소"></td>
							</tr>
							<tr>
								<th>휴대폰 번호</th>
									<td>
									<input type="text" class="form-control"
										name="order_phone" id="order_phone" placeholder="번호만 입력해주세요."
										aria-label="Username" aria-describedby="basic-addon1">
									</td>
							</tr>
							<tr>
								<th>요청사항</th>
								<td>
								<textarea cols="65" rows="4" maxlength="40" form="payForm" 
								name="order_msg" id="order_msg" placeholder="40자 내로 써주세요."
								aria-label="Username" aria-describedby="basic-addon1"></textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
                <div class="coast clearfix">
                    <ul class="coast-group float--right">
                        <li class="total__title">최종 결제 금액</li>
                        <li class="total"><fmt:formatNumber value="${orderProductPurchaseOne.product_price * order_detail_quantity +2500}" pattern="###,###,###"/>원</li>
                    </ul>
                </div>
                <div class="purchase">
                        <button type="button" class="btn-order" id="payApi" role="button"> 결제하기 </button> 
                </div>
       		</form>
       	</section>  
       
        <!-- Footer  -->
			<jsp:include page="../common/footer.jsp"/>
		<!-- Footer end -->
</div>        
<!-- content end  -->
     </div>     
     <!-- wrapper end  -->   
		<!-- jQuery CDN - Slim version (=without AJAX) -->
	 	<script src="${root}js/jquery-1.12.4.js"></script> 
		<!-- Popper.JS -->
		<script src="${root}js/bootstrap/popper.js"></script>
		<!-- Bootstrap JS -->
		<script src="${root}js/bootstrap/bootstrap.js"></script>
		<!-- MAIN JS -->
   		<script src="${root }js/main.js"></script>			
			<!-- PAY API (i'mport) -->
			<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
			<script>

				$(document).on('click','#payApi',function(){
		   			var order_detail_option = $('#order_detail_option').val();
		   			var order_detail_quantity = $('#order_detail_quantity').val();
		   			$("#order_detail_option").val(order_detail_option);
		   			$("#order_detail_quantity").val(order_detail_quantity);
		   			console.log(order_detail_option);
		   			console.log(order_detail_quantity);
		   			
			   		var product_stock = $('#product_stock').val();
					var order_detail_quantity = $('#order_detail_quantity').val();
					if(product_stock - order_detail_quantity<0){
					 alert("상품 재고보다 구매 수량이 많습니다. 다시 확인 해 주세요.");
					 return;
					}
					var payButton = $("#payApi");
		   			var order_name = $("#order_name").val();
		   			var sample6_postcode = $("#sample6_postcode").val();
		   			var sample6_address = $("#sample6_address").val();
		   			var sample6_detailAddress = $("#sample6_detailAddress").val();
		   			var order_phone = $("#order_phone").val(); 
		   			var order_msg = $("#order_msg").val(); 
		   			var phonenum = $('#order_phone').val();
 			   		var regPhone = /(01[0|1|6|9|7])(\d{3}|\d{4})(\d{4}$)/g; 
	
			   		 if(!regPhone.test(phonenum)){
			   		  alert('잘못된 휴대폰 번호입니다.');
			   		  $('#phone').focus();
			   		  return false;    
			   		 }
		   			
		   			if(order_name==null||order_name==""){
		   				alert("받으실 분 이름을 입력해주세요.");
		   				return;
		   			}
		   			if(order_phone==null||order_phone==""){
		   				alert("휴대폰 번호를 입력해주세요.");
		   				return;
		   			}
		   			if(sample6_postcode==null||sample6_postcode==""||sample6_address==null||sample6_address==""||sample6_detailAddress==""||sample6_detailAddress==null){
		   				alert("우편 번호를 입력해주세요.");
		   				return;
		   			}
		   			if(order_msg==null||order_msg==""){
		   				alert("요청사항을 입력해주세요.");
		   				return;
		   			}
					 $(function(){
					        var IMP = window.IMP; // 생략가능
					        IMP.init('iamport'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
					        var msg;
					        IMP.request_pay({
					            pg : 'kakaopay',
					            pay_method : 'card',
					            merchant_uid : 'merchant_' + new Date().getTime(),
					            name : '${orderProductPurchaseOne.product_name }',
								/* 원활한 결제 테스트를 위해 백원으로 입력했습니다. 
								상품의 정가로 결제를 진행하고 싶은 경우
								아래에 있는 주석 소스를 활용해주세요.*/
					            amount :100,
								/* 
								amount : '${orderProductPurchaseOne.product_price }',
								*/
					            buyer_email : '${orderVo.member_email}',
					            buyer_name : '${orderVo.member_name}',
					            buyer_tel : 'order_phone',
					            buyer_addr : 'order_addr2'+'order_addr3',
					            buyer_postcode : 'order_addr1',
					        }, function(rsp) {
					            if ( rsp.success ) {
						                jQuery.ajax({
						                    url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
						                    type: 'POST',
						                    dataType: 'json',
						                    data: {
						                        imp_uid : rsp.imp_uid
					                    }
					                }).done(function(data) {
					                    if ( everythings_fine ) {
					                        msg = '결제가 완료되었습니다.';
					                        msg += '\n고유ID : ' + rsp.imp_uid;
					                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
					                        msg += '\결제 금액 : ' + rsp.paid_amount;
					                        msg += '카드 승인번호 : ' + rsp.apply_num;
					                        alert(msg);
					                    } else {
					                    }
					                });
					                $('#payForm').submit();
					                
					            } else {
					                msg = '결제에 실패하였습니다.';
					                msg += ' 에러내용 : ' + rsp.error_msg;
					                location.href="<%=request.getContextPath()%>/order/productDetail?idx="+'${orderProductPurchaseOne.product_id }';
					                alert(msg);
					            }
					        });
				    });
				})
			</script>

		<!-- 배송지 정보 주소 검색 API -->
		<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<script>
				$(document).on('click','#postSearch',function(){
					 new daum.Postcode({
				            oncomplete: function(data) {
				                var addr = '';
				                var extraAddr = ''; 

				                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				                    addr = data.roadAddress;
				                } else { // 사용자가 지번 주소를 선택했을 경우(J)
				                    addr = data.jibunAddress;
				                }

				                if(data.userSelectedType === 'R'){
				                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
				                        extraAddr += data.bname;
				                    }
				                    if(data.buildingName !== '' && data.apartment === 'Y'){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    if(extraAddr !== ''){
				                        extraAddr = ' (' + extraAddr + ')';
				                    }
				                } else {
				                }
				                document.getElementById('sample6_postcode').value = data.zonecode;
				                document.getElementById("sample6_address").value = addr;
				                document.getElementById("sample6_detailAddress").focus();
				            }
				        }).open();
				}); 
			</script>
				/*  check box buyer == get package */		   		
		   		<script type="text/javascript"> 
		   		$('input[name=defaultCheck1]').on('click',function(){
		   			if($(this).is(":checked")){
		   				same(true);
		   			}else{
		   				same(false);
		   			}
		   		});
		   			function same(checked){
		   				var f= document.form;
		   				if(checked == true){
		   					document.getElementById("order_name").value="${memberVo.member_name}";
		   					document.getElementById("order_phone").value="${orderVo.member_phone }";
		   					document.getElementById("order_tel").value="${orderVo.member_phone }";
		   					document.getElementById("sample6_postcode").value="${memberVo.member_addr1}";
		   					document.getElementById("sample6_address").value="${memberVo.member_addr2}";
		   					document.getElementById("sample6_detailAddress").value="${memberVo.member_addr3}";
		   				}else{
		   					document.getElementById("order_name").value="";
		   					document.getElementById("order_phone").value="";
		   					document.getElementById("sample6_postcode").value="";
		   					document.getElementById("sample6_address").value="";
		   					document.getElementById("sample6_detailAddress").value="";
		   				}
		   			}
		   		</script>
		

	</body>
	
	</html>