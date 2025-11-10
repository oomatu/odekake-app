package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.OdekakeDto;
import com.example.demo.service.DetailOdekakeSql;

@Controller
public class DetailOdekakeController {

	@Autowired
	private DetailOdekakeSql detailOdekakeSql;

	@GetMapping("/detailOdekake")
	public String detailOdekake(@RequestParam("id") int id, Model model) {
		OdekakeDto dto = detailOdekakeSql.selectById(id);
		dto.setId(id);
		String createdAt = dto.getCreatedAt(); // 例: "2025-11-07 13:07:04.243523"
		String dateOnly = createdAt.split(" ")[0]; // "2025-11-07"
		model.addAttribute("createdAtFormatted", dateOnly);
		model.addAttribute("odekakeDto", dto);
		return "detailOdekake"; // ← 詳細ページのテンプレート名
	}

	@GetMapping("/editOdekake")
	public String editOdekake(@RequestParam("id") int id, Model model) {
		OdekakeDto dto = detailOdekakeSql.selectById(id);
		dto.setId(id);
		model.addAttribute("odekakeDto", dto);
		return "inputOdekake"; // ← 入力画面を再利用！
	}
}
