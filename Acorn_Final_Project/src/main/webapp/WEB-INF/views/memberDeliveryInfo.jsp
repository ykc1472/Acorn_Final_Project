<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/Final_Project/css/menu.css">
	<!--  -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<div align="center">
	<br>
	<br>
	<br>
	<table border="1">
		<tr>
			<td colspan="3" height="30px"><strong>[ ${orderInfo.categoryname}  ]</strong> ${orderInfo.ftitle} </td>
		</tr>
		<tr>
			<td rowspan="6"><img src="/Final_Project/image/food/${orderInfo.fmainimage}.jpg" width="400px"></td>
			<th>주문번호</th>
            <td>${orderInfo.ordernum}</td>
		</tr>
		<tr>
			<th>주문일</th>
            <td>${orderInfo.orderdate}</td>
		</tr>
		<tr>
			<th>옵션명</th>
            <td>${orderInfo.optionname}</td>
		</tr>
        <tr>
        	<th>수량</th>
        	<td>${orderInfo.amount} 개</td>
        </tr>
        <tr>
        	<th>결제정보</th>
        	<td>
       			<c:choose>
					<c:when test="${orderInfo.payMethod eq 1}">
						카드 결제
					</c:when>
					<c:when test="${orderInfo.payMethod eq 2}">
						계좌 이체
					</c:when>
					<c:when test="${orderInfo.payMethod eq 3}">
						무통장 입금
					</c:when>
				</c:choose>
        	</td>
        </tr>
        <tr>
        	<th>상태</th>
        	<td>
				<c:if test="${orderInfo.state eq 10}">주문 취소</c:if>
				<c:if test="${orderInfo.state eq 11}">주문 신청</c:if>
				<c:if test="${orderInfo.state eq 12}">주문 확인</c:if>
				<c:if test="${orderInfo.state eq 13}">포장 / 발송대기</c:if>
				<c:if test="${orderInfo.state eq 14}">배송중</c:if>
				<c:if test="${orderInfo.state eq 15}">배송 완료</c:if>
				<c:if test="${orderInfo.state eq 16}">구매 확정</c:if>
				<c:if test="${orderInfo.state eq 17}">환불 신청</c:if>
				<c:if test="${orderInfo.state eq 18}">환불 완료</c:if>
        	</td>
        </tr>
        <tr>
        	<td colspan="3"><strong>배송지 정보</strong></td>
        </tr>
        <tr>
        	<td colspan="3">
	        	<table>
	        		<tr>
						<td width="200px">수령자</td>
						<td>
							<c:choose>
								<c:when test="${orderInfo.state eq 11}">
									<input type="text" value="${orderInfo.orderName}" name="ordername">
								</c:when>
								<c:otherwise>
									${orderInfo.orderName}
								</c:otherwise>
							</c:choose>
						</td>
	        		</tr>
	        	</table>
        	</td>
        </tr>
        <tr>
        	<td colspan="3">
        		<table>
	        		<tr>
						<td width="200px" >우편번호</td>
						<td>
							<c:choose>
								<c:when test="${orderInfo.state eq 11}">
									<input type="text" value="${orderInfo.addr_post}" name="addr_post">
								</c:when>
								<c:otherwise>
									${orderInfo.addr_post}
								</c:otherwise>
							</c:choose>
						</td>
	        		</tr>
	        	</table>
	        </td>
        </tr>
        <tr>
        	<td colspan="3">
        		<table>
	        		<tr>
						<td width="200px">주소 상세정보</td>
						<td>
							<c:choose>
								<c:when test="${orderInfo.state eq 11}">
									<input type="text" value="${orderInfo.addr_f}" name="addr_f"> / <input type="text" value="${orderInfo.addr_l}" name="addr_l">
								</c:when>
								<c:otherwise>
									 ${orderInfo.addr_f} / ${orderInfo.addr_l}
								</c:otherwise>
							</c:choose>
						</td>
	        		</tr>
	        	</table>
	        </td>
        </tr>
	</table>
	</div>
</body>
</html>