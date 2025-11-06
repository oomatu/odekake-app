package com.example.demo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.OdekakeSummaryDto;

import util.DbConnector;

@Service
public class TopSql {

	public List<OdekakeSummaryDto> getAllTitles() {
		List<OdekakeSummaryDto> list = new ArrayList<>();

		String sql = "SELECT ID, TITLE, VISITED, URL1, CREATED_BY FROM ODEKAKEINFO ORDER BY ID ASC";
		try (Connection conn = DbConnector.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				OdekakeSummaryDto dto = new OdekakeSummaryDto();
				dto.setId(rs.getString("ID"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setVisited(rs.getString("VISITED"));
				dto.setUrl1(rs.getString("URL1"));
				dto.setCreatedBy(rs.getString("CREATED_BY"));
				list.add(dto);
			}

		} catch (Exception e) {
			System.err.println("❌ SQL実行失敗: " + e.getMessage());
			e.printStackTrace();
		}

		return list;
	}
}
