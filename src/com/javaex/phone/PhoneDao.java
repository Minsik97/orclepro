package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "phonedb";
	private String pw = "phonedb";

	// 생성자

	// 메소드g/s

	// 메소드 일반

	// DB접속
	private void getConnection() {
		// DB접속 기능
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

			System.out.println("[접속성공]");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	// 자원정리
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 사람 저장 기능
	public int personInsert(PhoneVo PhoneVo) {

		int count = 0;

		// DB접속
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " insert into person values(seq_person_id.nextval, ?, ?, ?) ";

			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, PhoneVo.getName());
			pstmt.setString(2, PhoneVo.getHp());
			pstmt.setString(3, PhoneVo.getCompany());

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao]" + count + "건 저장");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 자원정리
		close();

		return count;
	}

	// 정보 수정하기
	public int personUpdate(PhoneVo phoneVo) {

		int count = 0;

		// DB접속
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
					 query += " update person ";
					 query += " set name = ?, ";
					 query += " hp = ?, ";
					 query += " company = ? ";
					 query += " where person_id = ? ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phoneVo.getName());
			pstmt.setString(2, phoneVo.getHp());
			pstmt.setString(3, phoneVo.getCompany());
			pstmt.setInt(4, phoneVo.getPhoneId());

			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[dao]" + count + "건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		// 자원정리
		close();

		return count;
	}

	// 정보 삭제하기
	public int phoneDelete(int personId) {

		int count = 0;

		// DB접속
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " delete from person where person_id = ? ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao]" + count + "건 삭제");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();

		return count;

	}

	public List<PhoneVo> getPersonList() {
		List<PhoneVo> personList = new ArrayList<PhoneVo>();

		// DB접속
		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " select person_id, name, hp, company from person ";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {
				int phoneId = rs.getInt("person_id");
				String name = rs.getString("Name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PhoneVo vo = new PhoneVo(phoneId, name, hp, company);
				personList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();

		return personList;

	}

}