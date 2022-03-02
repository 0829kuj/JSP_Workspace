package test;

import java.time.LocalDate;
import java.util.List;

import todoApp.dao.TodoDaoImpl;
import todoApp.model.Todo;

public class Test {
	public static void main(String[] args) {
		// 중간점검용 Test클래스
		TodoDaoImpl dao = new TodoDaoImpl();
		Todo todo = new Todo(1L, "할일1", "hong", "처음 할일", LocalDate.parse("2022-01-20"), false);
		Todo todo2 = new Todo(2L, "할일2", "son", "두번째 할일", LocalDate.parse("2022-01-21"), false);
		Todo todo3 = new Todo(3L, "할일3", "kim", "세번째 할일", LocalDate.parse("2022-01-22"), true);
//		dao.insertTodo(todo);	// 입력테스트용
//		dao.insertTodo(todo2);	// 입력테스트용
//		dao.insertTodo(todo3);	// 입력테스트용
//		
//		Todo t1 = dao.selectTodo(1);
//		System.out.println(t1.toString());	// 검색테스트용
//		
//		Boolean t2 = dao.deleteTodo(3);	// 삭제테스트용
//		
//		Todo todo4 = new Todo(2L,"할일_수정", "park", "수정한 할일", LocalDate.parse("2022-01-31"), true);
//		dao.updateTodo(todo4);	// 업데이트 테스트용
		
//		List<Todo> todos = dao.selectAllTodos();	// 전체검색 테스트용
//		for(Todo todo5 : todos) {
//			System.out.println(todo5.toString());
//		}
	}
}
