package de.predic8.workshop.checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class CheckoutApplication {
	@Bean
	public Map<String, BigDecimal> prices() {
		return new ConcurrentHashMap<>();
	}

	@Bean
	@LoadBalanced
	public RestTemplate template() { return new RestTemplate();}

	public static void main(String[] args) {
		SpringApplication.run(CheckoutApplication.class, args);
	}

}