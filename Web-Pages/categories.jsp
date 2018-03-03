<%@ include file="includes/admin/header.jsp" %>

<p id="pagehead">VIEW CATEGORIES</p>
        
       <table class="table table-bordered" style="width:750px;">
    <thead>
        <tr>
            <th>S.No</th>
            <th>Category ID</th>
            <th>Category Name</th>
            <th></th>
        </tr>
    </thead>

    <tbody>
    <c:if test="${categories.size() <= 0 }">
    <tr><td colspan="4" align="center">No words found.</td></tr>
    </c:if>
	<c:forEach items="${categories}" var="category" varStatus="status" >
        <tr>
            <td><c:out value="${status.count}"></c:out></td>
            <td>${category.categoryId}</td>
            <td><c:out value="${category.categoryName}"></c:out></td>
            <td><a href="category?action=edit&id=<c:out value="${category.categoryId}"></c:out>">Edit</a></td>
        </tr>
  	</c:forEach>        
    </tbody>
</table>

<%@ include file="includes/admin/footer.jsp" %>