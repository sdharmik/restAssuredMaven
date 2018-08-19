package com.test.restAssured.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AssetDetails {

	private String serviceTag;
	private String localChannel;
	private String companyNumber;
	private String productLOB;
	private String shippedDate;
//	private String retailInfo;
	
	public String getServiceTag() {
		return serviceTag;
	}
	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}
	public String getLocalChannel() {
		return localChannel;
	}
	public void setLocalChannel(String localChannel) {
		this.localChannel = localChannel;
	}
	public String getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getProductLOB() {
		return productLOB;
	}
	public void setProductLOB(String productLOB) {
		this.productLOB = productLOB;
	}
	public String getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(String shippedDate) {
		this.shippedDate = shippedDate;
	}
/*	public String getRetailInfo() {
		return retailInfo;
	}
	public void setRetailInfo(String retailInfo) {
		this.retailInfo = retailInfo;
	}
*/	
	
}
