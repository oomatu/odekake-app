package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Service;

import com.example.demo.dto.OdekakeDto;

import util.DbConnector;

@Service
public class DetailOdekakeSql {

	public OdekakeDto selectById(int id) {
		OdekakeDto dto = new OdekakeDto();

		String sql = "SELECT * FROM ODEKAKEINFO WHERE ID = ?";

		try (Connection conn = DbConnector.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					dto.setTitle(rs.getString("TITLE"));
					dto.setBody(rs.getString("BODY"));
					dto.setUrl1(rs.getString("URL1"));
					dto.setUrl2(rs.getString("URL2"));
					dto.setVisited(rs.getString("VISITED"));
					dto.setSeason(rs.getString("SEASON"));
					dto.setRemarks(rs.getString("REMARKS"));
					dto.setCreatedBy(rs.getString("CREATED_BY"));
					dto.setCreatedAt(rs.getString("CREATED_AT"));
					dto.setPlace(rs.getString("PLACE"));

				}
			}

		} catch (Exception e) {
			System.err.println("❌ SQL実行失敗: " + e.getMessage());
			e.printStackTrace();
		}

		return dto;
	}
}