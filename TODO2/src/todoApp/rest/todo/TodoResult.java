package todoApp.rest.todo;

public class TodoResult {
	private boolean isSucceess;

	// 특별히 받아올 정보가 없으므로 간단하게 만듦.
	
	public boolean isSucceess() {
		return isSucceess;
	}

	public void setSucceess(boolean isSucceess) {
		this.isSucceess = isSucceess;
	}

	@Override
	public String toString() {
		return "TodoResult [isSucceess=" + isSucceess + "]";
	}
}
