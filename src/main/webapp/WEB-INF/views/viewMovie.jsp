<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page='header.jsp' />
<jsp:include page='message.jsp' />
<section>
	<div class="container-fluid">
		Movie Details:
		<div class="row content">

			<div class="col-sm-10">

				<div class="col-sm-12">
					<table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Movie Details:</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Name</td>
								<td>${movie.name}</td>
							</tr>
							<tr>
								<td>Producer Name</td>
								<td>${movie.producerName}</td>
							</tr>
							<tr>
								<td>Director Name</td>
								<td>${movie.directorName}</td>
							</tr>
							<tr>
								<td>Starring</td>
								<td>${movie.starring}</td>
							</tr>
							<tr>
								<td>Genres</td>
								<td>${movie.genres}</td>
							</tr>
							<tr>
								<td>Budget(in INR)</td>
								<td>${movie.budget}</td>
							</tr>
							<tr>
								<td>Release Date</td>
								<td>${movie.releaseDate}</td>
							</tr>
							<tr>
								<td>Language</td>
								<td>${movie.language}</td>
							</tr>
							<tr>
								<td>Country</td>
								<td>${movie.country}</td>
							</tr>
							<tr>
								<td>Booking Active</td>
								<td>${movie.bookingActive}</td>
							</tr>
							<tr>
								<td>Duration (in Minutes)</td>
								<td>${movie.duration}</td>
							</tr>
							<tr>
								<td>Poster Image</td>
								<td><img src="${movie.imageUrl}"></td>
							</tr>
						</tbody>
					</table>
					<a href="bookTicket?movieId=${movie.id}" class="btn btn-info">Book Ticket</a>
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
		</div>
	</div>
</section>
<jsp:include page='footer.jsp' />
