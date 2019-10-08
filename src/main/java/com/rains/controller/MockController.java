package com.rains.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rains.service.MockService;

@Controller
public class MockController {
	@Autowired
	private MockService mockService;

	@RequestMapping("/mock")
	public void mock(@RequestParam("uid") String uid,
			HttpServletRequest request, HttpServletResponse resp)
			throws IOException {
		System.out.println(uid + " come in!");
		Map<String, String> m = new HashMap<>();
		m.put("uid", uid);
		mockService.doMock(m);
		
		resp.getWriter().write("hello " + uid);
		resp.getWriter().flush();
		
		
	}
}
