<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="/Final_Project/css/menu.css">
<!--  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- 인터넷에 있는 한글 Noto Sans 글씨체를 사용하기 위한 Link-->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:400,500,700,900&amp;subset=korean" rel="stylesheet" />

<script type="text/javascript" src="/Final_Project/js/top.js" ></script>

<script type="text/javascript">
	<c:if test="${!empty mesg}">
		alert("${mesg}");
		<c:remove var="mesg" />
	</c:if>
</script>


<div>
	<div class = "font">
		<c:choose>
			<c:when test="${loginInfo != null }">
				<a href="/Final_Project/loginCheck/logout" class="menuTop">로그아웃</a>&nbsp;&nbsp;
				<c:choose>
					<c:when test="${loginInfo.grade == 99}">
						<a href="/Final_Project/adminCheck/foodInfoManagement" class="menuTop">상품정보 관리</a>&nbsp;&nbsp;
						<a href="/Final_Project/adminCheck/MemberManagement" class="menuTop">사용자 정보 관리</a>&nbsp;&nbsp;
						<a href="/Final_Project/adminCheck/orderList" class="menuTop">전체 주문확인</a>&nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						<a href="/Final_Project/loginCheck/cartList" class="menuTop">장바구니</a>&nbsp;&nbsp;
						<a href="" class="menuTop">나의정보</a>&nbsp;&nbsp;
						<a href="/Final_Project/loginCheck/orderList" class="menuTop">주문확인</a>&nbsp;&nbsp;
					</c:otherwise>
				</c:choose>
			</c:when>
		
			<c:otherwise>
				<a href="/Final_Project/loginForm" class="menuTop">로그인</a>&nbsp;&nbsp;
				<a href="/Final_Project/memberForm" class="menuTop">회원가입</a>&nbsp;
			</c:otherwise>
		</c:choose>
		<div align="center"><a href="main"><img src="/Final_Project/image/item/mainBanner.jpg" width = "200"></a>
		
		</div>
		<div>
			<form action="/Final_Project/searchingFoodList" method="get">
				<input type="text" name="search" id="search"> <input type="submit" value="검색">
			</form>
			 
		</div>
	</div>
	
</div>
