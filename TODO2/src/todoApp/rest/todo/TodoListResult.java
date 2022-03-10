package todoApp.rest.todo;

import java.util.List;

import todoApp.model.Todo;

public class TodoListResult {

	private boolean hasResult;
	private int totalCount;
	private List<Todo> todoList;
	public boolean isHasResult() {
		return hasResult;
	}
	public void setHasResult(boolean hasResult) {
		this.hasResult = hasResult;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Todo> getTodoList() {
		return todoList;
	}
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}
	@Override
	public String toString() {
		return "TodoListResult [hasResult=" + hasResult + ", totalCount=" + totalCount + ", todoList=" + todoList + "]";
	}

	
}
