package com.example.demo.controller;

import java.time.LocalDate;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.OdekakeDto;
import com.example.demo.service.DetailOdekakeSql;

@Controller
public class TopController {

	@Autowired
	private DetailOdekakeSql detailOdekakeSql;

	@PostMapping("/inputOdekakeNew")
	public String showInputForm(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");

		model.addAttribute("odekakeDto", new OdekakeDto());
		model.addAttribute("username", username);
		model.addAttribute("mode", "new");

		return "inputOdekake";
	}

	@GetMapping("/inputOdekakeEdit")
	public String inputOdekake(@RequestParam("id") int id, HttpSession session, Model model) {
		OdekakeDto dto = detailOdekakeSql.selectById(id);
		dto.setId(id);
		model.addAttribute("odekakeDto", dto);
		model.addAttribute("mode", "edit"); // ← 編集モードを渡す
		model.addAttribute("username", session.getAttribute("username"));
		model.addAttribute("today", LocalDate.now());
		return "inputOdekake";
	}

}
