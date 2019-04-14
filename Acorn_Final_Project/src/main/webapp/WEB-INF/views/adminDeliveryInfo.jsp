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
	
	<script type="text/javascript">
	$(document).ready(function(){
		$("select").on("change", function(event){
			var beforState = selectState($(this).attr("ordernum-data"), $(this).val());
		})
		
		$("#addressChange").on("click", function(event){
			$.ajax({
				type : "GET",
				url : "/Final_Project/identityCheck/addressChange",
				dataType : "text",
				data : {
					ordernum : $(this).attr("ordernum-data"),
					orderName : $("#ordername").val(),
					addr_post : $("#addr_post").val(),
					addr_f : $("#addr_f").val(),
					addr_l : $("#addr_l").val()
					
				},
				success : function(Data, status, xhr) {
					alert("수정 완료");
				},
				error : function(xhr, status, error) {
					console.log("error");
				}
			})
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
        		<select ordernum-data = '${orderInfo.ordernum}'>
					<option value="10" <c:if test="${orderInfo.state eq 10}">selected</c:if>>주문 취소</option>
					<option value="11" <c:if test="${orderInfo.state eq 11}">selected</c:if>>주문 신청</option>
					<option value="12" <c:if test="${orderInfo.state eq 12}">selected</c:if>>주문 확인</option>
					<option value="13" <c:if test="${orderInfo.state eq 13}">selected</c:if>>포장 / 발송대기</option>
					<option value="14" <c:if test="${orderInfo.state eq 14}">selected</c:if>>배송중</option>
					<option value="15" <c:if test="${orderInfo.state eq 15}">selected</c:if>>배송 완료</option>
					<option value="16" <c:if test="${orderInfo.state eq 16}">selected</c:if>>구매 확정</option>
					<option value="17" <c:if test="${orderInfo.state eq 17}">selected</c:if>>환불 신청</option>
					<option value="18" <c:if test="${orderInfo.state eq 18}">selected</c:if>>환불 완료</option>
				</select>
        	</td>
        </tr>
        <tr>
        	<td colspan="3"><strong>배송지 정보</strong></td>
        </tr>
        <tr>
        	<td colspan="3">
	        	   	<table>
	        		<tr>
						<td width="180px">수령자</td>
						<td>
							<input type="text" value="${orderInfo.orderName}" name="ordername" id="ordername">
						</td>
	        		</tr>
	        	</table>
        	</td>
        </tr>
        <tr>
        	<td colspan="3">
        		<table>
	        		<tr>
						<td width="180px" >우편번호</td>
						<td>
							<input type="text" value="${orderInfo.addr_post}" name="addr_post" maxlength="5" id="addr_post">
						</td>
	        		</tr>
	        	</table>
	        </td>
        </tr>
        <tr>
        	<td colspan="3">
        		<table>
	        		<tr>
						<td width="180px">주소 상세정보</td>
						<td>
							<input type="text" value="${orderInfo.addr_f}" name="addr_f" size="${orderInfo.addr_f.length()+10}" id="addr_f"><br>
							<input type="text" value="${orderInfo.addr_l}" name="addr_l" size="${orderInfo.addr_l.length()+10}" id="addr_l">
						</td>
	        		</tr>
	        	</table>
	        </td>
        </tr>
        <tr>
        	<td colspan="3" align="center">
        		<br><br>
        		<input type="button" id="addressChange" value="배송지 정보 수정" ordernum-data="${orderInfo.ordernum}">
        		<br>
	        </td>
        </tr>
	</table>
	</div>
</body>
</html>