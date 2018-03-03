<%@ include file="includes/admin/header.jsp" %>

<p id="pagehead">EDIT WORD OR TEXT</p>

<c:if test="${requestScope.statusMsg > 0}">
<div class="message success-message">
	<p><strong>Edited Successfully !</strong></p>
</div>
</c:if>
  
<form class="form form-aligned" method="post" action="word?action=edit&id=${param.id}">
    <fieldset>
    
      <div class="control-group">
          <label for="email">Select Category</label>
            <select name="categoryId" style="width:250px;"> 
            <c:forEach items="${categories}" var="category">            
             <option <c:if test="${text.categoryId == category.categoryId }">selected="selected"</c:if> value="<c:out value="${category.categoryId}"></c:out>"><c:out value="${category.categoryName}"></c:out></option>
            </c:forEach>
            </select>
            <a href="category?action=add">ADD NEW</a>
      </div>
                
        <div class="control-group">
            <label for="email">Word Or Text</label>
            <textarea name="wordText" rows="10" style="width:350px;" required="required"><c:out value="${text.text}"></c:out></textarea>
        </div>
      
        <div class="controls">           
            <button type="submit" class="button button-primary">Submit</button>
        </div>
        
    </fieldset>
</form>


<%@ include file="includes/admin/footer.jsp" %>