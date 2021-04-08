package com.bank.dto;

public class BankLoan {
public boolean eligibility(float required_amt, float your_mothly_salary, int t, float r) {
	boolean status=false;
	float emi;
    r = r / (12 * 100); // one month interest
    t = t * 12; // one month period
    emi = (required_amt * r * (float)Math.pow(1 + r, t)) 
            / (float)(Math.pow(1 + r, t) - 1);
    if(emi<=your_mothly_salary) {
    	status=true;
    }
    else {
    	status=false;
    }
  
	return status;
		
	}
}
