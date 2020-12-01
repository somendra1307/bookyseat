<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='header.jsp' />
<jsp:include page='message.jsp' />
<style>
.checked {
	color: orange;
}
</style>
<section>
	<div class="col-sm-2 sidenav">Movie Ratings:</div>

	<div class="col-sm-8 sidenav content">
		<div class="container-fluid">
			<div class="row content">
				<div class="categories">
					<div class="container">
						<div class="row">
							<c:forEach items="${ratings}" var="rating">
								<div class="col-md-3">
									<div>
										<img class="group list-group-image"
											src="${rating.movie.imageUrl}" height="150" width="100%"
											alt="" />
										<c:set var="total" value="1" />
										<c:forEach var="i" begin="1" end="${rating.rating.rating}">
											<span class="fa fa-star checked"></span>
										</c:forEach>
										<c:forEach var="j" begin="${total+rating.rating.rating}"
											end="5">
											<span class="fa fa-star"></span>
										</c:forEach>
										<p>
											<b>Review: </b> ${rating.rating.reviewText}
										</p>
										<br>
										<div>
											<h5 class="group inner list-group-item-heading">
												<b>User: </b> ${rating.user.emailId}
											</h5>
											<h5 class="group inner list-group-item-text">
												<b>Movie: </b> ${rating.movie.name}<br>
											</h5>
											<div>
												<a href="deleteRating?ratingId=${rating.rating.id}"
													class="btn btn-danger">Delete Rating</a>
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
