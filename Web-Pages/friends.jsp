<%@ include file="includes/user/header.jsp"%>


		<c:if test="${requestScope.successMsg != null}">
		<div class="message success-message">
			<p><strong>${requestScope.successMsg}</strong></p>
		</div>
		</c:if>

<p id="pagehead">
	<c:if test="${requestScope.pageType == 0}">
		My Friends
	</c:if>
	<c:if test="${requestScope.pageType == 1}">
		Find Friends
	</c:if>
</p>


<div>
	<table>
	    <c:if test="${friendsList.size() <= 0 }">
    <tr><td colspan="5" align="center">No more friends.</td></tr>
    </c:if>

		<c:forEach items="${friendsList}" var="friend">
			<tr>
				<td class="top"><img src="images/avatar.jpg" style="width: 100px" /></td>
				<td width="10"></td>
				<td>
					<div class="nametext">
						<c:out value="${friend.firstName}"></c:out>
						<c:out value="${friend.lastName}"></c:out>
					</div>
					<p class="muted"><c:out value="${friend.profession}"></c:out>
					<br><c:out value="${friend.location}"></c:out></p>
					<p>
					<c:if test="${requestScope.pageType == 0}">
						<a href="wall?id=<c:out value="${friend.userid}"></c:out>">Post on wall</a>
					</c:if>
					<c:if test="${requestScope.pageType == 1}">
					<a href="friends?action=add&id=<c:out value="${friend.userid}"></c:out>">Add as friend</a>
					</c:if>
					<p />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>


<%@ include file="includes/user/footer.jsp"%>
