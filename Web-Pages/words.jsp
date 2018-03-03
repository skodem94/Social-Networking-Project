<%@ include file="includes/admin/header.jsp" %>

		<p id="pagehead">VIEW WORDS</p>
       <table class="table table-bordered" style="width:750px;">
    <thead>
        <tr>
            <th>S.No</th>
            <th>Category</th>
            <th>Word</th>
            <th>Date</th>
            <th></th>
        </tr>
    </thead>

    <tbody>
    <c:if test="${words.size() <= 0 }">
    <tr><td colspan="5" align="center">No words found.</td></tr>
    </c:if>
    <c:forEach items="${words}" var="word" varStatus="status">
        <tr>
            <td>${status.count}</td>
            <td><c:out value="${word.categoryName}"></c:out></td>
             <td><c:out value="${word.text}"></c:out></td>
            <td><c:out value="${word.createdDate}"></c:out></td>
             <td><a href="word?action=edit&id=${word.textId}">Edit</a></td>
        </tr>
	</c:forEach>
        
    </tbody>
</table>


<%@ include file="includes/admin/footer.jsp" %>