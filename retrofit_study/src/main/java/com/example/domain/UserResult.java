package com.example.domain;

public class UserResult {
	private boolean isSuccess;

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "UserResult [isSuccess=" + isSuccess + "]";
	}
	
}
