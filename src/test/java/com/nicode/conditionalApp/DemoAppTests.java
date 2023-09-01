package com.nicode.conditionalApp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoAppTests {
    @Autowired
    TestRestTemplate restTemplate;
    private static GenericContainer<?> gc1 = new GenericContainer<>("devapp").withExposedPorts(8080);
    private static GenericContainer<?> gc2 = new GenericContainer<>("prodapp").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        gc1.start();
        gc2.start();
    }

    @Test
    void gc1Test() {
        ResponseEntity<String> resEnt = restTemplate.getForEntity("http://localhost:"+ gc1.getMappedPort(8080) + "/profile", String.class);
        System.out.println(resEnt.getBody());
        assertEquals(resEnt.getBody(),"Current profile is dev");
    }

    @Test
    void gc2Test() {
        ResponseEntity<String> resEnt = restTemplate.getForEntity("http://localhost:"+ gc2.getMappedPort(8081) + "/profile", String.class);
        System.out.println(resEnt.getBody());
        assertEquals(resEnt.getBody(),"Current profile is production");
    }
}
