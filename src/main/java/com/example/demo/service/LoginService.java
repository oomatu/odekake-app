package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginDto;

@Service
public class LoginService {

	LoginSql loginSql = new LoginSql();

	public boolean checkLogin(LoginDto loginDto) {

		String inputPass = loginDto.getPassword();
		String DbPass = loginSql.checkPass(loginDto.getUsername());

		if (DbPass != null && DbPass.equals(inputPass)) {
			return true;
		} else {
			return false;
		}
	}
}
