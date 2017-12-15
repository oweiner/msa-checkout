package de.predic8.workshop.checkout.service;

import de.predic8.workshop.checkout.dto.Basket;
import de.predic8.workshop.checkout.dto.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class CheckoutService {
	private final RestTemplate restTemplate;
	private final KafkaTemplate foo;

	/**
	public CheckoutService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	 **/

	public boolean areArticlesAvailable(Basket basket) {
		return basket.getItems().stream().allMatch(item -> {
				Stock stock = restTemplate.getForObject("http://stock/stocks/{uuid}", Stock.class, item.getArticle());

				return stock.getQuantity() >= item.getQuantity();
			}
		);
	}

	public boolean areArticlesAvailableFallback(Basket basket) {
		return false;
	}
}
