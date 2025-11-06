package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.OdekakeDto;
import com.example.demo.dto.OdekakeSummaryDto;
import com.example.demo.service.InputOdekakeService;
import com.example.demo.service.TopSql;

@Controller
public class InputOdekakeController {

	@Autowired
	private InputOdekakeService inputOdekakeService;

	@Autowired
	private TopSql topSql;

	@PostMapping("/saveOdekakeInfo")
	public String saveOdekakeInfo(@ModelAttribute OdekakeDto odekakeDto, HttpSession session, Model model) {

		odekakeDto.setCreatedBy((String) session.getAttribute("username"));
		boolean rs = inputOdekakeService.insertOdekakeInfo(odekakeDto);
		if (rs) {
			String username = (String) session.getAttribute("username");
			LocalDate today = LocalDate.now();

			// タイトル一覧を取得（例：List<String>）
			List<OdekakeSummaryDto> titleList = topSql.getAllTitles();
			model.addAttribute("titleList", titleList);
			model.addAttribute("username", username);
			model.addAttribute("today", today);

			return "top";
		} else {
			return "inputOdekake";
		}
	}

	@GetMapping("/backtop")
	public String backInputOdekake(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");

		List<OdekakeSummaryDto> titleList = topSql.getAllTitles();
		model.addAttribute("titleList", titleList);
		model.addAttribute("username", username);

		return "top";
	}

	@PostMapping("/updateOdekake")
	public String inputEdit(@ModelAttribute OdekakeDto odekakeDto, HttpSession session, Model model) {
		boolean rs = inputOdekakeService.updateOdekakeInfo(odekakeDto);
		if (rs) {
			String username = (String) session.getAttribute("username");
			LocalDate today = LocalDate.now();

			// タイトル一覧を取得（例：List<String>）
			List<OdekakeSummaryDto> titleList = topSql.getAllTitles();
			model.addAttribute("titleList", titleList);
			model.addAttribute("username", username);
			model.addAttribute("today", today);

			return "top";
		} else {
			return "inputOdekake";
		}
	}
}
