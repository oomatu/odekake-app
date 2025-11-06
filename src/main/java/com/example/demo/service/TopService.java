package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.dto.OdekakeSummaryDto;

@Service
public class TopService {

	@Autowired
	private TopSql topSql;

	public String showTitle(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");
		LocalDate today = LocalDate.now();

		// タイトル一覧を取得（例：List<String>）
		List<OdekakeSummaryDto> titleList = topSql.getAllTitles();
		model.addAttribute("titleList", titleList);
		model.addAttribute("username", username);
		model.addAttribute("today", today);
		model.addAttribute("titleList", titleList);

		return "q";
	}
}
