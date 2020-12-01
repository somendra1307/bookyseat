<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='header.jsp' />
<jsp:include page='message.jsp' />
<section>
	<div class="col-sm-2 sidenav">
		<a href="addMovie" class="btn btn-warning">Add New Movie</a>
	</div>

	<div class="col-sm-8 sidenav content">
		<div class="container-fluid">
			<div class="row content">
				<div class="categories">
					<div class="container">
						<div class="row">
							<c:forEach items="${movies}" var="movie">
								<div class="col-md-3">
									<div>
										<img class="group list-group-image" src="${movie.imageUrl}"
											height="150" width="100%" alt="" />
										<div>
											<h5 class="group inner list-group-item-heading">
												<b>${movie.name}</b>
											</h5>
											<h5 class="group inner list-group-item-text">
												Directed By : ${movie.directorName} <br>Produced By :
												${movie.producerName}
											</h5>
											<h4>Language : ${movie.language}</h4>
											<div>
												<a href="viewMovieDetails?movieId=${movie.id}"
													class="btn btn-success">View Details</a> <a
													href="deleteMovie?movieId=${movie.id}"
													class="btn btn-danger">Delete Movie</a> <a
													href="editMovie?movieId=${movie.id}" class="btn btn-info">Edit
													Details</a>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
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

</section>
<jsp:include page='footer.jsp' />
