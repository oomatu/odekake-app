package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.example.demo.dto.OdekakeDto;

import util.DbConnector;

@Service
public class InputOdekakeSql {

	public boolean insertOdekakeInfo(OdekakeDto odekakeDto) {

		String sql = "INSERT INTO odekakeinfo (title, body, url1, url2, season, remarks, created_by) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DbConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, odekakeDto.getTitle());
			pstmt.setString(2, odekakeDto.getBody());
			pstmt.setString(3, odekakeDto.getUrl1());
			pstmt.setString(4, odekakeDto.getUrl2());
			pstmt.setString(5, odekakeDto.getSeason());
			pstmt.setString(6, odekakeDto.getRemarks());
			pstmt.setString(7, odekakeDto.getCreatedBy());

			pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			System.err.println("❌ SQL実行失敗: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateOdekakeInfo(OdekakeDto odekakeDto) {

		String sql = "UPDATE odekakeinfo SET title = ?, body = ?, url1 = ?, url2 = ?, season = ?, remarks = ?, created_by = ? WHERE ID = ?";
		try (Connection conn = DbConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, odekakeDto.getTitle());
			pstmt.setString(2, odekakeDto.getBody());
			pstmt.setString(3, odekakeDto.getUrl1());
			pstmt.setString(4, odekakeDto.getUrl2());
			pstmt.setString(5, odekakeDto.getSeason());
			pstmt.setString(6, odekakeDto.getRemarks());
			pstmt.setString(7, odekakeDto.getCreatedBy());
			pstmt.setInt(8, odekakeDto.getId());
			
			pstmt.executeUpdate();
			return true;

		} catch (Exception e) {
			System.err.println("❌ SQL実行失敗: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public String selectNickname(String nickname) {

		String sql = "SELECT NICKNAME FROM USERS WHERE USER_NAME = ?";
		try (Connection conn = DbConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, nickname);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					nickname = rs.getString("NICKNAME");
				}
			}

		} catch (Exception e) {
			System.err.println("❌ SQL実行失敗: " + e.getMessage());
			e.printStackTrace();
		}

		return nickname; // nullなら該当なし
	}
}
