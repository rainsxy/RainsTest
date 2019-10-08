package com.rains.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rains.biz.MockBiz;

@Service
public class MockServiceImpl implements MockService{

	@Autowired 
	private MockBiz mockBiz;
	@Override
	public void doMock(Map<String, String> map) {
		getMockBiz().doMock(map);	
	}
	public MockBiz getMockBiz() {
		return mockBiz;
	}
	public void setMockBiz(MockBiz mockBiz) {
		this.mockBiz = mockBiz;
	}

}
