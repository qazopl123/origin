package com.study.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modifyOk implements Service {
	public modifyOk() {
		
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session으로 받아준다
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		
		// Member 찾아주기
		String id = (String)session.getAttribute("id");
		MemberDao dao = MemberDao.getInstance();
		MemberDto dto = dao.getMember(id);
		
		
		// Member의 원래정보 찾아주기
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String eMail = request.getParameter("eMail");
		String address = request.getParameter("address");
		
		// 새로운 정보로 업데이트 해주기
		dto.setId(id);
//		dto.setPw(pw);
		dto.setName(name);
		dto.seteMail(eMail);
		dto.setAddress(address);
		dto.setrDate(new Timestamp(System.currentTimeMillis()));
		
//	    dto.setId(id);
//	    dto.setPw(pw);
	    int userCheck = dao.userCheck(id, pw); 
		int ri = dao.updateMember(dto);	// 업데이트 OK?
		
		String json_data="";
		if (userCheck == 1) {	// id-pw 일치
			if (ri == 1){
				json_data="{\"code\":\"success\",\"desc\":\"업데이트 성공!\"}";
			} else {			// 업데이트 실패
				json_data="{\"code\":\"fail\",\"desc\":\"업데이트 실패!\"}";
			}
		} else {				// id-pw 불일치
			json_data="{\"code\":\"loginFail\",\"desc\":\"아이디와 비밀번호가 일치하지 않습니다!\"}";
		}
		response.setContentType("application/json; charset=UTF-8");
	    PrintWriter writer = response.getWriter();
	    writer.println(json_data);
	    writer.close();
	}
}
