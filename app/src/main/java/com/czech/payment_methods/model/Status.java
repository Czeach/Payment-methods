package com.czech.payment_methods.model;

import com.google.gson.annotations.SerializedName;

public class Status{

	@SerializedName("reason")
	private String reason;

	@SerializedName("code")
	private String code;

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}
}