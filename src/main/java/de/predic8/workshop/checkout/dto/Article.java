package de.predic8.workshop.checkout.dto;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class Article {
	private static Logger log = LoggerFactory.getLogger(Article.class);

	private String article;
	private long quantity;

	public Article() {
	}

	public Article(String article, long quantity) {
		this.article = article;
		this.quantity = quantity;
	}

	public String getArticle() {
		return this.article;
	}

	public long getQuantity() {
		return this.quantity;
	}

	public String toString() {
		return "Article(article=" + article + ", quantity=" + quantity;
	}
}