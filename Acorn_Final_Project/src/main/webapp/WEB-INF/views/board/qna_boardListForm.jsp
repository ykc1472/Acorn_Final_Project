<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
</style>

<script type="text/javascript">
	function move(url){
		location.href=url;
	}
	$(document).ready(function(){
		$(".board").on("mouseover", function(event){
			$(this).css("background", "rgba( 128, 128, 128, 0.3 )");
			
		})
		$(".board").on("mouseout", function(event){
			$(this).css("background", "");
		})
	})
</script>
</head>

<div align="center">
	<marquee behavior="alternate" scrolldelay="100" direction="right">
	디저트는 사랑이야!</marquee>
	<table class="bbs" width="800" height="200" border="2">
		<colgroup>
			<col width="100" />
			<col width="500" />
			<col width="150" />
			<col width="160" />
			<col width="100" />
		</colgroup>
		<thead>
			<tr>
				<th>번 호</th>
				<th>제 목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="list" items="${paging.qnaboardlist}">
			<c:choose>
				<c:when test="${(list.qna_option eq 0) && loginInfo.grade ne 99}">
					<tr class="board">
						<td align="center">${list.qna_num}</td>
						<td>삭제된 글입니다.</td>
				</c:when>
				<c:when test="${(list.qna_option eq 1 || list.qna_option eq 2) && loginInfo.userid ne list.userid && loginInfo.grade ne 99}">
					<tr class="board">
						<td align="center">${list.qna_num}</td>
						<td>${list.qna_title} / ${userInfo.userid}  ${list.userid}<img src="image/item/lock.png" width="30px"></td>
				</c:when>
				<c:otherwise>
					<tr onclick="location.href='qnaBoardForm?pick=${list.qna_num}'" class="board">
						<td align="center">${list.qna_num}</td>
						<td>${list.qna_title} <c:if test="${list.qna_option eq 0 }">[삭제]</c:if></td>
				</c:otherwise>
			</c:choose>
				<td align="center">${list.nickname}</td>
				<td align="center">${list.writedate}</td>
				<td align="center">${list.readCount}</td>
			</tr>
			<c:forEach var="commentlist" items="${paging.qnacommentlist}">
				<c:if test="${commentlist.qna_num == list.qna_num}">
					<c:choose>
						<c:when test="${(list.qna_option eq 0) && loginInfo.grade ne 99}">
							<tr class="board">
								<td align="center">${list.qna_num}</td>
								<td>삭제된 글입니다.</td>
						</c:when>
						<c:when test="${(list.qna_option eq 1 || list.qna_option eq 2) && loginInfo.userid ne list.userid && loginInfo.grade ne 99}">
							<tr class="board">
								<td align="center">&nbsp;ㄴ</td>
								<td>${commentlist.qnac_title}<img src="image/item/lock.png" width="30px"></td>
						</c:when>
						<c:otherwise>
							<tr class="board" onclick="location.href='qnaCommentBoardForm?pick=${list.qna_num}'">
								<td align="center">&nbsp;ㄴ</td>
								<td>${commentlist.qnac_title}<c:if test="${list.qna_option eq 0 }">[삭제]</c:if></td>
						</c:otherwise>
					</c:choose>
						<td align="center">${commentlist.nickname}</td>
						<td align="center">${commentlist.writedate}</td>
						<td align="center">${commentlist.readCount}</td>
					</tr>
				</c:if>
			</c:forEach>
		</c:forEach>
		</tbody>
	
		<tfoot>
			<tr>
				<td colspan="5" align="center">
				<c:forEach var="i" end="${paging.totalpage}" begin="1">
					<c:choose>
						<c:when test="${i eq paging.page}">
							<span style="color: red">${i}</span>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="qnaBoardListForm?page=${i}">${i}</a>&nbsp;
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</td>
			</tr>
		</tfoot>		
	</table>
		
		<input type="button" value="처음으로" onclick="move('qnaBoardListForm');" />
		<input type="button" value="글쓰기" onclick="move('loginCheck/qnawriteboardForm');" />
</div>
