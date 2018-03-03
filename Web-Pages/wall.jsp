<%@ include file="includes/user/header.jsp"%>

<p id="pagehead">
	<c:out value="${userData.firstName}"></c:out>
	<c:out value="${userData.lastName}"></c:out>
	's wall
</p>


<c:if test="${sessionScope.statusMsg > 0}">
	<div class="message success-message">
		<p>
			<strong>Posted successfully !</strong>
		</p>
	</div>
	<c:remove var="postMessage" scope="session" />
</c:if>

<c:if test="${sessionScope.wmessages.filteredStatusMessage != null}">
	<div class="message error-message" style="height:auto;text-align:left;padding:10px 30px;">
		<p style="font-weight:bold;">${sessionScope.wmessages.filteredStatusMessage}</p>
		<c:forEach var="mes" items="${sessionScope.filterMessages}">
    ${mes.key} : ${mes.value}<br/>
</c:forEach>
	</div>
</c:if>


<div style="width: 80%; margin: 0 auto;">
	<h2>What's On Your Mind ?</h2>

	<form class="form form-aligned" method="post"
		action="wall?id=<c:out value="${userData.userId}"></c:out>">
		<fieldset>

			<div class="control-group">

				<textarea style="width: 80%; height: 60px;" name="postMessage" required="required">${sessionScope.postMessage}</textarea>
				<span class="red">${sessionScope.wmessages.postMesg}</span>
			</div>

			<div style="float: right; margin-right: 120px;">
				<input type="submit" class="button button-primary" value="POST" />
			</div>
		</fieldset>
	</form>

</div>

<div>
	<table>
		<c:forEach items="${messageList}" var="message">
			<tr>
				<td class="top"><img src="images/avatar.jpg"
					style="width: 100px" /></td>
				<td width="10"></td>
				<td>
					<p class="nametext">
						<c:out value="${message.firstName}"></c:out>
						<c:out value="${message.lastName}"></c:out>
					</p>
					<p>
						<c:out value="${message.post}"></c:out>
					<p /> <c:out value="${message.postedTime}"></c:out>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>

<c:remove var="wmessages" scope="session" />
<c:remove var="statusMsg" scope="session" />
<c:remove var="filterMessages" scope="session" />
<c:remove var="postMessage" scope="session" />

<%@ include file="includes/user/footer.jsp"%>
