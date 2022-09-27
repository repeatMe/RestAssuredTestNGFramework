package com.spotify.oauth2.tests;

//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.application.PlaylistApi;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.pojo.Error;
import static utils.FakerUtils.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
//import io.restassured.RestAssured;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.builder.ResponseSpecBuilder;
//import io.restassured.filter.log.LogDetail;
//import io.restassured.http.ContentType;
//import io.restassured.parsing.Parser;
import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
import utils.DataLoader;


@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlayListTests extends BaseTest{
//RequestSpecification requestSpecification;
//ResponseSpecification responseSpecification;
//String access_token="BQDct4axBaqWUzbGebGVVn_Bz-6dyN-5eiCJPzVSoo4rosSpQpr2vG01gD1N4axUIGKkYL3zeUYliscFDpBohI_6NLiCdpCH9omNpFAlPdnZMc6s2JYZLruRSVQwaEo24h1pNlp_RCRDrQR7wXKJETm_UmOlg11Hr3DpyFsFnGqDX3y-MVy4tDUIubCee5jUT00JHxOuAzUWdjGGudZFIq5LAI7MChWrcUQFX2yi-4i7k2ma-YipeoMmJvhfXgRkzc2k5QwSRLGApd-J";

//@BeforeClass
//public void beforeClass() 
//{
//	RequestSpecBuilder requestSpecBuilder= new RequestSpecBuilder()
//			.setBaseUri("https://api.spotify.com")
//			.setBasePath("/v1")
//			.addHeader("Authorization", "Bearer "+access_token)
//			.setContentType(ContentType.JSON)
//			.log(LogDetail.ALL);
//	
//	requestSpecification =requestSpecBuilder.build();
//	
//	ResponseSpecBuilder responseSpecBuilder=new ResponseSpecBuilder()
//			
//		    //.expectContentType(ContentType.JSON)
//		    .log(LogDetail.ALL);
//	
//	responseSpecification=responseSpecBuilder.build();
//			
//			
//}
	@Step
	public Playlist playlistBuilder(String name,String description, boolean _public ){
	
	/*	//lombok without builder
		Playlist playlist=new Playlist();
		playlist.setName(name);
		playlist.setDescription(description);
		playlist.set_public(_public);
		return playlist;
		*/
		
		//lombok with @builder pattern
		return Playlist.builder().name(name).description(description)._public(false).build();
		
		
		
//	return new Playlist()
//			.setName(name)
//			.setDescription(description)
//			.setPublic(_public);	
	}
	@Step
	public void assertPlaylistEqual(Playlist responsePlaylist,Playlist requestPlaylist) {
		assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlaylist.get_public(),equalTo(requestPlaylist.get_public()));
	}
	@Step
	public void assertStatusCode(int actualStatusCode, StatusCode statusCode) {
		assertThat(actualStatusCode,equalTo(statusCode.code));
	}
	@Step
	//public void assertError(Error responseError,int expectedStatusCode,String expectedMsg) {
	public void assertError(Error responseError,StatusCode statusCode) {
			assertThat(responseError.getError().getStatus(),equalTo(statusCode.code));
		 assertThat(responseError.getError().getMessage(),equalTo(statusCode.msg));
	}

@Story("Create a playlist story")	
@Link("https://testng.com")
@Link(name="allure")
@TmsLink("1234567")
@Issue("12345")	
@Description("this is real description")	
@Test(description="hi i can create a playlist for u -this is display name")
public void Should_Be_Able_To_Create_A_PlayList() {
	
	/*	1.String payload="{\r\n"
			+ "  \"name\": \"New Playlist\",\r\n"
			+ "  \"description\": \"New playlist description\",\r\n"
			+ "  \"public\": false\r\n"
			+ "}";
*/
/*2.	Playlist requestPlaylist=new Playlist();
	requestPlaylist.setName("my new songs");
	requestPlaylist.setDescription("this one is brand new playlist");
	requestPlaylist.setPublic(false);
*/
	
/*3.	Playlist responsePlaylist=given(SpecBuilder.getRequestSpec())
	.body(requestPlaylist)
	.when().post("/users/31l2v25arsuly27gh2gzulz54354/playlists")
	.then().spec(SpecBuilder.getResponseSpec()).assertThat().statusCode(201)
	.extract().response().as(Playlist.class);
	
*/	
	/*4	Playlist requestPlaylist=new Playlist().setName("my new songs")
	.setDescription("this one is brand new playlist").setPublic(false);
    */	
	
	Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(),false);
    Response response=PlaylistApi.post(requestPlaylist);
	//assertStatusCode(response.statusCode(),201);
   // assertStatusCode(response.statusCode(),StatusCode.CODE_201.code);
    assertStatusCode(response.statusCode(),StatusCode.CODE_201);
	assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);
	
	//assertThat(response.statusCode(),equalTo(201));
	//Playlist responsePlaylist=response.as(Playlist.class);
	//assertPlaylistEqual(requestPlaylist,responsePlaylist);
	
	
	
//	assertThat(responsePlaylist.getName(),equalTo(requestPlaylist.getName()));
//	assertThat(responsePlaylist.getDescription(),equalTo(requestPlaylist.getDescription()));
//	assertThat(responsePlaylist.getPublic(),equalTo(requestPlaylist.getPublic()));

	
	/*	.body("name", equalTo("New Playlist"),
			"description",equalTo("New playlist description"),
			"public",equalTo(false));
*/
}
    @Story("Create a playlist story")	
	@Test
	public void Should_Be_Able_To_get_A_PlayList() {
		
	
//		Playlist responsePlaylist =	given(SpecBuilder.getRequestSpec())
//	   .when().get("playlists/7JLPIVkq2WCxFGHMf8zXBo")
//		.then().spec(SpecBuilder.getResponseSpec()).assertThat().statusCode(200)
//		.extract().response()
//		.as(Playlist.class);
		
		Playlist requestPlaylist = playlistBuilder("Updated Playlist tera bhai","Updated playlist description",false);
		Response response=PlaylistApi.get(DataLoader.getInstance().getGetPlaylistId());
		assertStatusCode(response.statusCode(),StatusCode.CODE_200);
		assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);
		
//		Playlist responsePlaylist=response.as(Playlist.class);
//		assertThat(responsePlaylist.getName(),equalTo("Updated Playlist tera bhai"));
//		assertThat(responsePlaylist.getDescription(),equalTo("Updated playlist description"));
//		assertThat(responsePlaylist.getPublic(),equalTo(false));
	
}
    @Story("Create a playlist story")	
	@Test
	public void Should_Be_Able_To_update_A_PlayList() {
		
//		Playlist requestPlaylist=new Playlist();
//		requestPlaylist.setName("Updated Playlist tera bhai");
//		requestPlaylist.setDescription("Updated playlist description");
//		requestPlaylist.setPublic(false);
		
    	Playlist requestPlaylist = playlistBuilder(generateName(),generateDescription(),false);
		//Playlist requestPlaylist = playlistBuilder("New Playlist","New playlist description",false);
		Response response=PlaylistApi.update( DataLoader.getInstance().getUpdatePlaylistId(),requestPlaylist);
		assertStatusCode(response.statusCode(),StatusCode.CODE_200);
		assertPlaylistEqual(response.as(Playlist.class),requestPlaylist);
		
	//	assertThat(response.statusCode(),equalTo(200));

		
//		given(SpecBuilder.getRequestSpec()).body(requestPlaylist)
//	    .when().put("/playlists/7JLPIVkq2WCxFGHMf8zXBo")
//		.then().spec(SpecBuilder.getResponseSpec()).assertThat().statusCode(200);
		
		
	
}
	@Test
	public void Should_Not_Be_Able_To_Create_A_PlayList_without_Name() {
		

// com.spotify.oauth2.pojo.Error error=	given(SpecBuilder.getRequestSpec() )
//		.body(requestPlaylist)
//		.when().post("/users/31l2v25arsuly27gh2gzulz54354/playlists")
//		.then().spec(SpecBuilder.getResponseSpec()).assertThat().statusCode(400)
//		.extract().response().as(com.spotify.oauth2.pojo.Error.class);
	
		
		Playlist requestPlaylist= playlistBuilder("",generateDescription(),false);
		Response response=PlaylistApi.post(requestPlaylist);
		//Error responseError=response.as(Error.class);
		//assertError(response.as(Error.class), 400,"Missing required field: name");
		assertError(response.as(Error.class), StatusCode.CODE_400);
		
	//assertStatusCode(response.statusCode(),400);
	//.spotify.oauth2.pojo.Error responseError=response.as(com.spotify.oauth2.pojo.Error.class);
		}
	@Test
	public void Should_Not_Be_Able_To_Create_A_PlayList_with_ExpiredToken() {
		Playlist requestPlaylist= playlistBuilder(generateName(),generateDescription(),false);
		String invalid_token="asdfrgthyj";
		
		Response response=PlaylistApi.post(invalid_token,requestPlaylist);
		Error responseError=response.as(Error.class);
		assertError(responseError, StatusCode.CODE_401);
		
		
//		assertThat(response.statusCode(),equalTo(401));
//		com.spotify.oauth2.pojo.Error error=response.as(com.spotify.oauth2.pojo.Error.class);
//		assertThat(error.getError().getStatus(),equalTo(401));
//		assertThat(error.getError().getMessage(),equalTo("Invalid access token"));
//		
		
/*		given().baseUri("https://api.spotify.com")
		.basePath("/v1")
		.header("Authorization", "Bearer "+"12030")
		.contentType(ContentType.JSON)
		.log().all()
		.body(payload)
		.when().post("/users/31l2v25arsuly27gh2gzulz54354/playlists")
		.then().spec(responseSpecification).assertThat().statusCode(400)
		.body("error.status", equalTo("401"),
				                             "error.message",equalTo("Invalid access token"));
*/	
}
}