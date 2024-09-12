package phase3Project1RestAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;



public class EmployeeAPITests {
	public void waitForApiRateLimit(int waitTimeInMillis) {
		try {
			System.out.println("Waiting for "+ waitTimeInMillis/1000 +"second before making the request");
			Thread.sleep(waitTimeInMillis);
			
		}catch(InterruptedException e) {
			e.printStackTrace();
			
		}
		
	}
	
	@Test(priority=1)
	public void getAllEmployees()  {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
//		waitForApiRateLimit(10000);
		
		given()
	                
			
		.when()
			.get("/employees")
		.then()
			.statusCode(200)
			.body("data", notNullValue())
			.log().body();
		
		
	}
	
	@Test(priority=2)
	public void getAllEmployeeDataById()  {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";	
		int employeeId=1;
		
//		waitForApiRateLimit(5000);
		
		given()
		 
		
		.when()
			.get("/employee/"+employeeId)
		
		.then()
			.statusCode(200)
			.log().body();
	
	}
	
	@Test(priority=3)
	public void updateEmployeeData() {
		
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		
		Map<String,Object> updatedData = new HashMap<>();
		updatedData.put("employee_name", "Munna");
		updatedData.put("employee_salary", 45000);
		updatedData.put("employee_age", 35);
		updatedData.put("profile_image", "");
		
		given()
			.header("Content-Type","application/json")
			.body(updatedData)
	
		.when()
			.put("update/9")
			
		
		.then()
			.statusCode(200)
			.body("data.employee_name",equalTo("Munna"))
			.body("data.employee_salary", equalTo(45000))
			.body("data.employee_age", equalTo(35))
			.log().body();
		
	}
	
	@Test(priority=4)
	public void deleteEmployeeRecord() {
		RestAssured.baseURI="https://dummy.restapiexample.com/api/v1";
		
		given()
		
		.when()
			.delete("/delete/8")
		
		.then()
			.statusCode(200)
			.log().body();
			
	}
}
