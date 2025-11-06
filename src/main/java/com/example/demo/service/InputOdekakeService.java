package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.OdekakeDto;

@Service
public class InputOdekakeService {

	public boolean insertOdekakeInfo(OdekakeDto odekakeDto) {

		InputOdekakeSql inputOdekakeSql = new InputOdekakeSql();
		String Nickname = inputOdekakeSql.selectNickname(odekakeDto.getCreatedBy());
		odekakeDto.setCreatedBy(Nickname);
		boolean rs = inputOdekakeSql.insertOdekakeInfo(odekakeDto);

		return rs;
	}
	
	public boolean updateOdekakeInfo(OdekakeDto odekakeDto) {

		InputOdekakeSql inputOdekakeSql = new InputOdekakeSql();
		String Nickname = inputOdekakeSql.selectNickname(odekakeDto.getCreatedBy());
		odekakeDto.setCreatedBy(Nickname);
		boolean rs = inputOdekakeSql.updateOdekakeInfo(odekakeDto);

		return rs;
	}

}
