package com.bank.dao;

import java.sql.SQLException;

import com.bank.dto.BankDtoImp;

public class BankDaoImp implements BankDao{

	public Boolean login(String name, String pass) {
		Boolean status = BankDtoImp.login(name, pass);
		return status;
		
	}

	public String genrateOTP() {
		String otp = BankDtoImp.genrateOTP();
		return otp;
		
	}

	public Boolean bankLogin(String name, String pass) throws SQLException {
		Boolean status=BankDtoImp.bankLogin(name,pass);
		return status;
	}

}
