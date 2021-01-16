package com.company.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.company.domain.MemberVO;

@Repository
public class memberDAO {
	@Autowired
	private DataSource ds;

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public int insert(MemberVO vo) {
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "insert into member values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getEmail());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int update(MemberVO vo) {
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "update member set password=? where userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPassword());
			pstmt.setString(2, vo.getUserid());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int delete(MemberVO vo) {
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "delete from member where userid=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPassword());
			result = pstmt.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public MemberVO getRow(MemberVO member) {
		MemberVO vo = null;
		try {
			con = ds.getConnection();
			String sql = "select * from member where userid=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setUserid(rs.getString("userid"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

	public List<MemberVO> getList() {
		List<MemberVO> list = new ArrayList<>();
		try {
			String sql = "select * from member";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserid(rs.getString("userid"));
				vo.setPassword(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setGender(rs.getString("gender"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
