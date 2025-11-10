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

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.OdekakeDto;
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

	        model.addAttribute("username", username);
	        model.addAttribute("today", LocalDate.now());
	        model.addAttribute("odekakeDto", new OdekakeDto());

	        List<OdekakeSummaryDto> allTitles = topSql.getAllTitles();

	        List<OdekakeSummaryDto> placeList = allTitles.stream()
	            .filter(dto -> "P".equals(dto.getCategory()))
	            .collect(Collectors.toList());

	        List<OdekakeSummaryDto> restaurantList = allTitles.stream()
	            .filter(dto -> "R".equals(dto.getCategory()))
	            .collect(Collectors.toList());

	        model.addAttribute("placeList", placeList);
	        model.addAttribute("restaurantList", restaurantList);

	        return "top";
	    } else {
	        model.addAttribute("errorMessage", "ユーザー名またはパスワードが間違っています");
	        model.addAttribute("username", loginDto.getUsername());
	        return "login";
	    }
	}
}
