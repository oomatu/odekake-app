package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DbConnector;

public class LoginSql {

	public String checkPass(String userName) {
		String pass = null;

		String sql = "SELECT PASSWORD FROM USERS WHERE USER_NAME = ?";
		try (Connection conn = DbConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, userName);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					pass = rs.getString("password");
				}
			}

		} catch (Exception e) {
			System.err.println("❌ SQL実行失敗: " + e.getMessage());
			e.printStackTrace();
		}

		return pass; // nullなら該当なし
	}
}
