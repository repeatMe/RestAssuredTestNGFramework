
package com.spotify.oauth2.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;



public class ExternalUrls {

    @JsonProperty("spotify")
    private String spotify;

    @JsonProperty("spotify")
    public String getSpotify() {
        return spotify;
    }

    @JsonProperty("spotify")
    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }

}
