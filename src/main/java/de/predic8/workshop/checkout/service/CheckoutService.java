package de.predic8.workshop.checkout.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import de.predic8.workshop.checkout.dto.Basket;
import de.predic8.workshop.checkout.dto.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static net.logstash.logback.marker.Markers.appendEntries;

@Service
public class CheckoutService {
	private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);


	private final RestTemplate restTemplate;

	public CheckoutService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public boolean areArticlesAvailable(Basket basket) {

		return basket.getItems().stream().allMatch(item -> {

			log.info("Checking article " + item.getArticleId() + " for "
					+ item.getQuantity() + " pieces.");

			Stock stock = restTemplate.getForObject(
					"http://stock/stocks/{uuid}", Stock.class, item.getArticleId());

			return stock.getQuantity() >= item.getQuantity();
		});
	}

	public boolean fallback(Basket basket, Throwable t) {

		Map<String, Object> entries = new HashMap<>();
		entries.put("fallback", t);

		log.error(appendEntries(entries), "");
		return true;
	}
}
