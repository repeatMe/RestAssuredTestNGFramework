package com.spotify.oauth2.api;

import java.time.Instant;
import java.util.HashMap;

//import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.ConfigLoader;

//import static io.restassured.RestAssured.*;

public class TokenManager {
	private static String access_token;
	private static Instant expiry_time;
	
	public synchronized static String getToken(){
		try {
			if(access_token==null || Instant.now().isAfter(expiry_time)) {
				System.out.println("Renewing token........");
				Response response=renewToken();
				access_token=response.path("access_token");
				int expiryDurationInSeconds=response.path("expires_in");
				expiry_time=Instant.now().plusSeconds(expiryDurationInSeconds-300);
			}
			else {
				System.out.println("Token is good to use");
			}
		}
		catch(Exception e) {
			throw new RuntimeException("abort failed!!!!!");
		}
	return access_token;
	}

	private static Response renewToken() {
		HashMap<String,String>formParams=new HashMap<>();
		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret",ConfigLoader.getInstance().getClientSecret() );
		formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefresh_token());
	
//	Response response=	given().baseUri("https://accounts.spotify.com")
//		.contentType(ContentType.URLENC)
//		.formParams(formParams).log().all()
//        .when().post("/api/token")
//        .then().spec(SpecBuilder.getResponseSpec())
//        .extract().response();
		Response response=RestResource.postAccount(formParams);
		if(response.statusCode() !=200) {
			throw new RuntimeException("ABORT!!!! renew token failed");
		}
		return response;
	}
}
