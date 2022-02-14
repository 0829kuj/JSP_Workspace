package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class ReplyDao {
	private DataSource datasource;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
    public  ReplyDao(DataSource datasource) {
        this.datasource = datasource;
        // 객체 생성시 커넥션 풀 datasource를 입력
    }
}
