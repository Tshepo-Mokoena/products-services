package com.tshepo.firebase.upload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Upload {
	
	@JsonProperty
	private String fileName;
	
	@JsonProperty
	private String url;

}
