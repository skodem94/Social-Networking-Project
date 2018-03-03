<%@ include file="includes/user/header.jsp"%>

<p id="pagehead" style="border: none;">Settings</p>

<c:if test="${messages.success != null}">
	<div class="message success-message">
		<p>
			<strong>${messages.success}</strong>
		</p>
	</div>
</c:if>

<c:if test="${statusMsg > 0}">
	<div class="message success-message" style="width: auto">
		<p>
			<strong>Updated Successfully !</strong>
		</p>
	</div>
</c:if>

<form class="form form-aligned" action="settings" method="post">
	<fieldset>
		<legend>Personal Details</legend>
		<div class="control-group">
			<label for="firstname">First Name</label> <input id="firstname"
				name="firstname" type="text"
				value="<c:out value="${param.firstname}"></c:out><c:out value="${userData.firstName}"></c:out>">
			<span class="red">${messages.firstname}</span>
		</div>
		<div class="control-group">
			<label for="lastname">Last Name</label> <input id="lastname"
				name="lastname" type="text"
				value="<c:out value="${param.lastname}"></c:out><c:out value="${userData.lastName}"></c:out>">
			<span class="red">${messages.lastname}</span>
		</div>

		<div class="control-group">
			<label for="lastname">Gender</label> <input id="radio-one"
				type="radio" name="gender" value="male"
				<c:if test="${param.gender == 'male'}">checked="checked"</c:if>
				<c:if test="${userData.gender == 'male'}">checked="checked"</c:if>>
			Male <input id="radio-two" type="radio" name="gender" value="female"
			<c:if test="${param.gender == 'female'}">checked="checked"</c:if>
				<c:if test="${userData.gender == 'female'}">checked="checked"</c:if>>
			Female
		</div>

		<div class="control-group">
			<label for="dob">Date of Birth</label> <input id="dob" name="dob"
				type="text"
				value="<c:out value="${param.dob}"></c:out><c:out value="${userData.dob}"></c:out>">
			<span class="red">${messages.dob}</span>
		</div>
		<div class="control-group">
			<label for="profession">Profession</label> <input id="profession"
				name="profession" type="text"
				value="<c:out value="${param.profession}"></c:out><c:out value="${userData.profession}"></c:out>">
			<span class="red">${messages.profession}</span>
		</div>

		<div class="control-group">
			<label for="location">Location</label> <input id="location"
				name="location" type="text"
				value="<c:out value="${param.location}"></c:out><c:out value="${userData.location}"></c:out>">
			<span class="red">${messages.location}</span>
		</div>
		<legend>Contact Details</legend>
		<div class="control-group">
			<label for="email">Email Address</label> <input id="email"
				type="email" name="email"
				value="<c:out value="${param.email}"></c:out><c:out value="${userData.emailAddress}"></c:out>">
			<span class="red">${messages.email}</span>
		</div>

		<div class="control-group">
			<label for="mobileno">Mobile No.</label> <input id="mobileno"
				name="mobileno" type="text"
				value="<c:out value="${param.mobileno}"></c:out><c:out value="${userData.mobileNo}"></c:out>">
			<span class="red">${messages.mobileno}</span>
		</div>
		<legend>Content Filtering Rules</legend>
		<br>
		<div class="control-group" style="margin-left: 80px;">
			<c:forEach items="${categories}" var="category">

				<input id="radio-one" type="checkbox" name="filterCategories"
					value="<c:out value="${category.categoryId}"></c:out>"
					<c:forEach var="item" items="${fcategories}">
  <c:if test="${item eq category.categoryId}">
    checked="checked"
  </c:if>
</c:forEach>>
				<c:out value="${category.categoryName}"></c:out>
				<br>
				<br>
			</c:forEach>
		</div>

		<div class="controls">
			<input type="submit" class="button button-primary" value="Submit">
			<input type="reset" class="button button-primary" value="Reset">
		</div>
	</fieldset>
</form>

<%@ include file="includes/user/footer.jsp"%>