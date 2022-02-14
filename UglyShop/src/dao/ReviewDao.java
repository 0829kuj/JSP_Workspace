package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import model.Review;

public class ReviewDao {
	private DataSource datasource;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
    public ReviewDao(DataSource datasource) {
        this.datasource = datasource;
        // 객체 생성시 커넥션 풀 datasource를 입력
    }
    
    // 모든 리뷰를 리스트로 리턴
    public List<Review> findAll(){
    	List<Review> list = new ArrayList<>();
    	
    	
		return list;
    }
}
