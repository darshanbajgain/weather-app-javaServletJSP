package MyPackage;

import jakarta.servlet.ServletException;
import java.util.Date;
import java.text.SimpleDateFormat;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Open Weather API setup
		String myApiKey = "use your API key here";
		//getting the city name from the form input
		String city = request.getParameter("city");
		//Create the URL of the 
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" +city+ "&appid="+ myApiKey;
		
		try {
			//API Integration
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			
			//reading data from network;
			InputStream inpStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inpStream);
			
			//want to store in string
			StringBuilder responseContent = new StringBuilder();
			
			//create scanner object  to take input from reader.
			Scanner scanner = new Scanner(reader);
			while(scanner.hasNext()) {
				responseContent.append(scanner.nextLine());
			}
			scanner.close();
			
			//now the response should be made in to json format
			// typecasting 
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
			System.out.println(jsonObject);
			
			
			//Temperature
			double tempInKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
			int tempInCelsius = (int)(tempInKelvin - 273.15);
			
			//Humidty
			int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();
			
			//wind speed
			double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
			//visibitity
			int visibilityInMeter = jsonObject.get("visibility").getAsInt();
			int visibility = visibilityInMeter / 1000;
			//weather condition
	        String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
	        //cloud condition
	        int cloudCover = jsonObject.getAsJsonObject("clouds").get("all").getAsInt();
	        
	        
	        // Date & Time
	     	//long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
	     	//String date = new Date(dateTimestamp).toString();
	     			
	        
	     	// Date & Time
	        long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
	        SimpleDateFormat sdfDate = new SimpleDateFormat("EEE MMM dd yyyy");
	        String date = sdfDate.format(new Date(dateTimestamp));

	        // Fetching the current time
	        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	        String formattedTime = sdfTime.format(new Date());


	        // Set the data as request attributes (for sending to the jsp page)
	        request.setAttribute("date", date);
	        request.setAttribute("city", city);
	        request.setAttribute("visibility",visibility);
	        request.setAttribute("temperature", tempInCelsius);
	        request.setAttribute("weatherCondition", weatherCondition); 
	        request.setAttribute("humidity", humidity);    
	        request.setAttribute("windSpeed", windSpeed);
	        request.setAttribute("cloudCover", cloudCover);
	        request.setAttribute("currentTime", formattedTime);
	        request.setAttribute("weatherData", responseContent.toString());
	        
	        connection.disconnect();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
        
     // Forward the request to the weather.jsp page for rendering
        request.getRequestDispatcher("index.jsp").forward(request, response);

        
	}

}
