<%@ include file="includes/admin/header.jsp" %>

<p id="pagehead">EDIT CATEGORY</p>
		
<c:if test="${requestScope.statusMsg > 0}">
<div class="message success-message">
	<p><strong>Edited Successfully !</strong></p>
</div>
</c:if>
  
      <form class="form form-aligned" method="post" action="category?action=edit&id=<c:out value="${param.id}"></c:out>">
    <fieldset>   

        
       <div class="control-group">
            <label for="name">Add Category</label>
            <input name="categoryid" type="hidden" value="<c:out value="${category.categoryId}"></c:out>">
            <input id="categoryname" name="categoryname" type="text" value="<c:out value="${category.categoryName}"></c:out>" required="required">
        </div>
      
        <div class="controls">
           
            <button type="submit" class="button button-primary">Submit</button>

        </div>
    </fieldset>
</form>

<%@ include file="includes/admin/footer.jsp" %>