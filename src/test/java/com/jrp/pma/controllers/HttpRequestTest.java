package com.jrp.pma.controllers;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInitializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void homePageReturnsVersionNumberCorrectly_thenSuccess() {

		System.out.println("### port = " + port);
		System.out.println("### " + "http//localhost:" + port + "/");
		String renderedHtml = this.restTemplate.getRootUri();
		System.out.println("### renderedHtml() = " + renderedHtml);
		String result = this.restTemplate.getForObject(renderedHtml, String.class);
		System.out.println("### result = " + result);
		// application.properties version = 3.3.3
		assertEquals(renderedHtml.contains("3.3.2"), true);
//		assertEquals(renderedHtml.contains("0.0.1"), true);
	}

}