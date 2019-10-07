package openweather.sydney;


import org.junit.BeforeClass;
import org.junit.Test;
import com.jayway.restassured.RestAssured;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static com.jayway.restassured.RestAssured.*;

public class Sydneyweather {

	@BeforeClass
	public static void init() {
		RestAssured.baseURI="https://api.openweathermap.org";
		RestAssured.basePath="/data/2.5";
	}
	
	
	@Test
	public void getSydneyTemp2() {	
		
			int i;		
			List<Float>  temp_for_16_days = new ArrayList<Float>();
			
					temp_for_16_days = given()
					.when()
					.get("/forecast?q=Sydney,AU&APPID=2bde50bdcc809a6230f17e9ba8e7f951&units=metric&cnt=16")
					.then().extract().path("list.main.temp");	
					
					System.out.println("Sydney Temperaure for next 16 days :" + temp_for_16_days + "\n");					
					SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
			        						
					for ( i=0;i<16;i++)
					{
						if (temp_for_16_days.get(i) > 10 ) {								
							Calendar cal = Calendar.getInstance();
						    cal.add(Calendar.DATE, i); // this will add number of days to current date 					                                
						    Date date = cal.getTime();  						    							    	
						    		String strDate = simpleDateformat.format(date);
						    		if (strDate.contains("Thu")) {
						    			System.out.println("Sydney Temperature on : " + date + " is: " + temp_for_16_days.get(i) + "\n");

						    		}
						    								
					}
		}		
	}
}
