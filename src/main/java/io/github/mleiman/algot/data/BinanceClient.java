package io.github.mleiman.algot.data;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class BinanceClient {

	private final WebClient client;

	public String simpleApiCall() {
		return client.get()
				.uri(uriBuilder -> uriBuilder
				.path("klines")
				.queryParam("symbol", "BTCUSDT")
				.queryParam("interval", "1m")
				.queryParam("limit", 3)
				.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}
}
