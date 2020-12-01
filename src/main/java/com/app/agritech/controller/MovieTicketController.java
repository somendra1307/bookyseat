package com.app.agritech.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.app.agritech.constant.States;
import com.app.agritech.constant.UserType;
import com.app.agritech.dto.MovieTicketDTO;
import com.app.agritech.dto.MovieRatingDTO;
import com.app.agritech.entity.Address;
import com.app.agritech.entity.Movie;
import com.app.agritech.entity.Ticket;
import com.app.agritech.entity.PersonalDetails;
import com.app.agritech.entity.MovieRating;
import com.app.agritech.entity.MovieShow;
import com.app.agritech.entity.UserDetails;
import com.app.agritech.messaging.EmailData;
import com.app.agritech.service.MovieTicketService;
import com.app.agritech.service.CloudinaryService;
import com.app.agritech.service.EmailService;
import com.app.agritech.utility.DateSupport;
import com.app.agritech.utility.Utility;

@Controller
public class MovieTicketController {

	@Autowired
	MovieTicketService movieTicketService;

	@Autowired
	EmailService emailService;

	@Autowired
	CloudinaryService cloudinaryService;

	@GetMapping("/logout")
	public String logOut(ModelMap model, HttpServletRequest request) {
		UserDetails user = getUserFromRequest(request);
		user.setLoggedIn(false);
		movieTicketService.saveUserDetails(user);
		request.getSession().setAttribute("user", null);
		model.put("success", "You are logged out successfully.");
		return "login";
	}

	@GetMapping("/addMovie")
	public String addTaskpage(ModelMap model, HttpServletRequest request) {
		model.clear();
		return "addMovie";
	}

	@GetMapping("/manageMovies")
	public String manageMovies(ModelMap model, HttpServletRequest request) {
		model.clear();
		List<Movie> movies = movieTicketService.searchMovies(null);
		model.put("movies", movies);
		return "manageMovies";
	}

	@GetMapping("/manageBookings")
	public String manageBookings(ModelMap model, HttpServletRequest request) {
		model.clear();
		model.clear();
		List<Ticket> tickets = movieTicketService.getAllMovieTickets();

		List<MovieTicketDTO> movieTickets = tickets.stream().map(x -> {
			return generateMovieTicketDTO(x);
		}).collect(Collectors.toList());
		model.put("tickets", movieTickets);
		return "manageBookings";
	}

	@GetMapping("/manageUsers")
	public String manageUsers(ModelMap model, HttpServletRequest request) {
		model.clear();
		List<UserDetails> users = movieTicketService.getAllUserDetails();
		model.put("users", users);
		return "manageUsers";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(ModelMap model, HttpServletRequest request) {
		model.clear();
		movieTicketService.deleteUser(Long.parseLong(request.getParameter("userId")));
		List<UserDetails> users = movieTicketService.getAllUserDetails();
		model.put("users", users);
		model.put("success", "User Deleted Successfully");
		return "manageUsers";
	}

	@GetMapping("/manageRatings")
	public String manageRatings(ModelMap model, HttpServletRequest request) {
		model.clear();
		List<MovieRating> movieRatings = movieTicketService.getMovieRatings(null);
		List<MovieRatingDTO> ratings = movieRatings.stream().map(x -> {
			MovieRatingDTO mr = new MovieRatingDTO();
			mr.setMovie(movieTicketService.getMovie(x.getMovieId()));
			mr.setRating(x);
			mr.setUser(movieTicketService.getUserDetails(x.getUserId()));
			return mr;
		}).collect(Collectors.toList());
		model.put("ratings", ratings);
		return "manageRatings";
	}

	@GetMapping("/myRatings")
	public String myRatings(ModelMap model, HttpServletRequest request) {
		model.clear();
		if (!isUserLoggedIn(request))
			return "login";
		UserDetails user = getUserFromRequest(request);
		List<MovieRating> movieRatings = movieTicketService.getMovieRatings(user.getId());
		List<MovieRatingDTO> ratings = movieRatings.stream().map(x -> {
			MovieRatingDTO mr = new MovieRatingDTO();
			mr.setMovie(movieTicketService.getMovie(x.getMovieId()));
			mr.setRating(x);
			mr.setUser(movieTicketService.getUserDetails(x.getUserId()));
			return mr;
		}).collect(Collectors.toList());
		model.put("ratings", ratings);
		return "myRatings";
	}

	@GetMapping("/deleteRating")
	public String deleteRating(ModelMap model, HttpServletRequest request) {
		model.clear();
		movieTicketService.deleteMovieRating(Arrays.asList(Long.parseLong(request.getParameter("ratingId"))));
		List<MovieRating> movieRatings = movieTicketService.getMovieRatings(null);
		List<MovieRatingDTO> ratings = movieRatings.stream().map(x -> {
			MovieRatingDTO mr = new MovieRatingDTO();
			mr.setMovie(movieTicketService.getMovie(x.getMovieId()));
			mr.setRating(x);
			mr.setUser(movieTicketService.getUserDetails(x.getUserId()));
			return mr;
		}).collect(Collectors.toList());
		model.put("ratings", ratings);
		model.put("success", "Movie Rating Deleted Successfully");
		return "manageRatings";
	}

	@GetMapping("/deleteMovie")
	public String deleteMovie(ModelMap model, HttpServletRequest request) {
		model.clear();
		movieTicketService.deleteMovies(Arrays.asList(Long.parseLong(request.getParameter("movieId"))));
		List<Movie> movies = movieTicketService.searchMovies(null);
		model.put("success", "Movie Deleted Successfully");
		model.put("movies", movies);
		return "manageMovies";
	}

	@GetMapping("/addShow")
	public String addClientpage(ModelMap model, HttpServletRequest request) {
		model.clear();
		return "addShow";
	}

	@GetMapping("/bookTicket")
	public String addProjectpage(ModelMap model, HttpServletRequest request) {
		model.clear();
		return "bookTicket";
	}

	@GetMapping("/register")
	public String registerPage(ModelMap model) {
		model.put("states", States.getStateMap());
		return "register";
	}

	@GetMapping("/index")
	public String homePage(ModelMap model, HttpServletRequest request) {
		model.clear();
		List<Movie> movies = movieTicketService.searchMovies(null);
		model.put("movies", movies);
		return "index";
	}

	@GetMapping("/addMovieRating")
	public String generateReportPage(ModelMap model, HttpServletRequest request) {
		model.clear();
		return "addMovieRating";
	}

	@GetMapping("/login")
	public String loginPage(ModelMap model, HttpServletRequest request) {
		model.clear();
		return "login";
	}

	@GetMapping("/viewTicket")
	public String viewTicket(ModelMap model, HttpServletRequest request) {
		model.clear();
		Ticket ticket = movieTicketService.getMovieTicket(Long.parseLong(request.getParameter("ticketId")));

		MovieTicketDTO movieTicket = generateMovieTicketDTO(ticket);
		model.put("ticket", movieTicket);
		return "viewTicket";
	}

	@GetMapping("/viewMovieDetails")
	public String viewMovieDetails(ModelMap model, HttpServletRequest request) {
		model.clear();
		model.put("movie", movieTicketService.getMovie(Long.parseLong(request.getParameter("movieId"))));
		return "viewMovie";
	}

	@PostMapping(value = "/authenticate")
	public String authenticate(ModelMap model, HttpServletRequest request) {
		model.clear();

		HttpSession session = request.getSession();
		String userId = request.getParameter("username");
		String password = request.getParameter("password");

		UserDetails user = movieTicketService.getUserDetails(userId, password);

		List<Movie> movies = movieTicketService.searchMovies(null);

		if (user != null) {
			user.setLoggedIn(true);
			user.setLastLogInDate(new Date());
			movieTicketService.saveUserDetails(user);
			session.setAttribute("user", user);
			session.setAttribute("roleCode", user.getRoleCode() + "");
			model.put("messageCount", 10);
			model.put("loggedin", true);
			model.put("movies", movies);
			return "index";
		} else {
			model.put("error", "Invalid username password");
			return "login";
		}
	}

	@GetMapping("/myProfile")
	public String getProfile(ModelMap model, HttpServletRequest request) {
		UserDetails user = (UserDetails) request.getSession().getAttribute("user");
		PersonalDetails person = user.getPersonalDetails();
		model.put("userProfile", person);
		return "myProfile";
	}

	@PostMapping("/saveProfile")
	public String updateProfile(@ModelAttribute("userProfile") PersonalDetails userProfile, ModelMap model,
			HttpServletRequest request) {
		movieTicketService.saveProfile(userProfile);
		model.put("userProfile", userProfile);
		model.put("success", "Your profile saved successfully");
		model.put("title", "Success: ");
		return "myProfile";
	}

	@PostMapping(value = "/bookTicket")
	public String bookTicket(ModelMap model, HttpServletRequest request) {
		model.clear();

		UserDetails userDetails = ((UserDetails) request.getSession().getAttribute("user"));
		String showId = request.getParameter("showId");
		String seatNos = request.getParameter("seatNos");

		Ticket ticket = new Ticket();
		ticket.setShowId(Long.parseLong(showId));
		ticket.setPurchaseDate(new Date());
		ticket.setSeatNos(seatNos);
		ticket.setUserId(userDetails.getId());

		ticket = movieTicketService.saveOrUpdateTicket(ticket);

		MovieTicketDTO movieTicket = generateMovieTicketDTO(ticket);

		model.put("ticket", movieTicket);
		model.put("success", "Your Movie Ticket Booked Skuccessfully");
		return "viewTicket";
	}

	private MovieTicketDTO generateMovieTicketDTO(Ticket ticket) {
		MovieTicketDTO movieTicketDTO = new MovieTicketDTO();
		MovieShow show = movieTicketService.getMovieShow(ticket.getShowId());
		movieTicketDTO.setMovie(movieTicketService.getMovie(show.getMovieId()));
		movieTicketDTO.setShow(show);
		movieTicketDTO.setTicket(ticket);
		return movieTicketDTO;
	}

	@PostMapping(value = "/addMovie")
	public String addAttendance(ModelMap model, HttpServletRequest request) throws Exception {
		model.clear();
		if (!isUserAdmin(request)) {
			model.put("error", "user has no right to add movies in system, please login as admin user");
			return "login";
		}

		String name = request.getParameter("name");
		String producerName = request.getParameter("producerName");
		String directorName = request.getParameter("directorName");
		String dateValue = request.getParameter("releaseDate");
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
		String genres = request.getParameter("genres");
		String bookingActive = request.getParameter("bookingActive");
		String budget = request.getParameter("budget");
		String duration = request.getParameter("duration");
		String country = request.getParameter("country");
		String language = request.getParameter("language");
		String starring = request.getParameter("starring");

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile imageFile = null;
		Iterator<String> iterator = multipartRequest.getFileNames();

		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			imageFile = multipartRequest.getFile(key);
		}

		Movie movie = new Movie();
		movie.setBookingActive(Boolean.parseBoolean(bookingActive));
		movie.setBudget(Long.parseLong(budget));
		movie.setName(name);
		movie.setDirectorName(directorName);
		movie.setProducerName(producerName);
		movie.setCountry(country);
		movie.setDuration(Integer.parseInt(duration));
		movie.setGenres(genres);
		movie.setImageUrl(cloudinaryService.uploadImage(imageFile, "staticresource"));
		movie.setLanguage(language);
		movie.setReleaseDate(date);
		movie.setStarring(starring);

		movieTicketService.saveOrUpdateMovie(movie);
		List<Movie> movies = movieTicketService.searchMovies(null);
		model.put("movies", movies);
		model.put("success", "New Movie Added Successfully");
		return "movies";
	}

	@PostMapping(value = "/addShow")
	public String addTask(ModelMap model, HttpServletRequest request) throws Exception {
		model.clear();

		String movieId = request.getParameter("movieId");
		String dateValue = request.getParameter("showDateTime");
		Date date = new SimpleDateFormat("yyyy-MM-dd h:mm tt").parse(dateValue);
		String ticketPrice = request.getParameter("ticketPrice");

		MovieShow movieShow = new MovieShow();
		movieShow.setMovieId(Long.parseLong(movieId));
		movieShow.setShowDateTime(date);
		movieShow.setTicketPrice(Integer.parseInt(ticketPrice));

		movieShow = movieTicketService.saveMovieShow(movieShow);
		List<MovieShow> movieShows = movieTicketService.getMovieShows(movieShow.getMovieId());
		model.put("movieShows", movieShows);
		model.put("success", "your record submitted successfully");
		return "viewMovie";
	}

	@PostMapping(value = "/addClient")
	public String addClient(ModelMap model, HttpServletRequest request) {
		model.clear();

		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String description = request.getParameter("description");

		Movie client = new Movie();
		client.setCode(code);
		client.setDescription(description);
		client.setName(name);

		timesheetService.saveClient(client);
		List<Movie> clients = timesheetService.getAllClients();
		model.put("clients", clients);
		model.put("success", "your record submitted successfully");
		return "viewClient";
	}

	@PostMapping(value = "/generateReport")
	public String generateReport(ModelMap model, HttpServletRequest request) {
		model.clear();
		String emailIds = request.getParameter("emailIds");

		// list emails

		model.put("success", "Report is sent to following Email Ids: " + emailIds);
		return "message";
	}

	@PostMapping(value = "/addMovieRating")
	public String addProject(ModelMap model, HttpServletRequest request) {
		model.clear();

		UserDetails user = getUserFromRequest(request);
		if (user == null) {
			model.put("error", "Your are logged out, Please login");
			return "login";
		}

		String movieId = request.getParameter("movieId");
		String rating = request.getParameter("rating");
		String reviewText = request.getParameter("reviewText");

		MovieRating movieRating = new MovieRating();
		movieRating.setMovieId(Long.parseLong(movieId));
		movieRating.setRating(Integer.parseInt(rating));
		movieRating.setReviewText(reviewText);
		movieRating.setUserId(user.getId());

		movieRating = movieTicketService.saveOrUpdateMovieRating(movieRating);

		List<MovieRating> movieRatings = movieTicketService.getMovieRatings(user.getId());
		model.put("movieRatings", movieRatings);
		model.put("success", "Your Movie Rating Submitted Successfully");
		return "myMovieRatings";
	}

	@PostMapping("/signup")
	public String addUser(ModelMap model, HttpServletRequest request) throws Exception {
		model.clear();

		String name = request.getParameter("fullName");
		String mobileNumber = request.getParameter("mobileNo");
		String addressText = request.getParameter("address");
		String pincode = request.getParameter("pincode");
		String birthDate = request.getParameter("birthDate");
		Date bdate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		String confirmPassword = request.getParameter("cnfPassword");

		UserDetails userDetail = new UserDetails();
		PersonalDetails person = new PersonalDetails();
		Address address = new Address();

		address.setAddressText(addressText);
		address.setCity(city);
		address.setCountry("India");
		address.setPincode(Integer.parseInt(pincode));
		address.setState(state);

		person.setName(name);
		person.setBirthDate(bdate);
		person.setAddress(address);
		person.setEmailId(emailId);
		person.setMobNumber(Long.parseLong(mobileNumber));
		userDetail.setEmailId(emailId);
		if (password.equals(confirmPassword))
			userDetail.setPassword(password);
		userDetail.setPersonalDetails(person);
		userDetail.setRoleCode(UserType.END_USER.getCode());

		EmailData data = new EmailData();
		String a[] = { emailId };
		data.setTo(a);
		data.setSubject("Email Verification OTP From Agritech");
		data.setTemplate("Your agritech account OTP is" + Utility.generateOTP());
		emailService.sendEmail(data);

		movieTicketService.saveUserDetails(userDetail);
		return "validateOtp";
	}

	@GetMapping("/validateOtp")
	public String otpValidation(ModelMap model, HttpServletRequest request) {
		model.clear();
		String otp = request.getParameter("otp");

		if (!otp.equals("12345")) {
			model.put("error", "Invalid otp!");
			return "validateOtp";
		}
		model.put("success", "OTP verified successfully");
		return "login";
	}

	@GetMapping("/myTimesheet")
	public String getMytimesheets(ModelMap model, HttpServletRequest request) {
		model.clear();
		UserDetails UserDetails = ((UserDetails) request.getSession().getAttribute("user"));
		if (UserDetails != null) {
			List<Theatres> empTimesheets = timesheetService.getEmployeeTimesheetofUser(UserDetails.getId());
			List<MovieRatingDTO> ehours = new ArrayList<>();
			for (Theatres employeeTimesheet : empTimesheets) {
				MovieRatingDTO e = new MovieRatingDTO();
				e.setClient(timesheetService.getClient(employeeTimesheet.getClientId()).getName());
				e.setDate(
						DateSupport.convertDateToString(employeeTimesheet.getDate(), DateSupport.DEFAULT_DATE_FORMAT));
				e.setDescription(employeeTimesheet.getDescription());
				e.setDuration(employeeTimesheet.getHours());
				e.setIssueTrackingId(employeeTimesheet.getIssueTrackingId());
				e.setProject(timesheetService.getProject(employeeTimesheet.getProjectId()).getName());
				e.setTask(timesheetService.getTask(employeeTimesheet.getTaskId()).getName());
				ehours.add(e);
			}

			model.put("logHours", ehours);
			return "myTimesheet";
		} else {
			model.put("error", "user not logged in!");
			return "login";
		}

	}

	@GetMapping("/myAttendance")
	public String myAttendance(ModelMap model, HttpServletRequest request) {
		model.clear();
		UserDetails UserDetails = ((UserDetails) request.getSession().getAttribute("user"));
		if (UserDetails != null) {
			List<Ticket> attendances = timesheetService.getEmployeeAttendanceofUser(UserDetails.getId());
			List<MovieTicketDTO> empAttendances = new ArrayList<>();

			for (Ticket employeeAttendance : attendances) {

				MovieTicketDTO empat = new MovieTicketDTO();
				empat.setAttendance(employeeAttendance.getAttendance());
				empat.setDate(
						DateSupport.convertDateToString(employeeAttendance.getDate(), DateSupport.DEFAULT_DATE_FORMAT));
				empat.setLoggedIn(employeeAttendance.getPunchInTime());
				empat.setLoggedOut(employeeAttendance.getPunchOutTime());
				empat.setRemark(employeeAttendance.getRemark());
				empAttendances.add(empat);
			}
			model.put("attendances", empAttendances);
			return "myAttendance";
		} else {
			model.put("error", "user not logged in!");
			return "login";
		}

	}

	private boolean isUserLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null;
	}

	private boolean isUserAdmin(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null
				&& ((UserDetails) request.getSession().getAttribute("user")).getRoleCode() == UserType.ADMIN.getCode();
	}

	private UserDetails getUserFromRequest(HttpServletRequest request) {
		if (isUserLoggedIn(request))
			return ((UserDetails) request.getSession().getAttribute("user"));
		else
			return null;
	}

}
