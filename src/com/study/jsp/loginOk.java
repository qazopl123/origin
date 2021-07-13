package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginOk implements Service {
	public loginOk() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("loginOk");
		request.setCharacterEncoding("UTF-8");
		
		// Query String으로 들어옴 = getParameter로 받음
	    String id = request.getParameter("id");
	    String pw = request.getParameter("pw");
	    
	    // Member 찾아주기
	    MemberDao dao = MemberDao.getInstance();
	    MemberDto dto = dao.getMember(id);
	    
	    // ID-PW 확인
	    int checkNum = dao.userCheck(id, pw);
	    dto.setId(id);
	    dto.setPw(pw);
	    	    
		String json_data="";
	    if (checkNum == -1) {
	    	json_data="{\"code\":\"fail\",\"desc\":\"아이디가 존재하지 않습니다.\"}";
	    } else if (checkNum == 1) {
	    	HttpSession session = request.getSession();
	    	String name = dto.getName();
	    	session.setAttribute("id", dto.getId());
	    	session.setAttribute("name", name);
	    	session.setAttribute("ValidMem", "yes");
	    	json_data="{\"code\":\"success\",\"desc\":\"로그인되었습니다.\"}";
	    } else if (checkNum == 0) {
	    	json_data="{\"code\":\"loginFail\",\"desc\":\"비밀번호가 틀립니다.\"}";
	    }
	    response.setContentType("application/json; charset=UTF-8");
		PrintWriter writer = response.getWriter();
		writer.println(json_data);
		writer.close();
	}
}
