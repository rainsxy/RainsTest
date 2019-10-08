package com.rains.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.rains.dao.MockDao;

@Repository
public class MockDaoImpl implements MockDao {

	@Override
	public void doMock(Map<String, String> map) {
		System.out.println("MockDaoImpl:" + map.get("uid"));
	}

}
