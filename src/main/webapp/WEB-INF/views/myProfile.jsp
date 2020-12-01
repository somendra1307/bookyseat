<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page='header.jsp' />
<jsp:include page='message.jsp' />
<section>
	<div class="container-fluid">
		My Account Details:
		<div class="row content">
			<div class="col-sm-2 sidenav"></div>
			<form:form method="post" modelAttribute="userProfile"
				action="saveProfile">
				<form:input class="form-control" id="id" type="hidden" path="id" />
				<div class="col-sm-8">
					<div class="form-group">
						<label class="col-sm-3">Name:</label>
						<div class="col-sm-9">
							<form:input class="form-control" id="name" type="text"
								path="name" />
						</div>
						<br> <br>
					</div>
					<div class="form-group">
						<label class="col-sm-3">BirthDate:</label>
						<div class="col-sm-9">
							<form:input class="form-control" id="birthDate" type="datetime"
								path="birthDate" />
						</div>
						<br> <br>
					</div>
					<div class="form-group">
						<label class="col-sm-3">Mobile Number:</label>
						<div class="col-sm-9">
							<form:input class="form-control" id="mobileNumber" type="number"
								path="mobNumber" />
						</div>
						<br> <br>
					</div>
					<div class="form-group">
						<label class="col-sm-3">Email ID:</label>
						<div class="col-sm-9">
							<form:input class="form-control" id="emailId" type="text"
								path="emailId" />
						</div>
						<br> <br>
					</div>

					<div>
						<label class="col-sm-3"></label>
						<div class="col-sm-9">
							<br> <br>
							<button class="btn btn-success" type="submit">Save
								Profile</button>
						</div>
						<br> <br>
					</div>
				</div>
			</form:form>
			<div class="col-sm-2 sidenav">
				<div class="well">
					<p>Advertisement Section1</p>
				</div>
				<div class="well">
					<p>Advertisement Section2</p>
				</div>
				<div class="well">
					<p>Advertisement Section3</p>
				</div>
				<div class="well">
					<p>Advertisement Section4</p>
				</div>
			</div>
		</div>
	</div>
</section>
<jsp:include page='footer.jsp' />
