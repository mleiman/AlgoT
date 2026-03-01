package io.github.mleiman.algot.config;

import io.github.mleiman.algot.data.binance.BinanceClient;
import io.github.mleiman.algot.data.binance.json.BinanceCandleDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class BinanceConfig {

	@Value("${binance.url}")
	private String url;

	@Bean
	public BinanceClient getBinanceClient() {
		WebClient client = WebClient.builder()
				.baseUrl(url)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		return new BinanceClient(client);
	}

	@Bean
	public BinanceCandleDeserializer getCandleDeserializer() {
		return new BinanceCandleDeserializer();
	}
}
