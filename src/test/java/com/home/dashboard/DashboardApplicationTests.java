package com.home.dashboard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.home.dashboard.DashboardApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DashboardApplication.class)
@WebAppConfiguration
public class DashboardApplicationTests {

	@Test
	public void contextLoads() {
	}

}
