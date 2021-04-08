package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.dto.BankLoan;

@WebServlet("/CarLoan")
public class CarLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		// String name=req.getParameter("name");
		// String email=req.getParameter("emailid");
		// String mobile=req.getParameter("mobile");
		// String city=req.getParameter("city");
		// String dob=req.getParameter("dob");
		// String pan=req.getParameter("pan");
		float required_amt = Float.parseFloat(req.getParameter("loanamt"));
		// String occupation=req.getParameter("occupation");
		float your_mothly_salary = Float.parseFloat(req.getParameter("income"));
		// String veichel_manufacture=req.getParameter("manufacture");
		// String year_of_manufacture=req.getParameter("manufacture_year");
		int tenor = Integer.parseInt(req.getParameter("tenor"));
		float intrest = 9;
		BankLoan bankLoan = new BankLoan();
		boolean eligibility = bankLoan.eligibility(required_amt, your_mothly_salary, tenor, intrest);
		if (eligibility) {
			out.println("<script>");
			out.println(
					"alert('THANK YOU FOR APPLYLING CAR LOAN YOU ARE ELIGIBLE TO TAKE THE LOAN OUR EXECUTIVE WILL REACH YOU');");
			out.println("location.href='veichelloan.jsp';");
			out.println("</script>");
			// bankLoan.ApplyLoan(name,email,mobile,city,dob,pan,city,dob,pan,required_amt,occupation,your_mothly_salary,veichel_manufacture,year_of_manufacture,tenor);
		} else {
			out.println("<script>");
			out.println("alert('SORRY....AT THE MOMENT YOU ARE NOT ELIGIBLE TO TAKE THE CAR LOAN.');");
			out.println("location.href='veichelloan.jsp';");
			out.println("</script>");

		}
	}
}
