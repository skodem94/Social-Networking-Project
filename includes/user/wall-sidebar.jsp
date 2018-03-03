<div id="sidebar">
    <img src="images/avatar.jpg" />
   <p class="dptext">${userData.firstName} ${userData.lastName}</p>
    <ul>
    <!-- <li><a href="friends">My Friends</a></li>
	<li><a href="mywall.jsp">My wall</a></li>
	<li><a href="friends?action=find">Find Friends</a></li> -->
	<li><a href="wall?id=${param.id}">Post on wall</a></li>
	<!--<li><a href="friends?action=remove&id=${param.id}">Remove Friend</a></li> -->
    </ul>
    </div>  