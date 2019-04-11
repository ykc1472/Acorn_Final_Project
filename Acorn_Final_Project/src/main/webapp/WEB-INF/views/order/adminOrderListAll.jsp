<%@page import="com.dto.OrderDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("select").on("change", function(event){
			var beforState = selectState($(this).attr("ordernum-data"), $(this).val());
		})
	})
	function selectState(ordernum, afterState){
		$.ajax({
			type : "GET",
			url : "/Final_Project/adminCheck/selectState",
			dataType : "text",
			data : {
				ordernum : ordernum
			},
			success : function(Data, status, xhr) {
			   changeState(ordernum, Data, afterState);
			},
			error : function(xhr, status, error) {
				console.log("error");
			}
		})
	}
	function changeState(ordernum, beforState, afterState){
		$.ajax({
			type : "PUT",
			url : "/Final_Project/adminCheck/changeState",
			dataType : "text",
			headers: {
				"Content-Type":"application/json"
			},
			data : JSON.stringify({ordernum : ordernum, state :afterState}),
			success : function(Data, status, xhr) {
				if(Data == 1){
				  	var mesg = "주문번호 < " + ordernum +" >의 상태를\n"+stateMesg(beforState)+"에서 "+ stateMesg(afterState)+"로 변경하셨습니다.";
			   		alert(mesg);
				} else {
					alert("상태 변경에 실패했습니다.");
				}
			},
			error : function(xhr, status, error) {
				console.log("error");
			}
		})
	}
	
	function stateMesg(state){
		var mesg = "";
		if(state == 10){
			mesg = "주문 취소";
		} else if (state == 11){
			mesg = "주문 신청";
		}else if (state == 12){
			mesg = "주문 확인";
		}else if (state == 13){
			mesg = "포장 / 발송대기";
		}else if (state == 14){
			mesg = "배송중";
		}else if (state == 15){
			mesg = "배송 완료";
		}else if (state == 16){
			mesg = "구매 확정";
		}else if (state == 17){
			mesg = "환불 신청";
		}else if (state == 18){
			mesg = "환불 완료";
		}
		return mesg;
	}

</script>
<div align="center">
<table width="70%" cellspacing="0" cellpadding="0">
	<tr>
		<td class="td_default"><b>상품 및 배송정보</b>
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

	<tr>
		<td>
			<table width="100%" border="1" style="border-collapse: collapse" bordercolor="#CCCCCC">
				<tr>
					<td class="td_default" width="100" height="35" align="center"><strong>주문번호</strong></td>
					<td width="200" class="td_default" height="35" align="center" colspan="2"><strong>상품명</strong></td>
					<td width="100" class="td_default" height="35" align="center"><strong>판매가</strong></td>
					<td class="td_default" width="50" height="35" align="center"><strong>수량</strong></td>
					<td class="td_default" width="100" height="35" align="center"><strong>합계</strong></td>
					<td class="td_default" width="100" height="35" align="center"><strong>배송상태</strong></td>
					<td class="td_default" width="100" height="35" align="center"><strong>수령인</strong></td>
				</tr>

				<c:forEach items="${pagingDTO.orderlist}" var="order" varStatus="i">
				<tr>
					<td height="35" class="td_default" align="center">
						<span class="a_default">${order.ordernum}</span>
					</td>
					<td height="35" width="50" class="td_default" align="center">
						<img src="/Final_Project/image/food/${order.fmainimage}.jpg" border="0" height="60">
					</td>
					<td height="35" class="td_default">
						<span class="a_default">${order.ftitle}</span><br>
						${order.content}<br>
						<font size="2" color="#665b5f">[옵션  ${order.foption}: ${order.optionname}]</font>
					</td>
					<td height="35" class="td_default" align="center">
						<span  id = "price1"><fmt:formatNumber value='${order.fprice + order.optionprice}' pattern='###,###,###' /></span>원
					</td>
					<td height="35" class="td_default" align="center">
						<span id = "num1">${order.amount}</span>개
					</td>
					<td height="35" class="td_default" align="center">
						<span><fmt:formatNumber value='${(order.fprice + order.optionprice) * order.amount}' pattern='###,###,###' /> 원</span>
					</td>
					<td height="35" class="td_default" align="center">
						<select ordernum-data = '${order.ordernum}'>
								<option value="10" <c:if test="${order.state eq 10}">selected</c:if>>주문 취소</option>
								<option value="11" <c:if test="${order.state eq 11}">selected</c:if>>주문 신청</option>
								<option value="12" <c:if test="${order.state eq 12}">selected</c:if>>주문 확인</option>
								<option value="13" <c:if test="${order.state eq 13}">selected</c:if>>포장 / 발송대기</option>
								<option value="14" <c:if test="${order.state eq 14}">selected</c:if>>배송중</option>
								<option value="15" <c:if test="${order.state eq 15}">selected</c:if>>배송 완료</option>
								<option value="16" <c:if test="${order.state eq 16}">selected</c:if>>구매 확정</option>
								<option value="17" <c:if test="${order.state eq 17}">selected</c:if>>환불 신청</option>
								<option value="18" <c:if test="${order.state eq 18}">selected</c:if>>환불 완료</option>
						</select>
					</td>
					<td height="35" class="td_default" align="center">
						<span><a href="/Final_Project/identityCheck/deliveryInfo?ordernum=${order.ordernum}" target="_blank">${order.orderName}</a></span>
					</td>
				</tr>
				</c:forEach>

			</table>
		</td>
	</tr>

	<tr>
		<td height="40">
	</tr>

	<tr>
		<td class="td_default" align="center">
			<b>
					<c:forEach var="i" end="${pagingDTO.totalpage}" begin="1">
					<c:choose>
						<c:when test="${i eq pagingDTO.page}">
							<span style="color: red">${i}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="/Final_Project/adminCheck/orderList?page=${i}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</b>
		</td>
	</tr>

	<tr>
		<td height="15">
	</tr>

</table>
</div>