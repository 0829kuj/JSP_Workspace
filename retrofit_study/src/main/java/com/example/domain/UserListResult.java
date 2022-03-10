package com.example.domain;

import java.util.List;


public class UserListResult {

	private List<User> userList;
	private boolean hasResult;
	private int totalCount;
	
	
	/**
	 * @return the userList
	 */
	public List<User> getUserList() {
		return userList;
	}
	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	/**
	 * @return the hasResult
	 */
	public boolean isHasResult() {
		return hasResult;
	}
	/**
	 * @param hasResult the hasResult to set
	 */
	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	@Override
	public String toString() {
		return "UserListResult [userList=" + userList + ", hasResult=" + hasResult + ", totalCount=" + totalCount + "]";
	}
	
	
}
