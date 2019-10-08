package com.rains.biz;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rains.dao.MockDao;

@Service
public class MockBizImpl implements MockBiz {

	@Autowired
	public MockDao mockDao;
	
	@Override
	public void doMock(Map<String, String> map) {
		System.out.println("MockBizImpl:" + map.get("uid"));
		mockDao.doMock(map);
	}

}
