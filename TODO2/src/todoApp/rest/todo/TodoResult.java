package todoApp.rest.todo;

public class TodoResult {
	private boolean isSuccess;

	// 특별히 받아올 정보가 없으므로 간단하게 만듦.
	
	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Override
	public String toString() {
		return "TodoResult [isSuccess=" + isSuccess + "]";
	}
}

