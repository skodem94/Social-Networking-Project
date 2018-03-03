<%@ include file="includes/admin/header.jsp" %>

<p id="pagehead">ADD CATEGORY</p>
		
<c:if test="${requestScope.statusMsg > 0}">
<div class="message success-message">
	<p><strong>Added Successfully !</strong></p>
</div>
</c:if>
  
      <form class="form form-aligned" method="post" action="category?action=add">
    <fieldset>   

        
       <div class="control-group">
            <label for="name">Add Category</label>
            <input id="categoryname" name="categoryname" type="text" value="" required="required">
        </div>
      
        <div class="controls">
           
            <button type="submit" class="button button-primary">Submit</button>

        </div>
    </fieldset>
</form>

<%@ include file="includes/admin/footer.jsp" %>