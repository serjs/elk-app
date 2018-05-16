package net.express42.elksampleapp;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@SpringBootApplication
public class ElkSampleAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElkSampleAppApplication.class, args);
	}
}

@RestController
class ELKController {
	private static final Logger LOG = LogManager.getLogger(ELKController.class.getName());

	@Autowired
	RestTemplate restTemplete;

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RequestMapping(value = "/validrequest")
	public String helloWorld() {
		String response = "Hello user! Today's date: " + new Date();
		LOG.log(Level.INFO, "/validrequest - " + response);

		return response;
	}

	@RequestMapping(value = "/elk")
	public String helloWorld1() {
	    
		String response = restTemplete.exchange("http://localhost:8080/validrequest", HttpMethod.GET,null, new ParameterizedTypeReference<String>() {}).getBody();

		try {
			String exceptionrsp = restTemplete.exchange("http://localhost:8080/exception", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}).getBody();
			response = response + " === " + exceptionrsp;
		} catch (Exception e) {
			// exception should not reach here. Really bad practice :)
		}

		return (String) response;
	}

	@RequestMapping(value = "/exception")
	public String exception() {
		String rsp = "";
		try {
			int i = 1 / 0;
			// should get exception
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			String sStackTrace = sw.toString(); // stack trace as a string
			LOG.error("Exception As String - "+sStackTrace);

			rsp = sStackTrace;
		}

		return rsp;
	}
}
