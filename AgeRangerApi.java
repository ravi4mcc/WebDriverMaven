package com.ageranger.api.test;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

public class AgeRangerApi {

	/* 
	 * Test case to verify the HTTP status code for Success
	 */
	@Test
	public void testStatusCode() {
		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().statusCode(200);
		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().statusCode(200);
	}

		
	/*
	 *  Test case to verify the single content
	 */
	@Test
	public void testsinglecontent() {

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("Id[0]", equalTo(780));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("FirstName[0]",
				equalTo("John"));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("LastName[0]",
				equalTo("Smith"));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("Age[0]", equalTo(2));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("AgeGroup[0]",
				equalTo("Child"));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("Id[0]",
				equalTo(1));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("MinAge[0]",
				equalTo(0));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("MaxAge[0]",
				equalTo(2));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("Description[0]",
				equalTo("Toddler"));

	}

	/*
	 *  Test case to verify the multiple content
	 */
	@Test
	public void testmultiplecontent() {

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("Id",
				hasItems(780, 781, 782));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("FirstName",
				hasItems("John", "test"));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("LastName",
				hasItems("Smith", "test"));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("Age",
				hasItems(2, 17, 98));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("Id",
				hasItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("MinAge",
				hasItems(0, 2, 14, 20, 25, 30, 50, 70, 99, 110, 199, 4999));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("MaxAge",
				hasItems(2, 14, 20, 25, 30, 50, 70, 99, 110, 199, 4999));

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().body("Description",
				hasItems("Toddler", "Child", "Teenager"));

	}

	/*
	 *  Test case to search by First name and Last name as parameters
	 */
	@Test
	public void searchByFirstname() {
		given().param("FirstName", "John").param("LastName", "Smith").when()
				.get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().statusCode(200).log()
				.all();
	}

	/*
	 *  Test case for to search by Id and Description as parameters
	 */
	@Test
	public void searchById() {
		given().param("Id", "1").param("Description", "Toddler").when()
				.get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups").then().statusCode(200).log()
				.all();
	}

	/*
	 *  Test case to get the list of people details from DB
	 */
	@Test
	public void getAllPersons() {
		Response res = given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople");
		String json = res.asString();
		System.out.println(json);

	}

	/*
	 * Test case to get all age group details from DB
	 */
	@Test
	public void getAllAgeGroups() {
		Response res = given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroups");
		String json = res.asString();
		System.out.println(json);
	}

	/*
	 *  Test case to add a new person in DB using POST
	 */
	@Test
	public void addNewPerson() {
		given().param("FirstName", "Swathi").param("LastName", "Budde").param("Age", 38).param("AgeGroup", "Very Adult")
				.when().post("http://ageranger.azurewebsites.net/api/AgeRanger/AddPerson").then().statusCode(204).log()
				.all();
	}

	/*
	 *  Test case to Update person details in DB using PUT
	 */
	/*@Test
	public void updatePersonDetails() {

	
		
		given().pathParam("Id", 878).param("LastName", "Budde").when()
		.put("http://ageranger.azurewebsites.net/api/AgeRanger/UpdatePerson/{Id}").then().statusCode(200)
				.log().all();
	}*/

	/*
	 * Test case to delete a person details in DB using DELETE
	 */
	@Test
	public void deletePersonDetails() {
		given().pathParam("Id", 886).when().delete("http://ageranger.azurewebsites.net/api/AgeRanger/DeletePerson/{Id}")
				.then().statusCode(204);
	}
	
	
	//Negative test cases
	
	/* 
	 * Test case to verify the invalid HTTP status code
	 */
	@Test
	public void invalidTestStatusCode() {
		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeopl").then().statusCode(404);
		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllAgeGroup").then().statusCode(404);
	}

	
	/*
	 *  Test case to verify the single content
	 */
	@Test
	public void invalidTestSingleContent() {

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("Id[0]", not(equalTo(777)));

	}
	
	
	/*
	 *  Test case to verify the multiple content
	 */
	@Test
	public void invalidTestMultipleContent() {

		given().get("http://ageranger.azurewebsites.net/api/AgeRanger/GetAllPeople").then().body("Id",not(hasItems(78, 70, 72)));
	}
	
	
}
