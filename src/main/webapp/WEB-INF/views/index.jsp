<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='header.jsp' />
<jsp:include page='message.jsp' />
<div class="main-banner banner text-center">
	<div class="container">
		<h1>
			Book<span class="segment-heading"> Movie Tickets Online With</span>
			BookMySeat
		</h1>
		<p>Search movies here:</p>
	</div>
</div>
<div class="content">
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
										href="contactSeller?adId=${movie.id}" class="btn btn-info">Book
										Ticket</a>
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
<jsp:include page='footer.jsp' />