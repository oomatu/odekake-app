package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

			List<OdekakeSummaryDto> allTitles = topSql.getAllTitles();

			List<OdekakeSummaryDto> placeList = allTitles.stream()
					.filter(dto -> "P".equals(dto.getCategory()))
					.collect(Collectors.toList());

			List<OdekakeSummaryDto> restaurantList = allTitles.stream()
					.filter(dto -> "R".equals(dto.getCategory()))
					.collect(Collectors.toList());

			model.addAttribute("placeList", placeList);
			model.addAttribute("restaurantList", restaurantList);
			model.addAttribute("username", username);
			model.addAttribute("today", today);

			return "top";
		} else {
			model.addAttribute("errorMessage", "ごめんなさい！エラーなので報告してください！！");
			return "inputOdekake";
		}
	}

	@GetMapping("/backtop")
	public String backInputOdekake(HttpSession session, Model model) {
		String username = (String) session.getAttribute("username");

		List<OdekakeSummaryDto> allTitles = topSql.getAllTitles();

		List<OdekakeSummaryDto> placeList = allTitles.stream()
				.filter(dto -> "P".equals(dto.getCategory()))
				.collect(Collectors.toList());

		List<OdekakeSummaryDto> restaurantList = allTitles.stream()
				.filter(dto -> "R".equals(dto.getCategory()))
				.collect(Collectors.toList());

		model.addAttribute("placeList", placeList);
		model.addAttribute("restaurantList", restaurantList);
		model.addAttribute("username", username);

		return "top";
	}

	@PostMapping("/updateOdekake")
	public String inputEdit(@ModelAttribute OdekakeDto odekakeDto, HttpSession session, Model model) {
		boolean rs = inputOdekakeService.updateOdekakeInfo(odekakeDto);
		if (rs) {
			String username = (String) session.getAttribute("username");
			LocalDate today = LocalDate.now();

			List<OdekakeSummaryDto> allTitles = topSql.getAllTitles();

			List<OdekakeSummaryDto> placeList = allTitles.stream()
					.filter(dto -> "P".equals(dto.getCategory()))
					.collect(Collectors.toList());

			List<OdekakeSummaryDto> restaurantList = allTitles.stream()
					.filter(dto -> "R".equals(dto.getCategory()))
					.collect(Collectors.toList());

			model.addAttribute("placeList", placeList);
			model.addAttribute("restaurantList", restaurantList);
			model.addAttribute("username", username);
			model.addAttribute("today", today);

			return "top";
		} else {
			model.addAttribute("errorMessage", "ごめんなさい！エラーなので報告してください！！");
			return "inputOdekake";
		}
	}
}
