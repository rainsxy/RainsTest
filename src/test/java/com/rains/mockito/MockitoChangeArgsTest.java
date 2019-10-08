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
		 * û��ִ�����ǰ��@mock�����޷�����spring@Autowireע��Ķ��󣬼��޷�mock mockBiz���󡣵���ʹ��mockito���������⡣
		 * ��������Ŀ��û�м����ֱ�ӳɹ��ģ�������mockito��spring�汾���쵼��
		 * MockitoAnnotations.initMocks������ɨ��ע�⣬��mock����ͨ������ע���ȥ
		 * 
		 * ps��һ���Ƚ����İ취���ֹ�����:mockService.setMockBiz(mockBiz);
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
		 * Mockito.argThat �����޸�void�����е����
		 * ע������mockBiz������mock����
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
		 * Mockito.argThat �����޸�void�����е����
		 * �����޷���2��ȥmock��service->biz->dao ����bizDao��null������
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
