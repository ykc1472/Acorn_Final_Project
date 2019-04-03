<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
	$(document).ready(function(){
		$(".updateInfo").on("click", function(event){
			var fcode = $(this).attr("data-fcode");
			var foption = $(this).attr("data-foption");
			$.ajax({
				type : "POST",
				url : "/Final_Project/adminCheck/updateFoodInfo",
				dataType : "text",
				data : {
					fcode : fcode,
					foption : foption,
					ftitle : $("#ftitle_"+fcode).val(),
					fprice : $("#fprice_"+fcode).val(),
					fcategory : $("#fcategory_"+fcode).val(),
					content : $("#content_"+fcode).val(),
					optionname : $("#optionname_"+fcode+"_"+foption).val(),
					optionprice : $("#optionprice_"+fcode+"_"+foption).val(),
					stock : $("#stock_"+fcode+"_"+foption).val()
				},
				success : function(Data, status, xhr) {
				   console.log(Data);
				},
				error : function(xhr, status, error) {
					console.log("error");
				}
			}) 
		}) // end updateInfo
		
		$(".deleteInfo").on("click", function(event){
			var fcode = $(this).attr("data-fcode");
			var foption = $(this).attr("data-foption");
			var ftitle = $("#ftitle_"+fcode).val(); 
			var optionname = $("#optionname_"+fcode+"_"+foption).val();
		    if (confirm("제품 : " + ftitle + "의 \n" + optionname + "을 삭제하시겠습니까?") == true) {
				location.replace('/Final_Project/adminCheck/deleteFoodOption?fcode='+fcode+'&foption='+foption);
		    }
		});
	})
</script>

<div align="center">
	<form>
		<table>
		<tr>
			<td colspan="10"><hr></td>
		</tr>
			<tr>
				<th>카테고리</th>
				<th>상품명</th>
				<th>기본 가격</th>
				<th>이미지</th>
				<th>제품설명</th>
				<th>옵션명</th>
				<th>가격차액</th>
				<th>재고량</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<tr>
				<td colspan="10"><hr></td>
			</tr>
			<c:forEach items="${foodInfoList.foodlist}" var="foodInfo" varStatus="status">
			<tr>
				<c:choose>
					<c:when test="${status.index eq 0}">
						<td><select name="fcategory" id="fcategory_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}">
						<c:forEach items="${categoryList}" var="category">
							<c:choose>
								<c:when test="${category.FCATEGORY eq foodInfo.fcategory}">
									<option value="${category.FCATEGORY}" selected>${category.CATEGORYNAME}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.FCATEGORY}">${category.CATEGORYNAME}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select></td>
					<td><input type="text" name="ftitle" id="ftitle_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}" value="${foodInfo.ftitle}"></td>
					<td><input type="text" name="fprice" id="fprice_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}" value="${foodInfo.fprice}"></td>
					<c:choose>
						<c:when test="${foodInfo.fimage eq null}">
							<td><input type="button" value="등록하기" data-fcode="${foodInfo.fcode}"></td>
						</c:when>
						<c:otherwise>
							<td><input type="button" value="수정하기" data-fcode="${foodInfo.fcode}"></td>
						</c:otherwise>
					</c:choose>
					<td><input type="text" name="content" id="content_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}" value="${foodInfo.content}"></td>
					</c:when>
					<c:when test="${foodInfo.fcode ne foodInfoList.foodlist[(status.index)-1].fcode}">
						<td><select name="fcategory" id="fcategory_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}">
						<c:forEach items="${categoryList}" var="category">
							<c:choose>
								<c:when test="${category.FCATEGORY eq foodInfo.fcategory}">
									<option value="${category.FCATEGORY}" selected>${category.CATEGORYNAME}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.FCATEGORY}">${category.CATEGORYNAME}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select></td>
					<td><input type="text" name="title" id="title_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}" value="${foodInfo.ftitle}"></td>
					<td><input type="text" name="fprice" id="fprice_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}" value="${foodInfo.fprice}"></td>
					<c:choose>
						<c:when test="${foodInfo.fimage eq null}">
							<td><input type="button" value="등록하기" data-fcode="${foodInfo.fcode}"></td>
						</c:when>
						<c:otherwise>
							<td><input type="button" value="수정하기" data-fcode="${foodInfo.fcode}"></td>
						</c:otherwise>
					</c:choose>
					<td><input type="text" name="content" id="content_${foodInfo.fcode}" data-fcode="${foodInfo.fcode}" value="${foodInfo.content}"></td>
					</c:when>
					<c:otherwise>
						<td colspan="5"></td>
					</c:otherwise>
				</c:choose>
				<td><input type="text" name="optionname" id="optionname_${foodInfo.fcode}_${foodInfo.foption}" data-fcode="${foodInfo.fcode}" data-foption="${foodInfo.foption}" value="${foodInfo.optionname}"></td>
				<td><input type="text" name="optionprice" id="optionprice_${foodInfo.fcode}_${foodInfo.foption}" data-fcode="${foodInfo.fcode}" data-foption="${foodInfo.foption}" value="${foodInfo.optionprice}"></td>
				<td><input type="text" name="stock" id="stock_${foodInfo.fcode}_${foodInfo.foption}" data-fcode="${foodInfo.fcode}" value="${foodInfo.stock}"></td>
				<td><input type="button" value="수정" data-fcode="${foodInfo.fcode}" data-foption="${foodInfo.foption}" class="updateInfo"></td>
				<td><input type="button" value="삭제" data-fcode="${foodInfo.fcode}" data-foption="${foodInfo.foption}" class="deleteInfo"></td>
			</tr>
			<tr>
				<td colspan="10"><hr></td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="10" align="center">
					<c:forEach var="i" end="${foodInfoList.totalpage}" begin="1">
						<c:choose>
							<c:when test="${i eq foodInfoList.page}">
								<span style="color: red">${i}</span>&nbsp;
							</c:when>
							<c:otherwise>
								<a href="/Final_Project/adminCheck/foodInfoManagement?page=${i}">${i}</a>&nbsp;
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			
		</table>
	</form>
</div>