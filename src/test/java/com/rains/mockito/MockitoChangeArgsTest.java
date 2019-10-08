package com.rains.mockito;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rains.RainsServiceTest;
import com.rains.biz.MockBiz;
import com.rains.biz.MockBizImpl;
import com.rains.dao.MockDao;
import com.rains.dao.MockDaoImpl;
import com.rains.service.MockServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RainsServiceTest.class)
public class MockitoChangeArgsTest {

	@Autowired
	@InjectMocks
	private MockServiceImpl mockService;

	@Mock
	private MockBizImpl mockBiz;

	@Mock
	private MockDaoImpl mockDao;
	
	@Before 
	public void setup(){
		/**
		 * 没有执行这句前。@mock对象无法覆盖spring@Autowire注入的对象，即无法mock mockBiz对象。导致使用mockito测试有问题。
		 * 在其他项目有没有加这句直接成功的，估计是mockito和spring版本差异导致
		 * MockitoAnnotations.initMocks方法中扫描注解，将mock对象通过反射注入进去
		 * 
		 * ps：一个比较土的办法是手工设置:mockService.setMockBiz(mockBiz);
		 */
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test1() {
		System.out.println(1);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testChangeArgsInBiz() {
		Map<String, String> map = new HashMap<>();
		map.put("uid", "1");
		
		/**
		 * Mockito.argThat 用于修改void方法中的入参
		 * 注意这里mockBiz必须是mock对象
		 */
		Mockito.doNothing().when(mockBiz).doMock(Mockito.argThat(new ArgumentMatcher<Map<String, String>>() {
			@Override
			public boolean matches(Object argument) {
				Map<String, String> m  = (Map<String, String>) argument;
				m.put("age", "18");
				return false;
			}
		}));
		mockService.doMock(map);
		
		map.forEach((k, v) -> {
			System.out.println(k + " " + v);
		});
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testChangeArgsInDao() {
		Map<String, String> map = new HashMap<>();
		map.put("uid", "1");
		
		/**
		 * Mockito.argThat 用于修改void方法中的入参
		 * 这里无法跨2层去mock，service->biz->dao 这里bizDao是null对象了
		 */
		Mockito.doCallRealMethod().when(mockBiz).doMock(map);
		Mockito.doNothing().when(mockDao).doMock(Mockito.argThat(new ArgumentMatcher<Map<String, String>>() {
			@Override
			public boolean matches(Object argument) {
				Map<String, String> m  = (Map<String, String>) argument;
				m.put("age", "18");
				return false;
			}
		}));
		mockService.doMock(map);
		
		map.forEach((k, v) -> {
			System.out.println(k + " " + v);
		});
	}
}
