<%@ page import="beans.*"%>
<%@ page import="servlets.*"%>
<!-- 
</div>
</div>
<div id="secondaryContent_2columns">
	<div id="columnC_2columns">
		<h4>
			<span>Search</span>
		</h4>
		<form method="post" action="#">
			<div id="search">
				<input type="text" class="text" name="keywords" /> <input
					type="submit" class="button" value="Go" /> <br class="clear" />
			</div>
		</form>
		<h4>
			<span>Profile</span>
		</h4>
		<p></p>
		<br />
		<h4>
			<span></span>
		</h4>

		<form action="/Forum/Logging" method="post">
			<label for="userName">Name:</label> <input type="text" size="10"
				name="userName" id="userName" /> <label for="password">Password:</label>
			<input type="password" size="10" name="password" id="password" />
			<div style="text-align: right;">
				<a href="registration.jsp">Register</a> | <input type="submit"
					class="button" value="Go!" name="registration" id="registration" />
			</div>
		</form>

		<label style="color: red;" id="loggingError">${loggingError}</label>
-->
		<h4>
			<span>Latest </span>topics
		</h4>
		<ul class="links">
			<li class="first"><a href="#">Fusce dui neque fringilla</a></li>
			<li><a href="#">Eget tempor eget nonummy</a></li>
			<li><a href="#">Nec metus sed donec</a></li>
			<li><a href="#">Velit semper nisi molestie</a></li>
			<li><a href="#">Consequat sed cursus</a></li>
			<li><a href="#">Nisi tempus nullam</a></li>
			<li><a href="#">Magna sed bibendum mauris</a></li>
			<li><a href="#">Velit semper nisi molestie</a></li>
			<li><a href="#">Consequat sed cursus</a></li>
			<li><a href="#">Id posuere metus sem</a></li>
			<li><a href="#">Eget tempor eget nullam</a></li>
			<li><a href="#">Velit semper nisi molestie</a></li>
			<li><a href="#">Consequat sed cursus</a></li>
		</ul>
	</div>
</div>
<br class="clear" />
</div>
</div>
<div id="footer" class="fixed">
	Copyright &copy; 2017 Zivko Stanisic. All rights reserved. <br />Design
	by <a href="http://www.nodethirtythree.com/">NodeThirtyThree Design</a>
	and <a href="http://www.freewebsitetemplates.com">Free Website
		Templates</a>.
</div>
</body>
</html>