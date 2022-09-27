package com.spotify.oauth2.api.application;

//import static io.restassured.RestAssured.given;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Route;

import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.ConfigLoader;

public class PlaylistApi {
	
	@Step
	public static Response post(Playlist requestPlaylist) {
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().getUserId()+Route.PLAYLIST, TokenManager.getToken(), requestPlaylist);
				
//1.		return given(SpecBuilder.getRequestSpec())
//				.body(requestPlaylist)
//				.header("Authorization", "Bearer "+access_token)
//				.when().post("/users/31l2v25arsuly27gh2gzulz54354/playlists")
//				.then().spec(SpecBuilder.getResponseSpec())
//				.extract().response();
		
	//below one is a overload method of above one method and used to just get the wrong token	
	}
	@Step
	public static Response post(String token,Playlist requestPlaylist) {
		return RestResource
				.post(Route.USERS+"/"+ConfigLoader.getInstance().getUserId()+Route.PLAYLIST,token, requestPlaylist);

//		return given(SpecBuilder.getRequestSpec())
//				.body(requestPlaylist)
//				.header("Authorization","Bearer "+token)
//				.when().post("/users/31l2v25arsuly27gh2gzulz54354/playlists")
//				.then().spec(SpecBuilder.getResponseSpec())
//				.extract().response();
		
	}
	public static Response get(String playlistId) {
		return RestResource
				.get(Route.PLAYLIST+playlistId,TokenManager.getToken());
		
//		return given(SpecBuilder.getRequestSpec())
//				.header("Authorization", "Bearer "+access_token)
//				   .when().get("playlists/"+playlistId)
//					.then().spec(SpecBuilder.getResponseSpec())
//					.extract().response();
//					
	}
	public static Response update(String playlistId,Playlist requestPlaylist) {
		return RestResource.update(Route.PLAYLIST+playlistId, TokenManager.getToken(), requestPlaylist);
		
//		return given(SpecBuilder.getRequestSpec())
//				.body(requestPlaylist)
//				.header("Authorization", "Bearer "+access_token)
//				.when().post("/playlists/"+playlistId)
//				.then().spec(SpecBuilder.getResponseSpec())
//				.extract().response();
	}
	
}
