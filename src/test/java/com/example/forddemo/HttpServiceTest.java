package com.example.forddemo;

import com.example.forddemo.entity.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpServiceTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    ObjectMapper jsonToObjectConvertor = new ObjectMapper();

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        String jsonResultInString = this.restTemplate.getForObject("http://localhost:" + port + "/getVehicleInformation", String.class);
        Vehicle[] vehicles = jsonToObjectConvertor.readValue(jsonResultInString, Vehicle[].class);
        assertThat(vehicles.length).isEqualTo(0);
        assertThat(vehicles[0].getVehicleId()).isEqualTo(101L);

    }

}
