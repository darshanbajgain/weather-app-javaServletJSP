<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather App - Weather Details</title>
<link rel="stylesheet" href="jsp-style.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

</head>
<body>

	<div class="container">
		<h1>Weather Details</h1>

		<div class="weather-image-container">
			<img id="weather-icon" src="images/img1.png" alt="Weather Image">
			<div class="temp-city">
				<h2>
					<i class="fas fa-city"></i> ${city}
				</h2>
				<h2>
					<i class="fas fa-thermometer-half"></i> ${temperature}&deg;C
				</h2>
			</div>
		</div>

		<div class="weather-info">
			<p>
				<i class="fas fa-calendar-alt"></i> Date: ${date}
			</p>
			<p>
				<i class="fas fa-clock"></i> CurrentTime: ${currentTime}
			</p>
			<p>
				<i class="fas fa-cloud-sun"></i> Condition: ${weatherCondition}
			</p>
			<p>
				<i class="fas fa-eye"></i> Visibility: ${visibility}km
			</p>
			<p>
				<i class="fas fa-wind"></i> WindSpeed: ${windSpeed}km/hr
			</p>
			<p>
				<i class="fas fa-cloud"></i> CloudCover: ${cloudCover}%
			</p>
		</div>

		<div class="container">
			<h2>Find Other Places Weather</h2>
			<form id="weatherForm" action="MyServlet" method="post">
				<input type="text" id="city" name="city"
					placeholder="E.g., Kathmandu, New York, London">
				<button type="submit">Get Weather</button>
				<p id="errorMsg"
					style="color: red; padding: 6px 6px; display: none;">Please
					enter the name of the place.</p>

			</form>
		</div>
	</div>

	<script src="script.js"></script>
</body>

<footer>
	<div class="footer-container">
		<p>Developed by Darshan Bajgain @ 2024 | JAVA Servlet JSP Dynamic
			Web Project</p>
	</div>
</footer>
</html>
