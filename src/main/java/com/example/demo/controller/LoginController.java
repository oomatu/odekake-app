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

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.OdekakeSummaryDto;
import com.example.demo.service.LoginService;
import com.example.demo.service.TopSql;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private TopSql topSql;

	@GetMapping("/login")
	public String startWeb() {
		return "login";
	}

	@PostMapping("/top")
	public String checkLogin(@ModelAttribute LoginDto loginDto, HttpSession session, Model model) {

		boolean rs = loginService.checkLogin(loginDto);
		if (rs) {
			String username = loginDto.getUsername();

			session.setAttribute("username", username);
			LocalDate today = LocalDate.now();
			model.addAttribute("username", username);
			model.addAttribute("today", today);

			List<OdekakeSummaryDto> titleList = topSql.getAllTitles();
			model.addAttribute("titleList", titleList);
			model.addAttribute("username", username);
			model.addAttribute("today", today);

			return "top";
		} else {
			model.addAttribute("errorMessage", "ユーザー名またはパスワードが間違っています");
			model.addAttribute("username", loginDto.getUsername());
			return "login";
		}
	}
}
