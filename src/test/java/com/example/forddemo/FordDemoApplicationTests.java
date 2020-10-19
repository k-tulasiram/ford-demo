package com.example.forddemo;

import com.example.forddemo.controller.VehicleController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FordDemoApplicationTests {

	@Autowired
	private VehicleController vehicleController;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(vehicleController);
	}

}
