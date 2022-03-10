package todoApp.dao;

import java.util.List;

import todoApp.model.Todo;

// 인터페이스. 메서드와 매개변수, 리턴형만 지정해둔 상태. 구현은 안되어있으며 dao로 이 인터페이스를 구현해서 기능을 완성해야함. 
public interface TodoDao {
	
	void insertTodo(Todo todo);			// Create 입력 => 할 일을 DB에 입력
	Todo selectTodo(long todoId);		// Read => id로 할일을 검색 
	

	List<Todo> selectTodoByUsername(String username);	// username으로 할일을 검색
	
	List<Todo> selectAllTodos();		// Read => 모든 할일을 검색 (DB에서 <Todo>의 list타입으로 결과를 리턴받음) 
	
	boolean deleteTodo(long todoId);	// Delete => id로 할일을 삭제 (제대로 삭제되었는지 true, false로 리턴)
	boolean deleteTodo(String username);// Delete => 할일을 삭제 (username으로)
	
	boolean updateTodo(Todo todo);		// Update => 할일을 업데이트 (제대로 업데이트되었는지 true, false로 리턴) 
}
