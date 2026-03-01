package io.github.mleiman.algot.data.binance;

import io.github.mleiman.algot.data.common.ChartInterval;
import io.github.mleiman.algot.model.candle.Candle;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class BinanceClient {

	private final WebClient client;

	public BinanceClient(WebClient client) {
		this.client = client;
	}

	public List<Candle> simpleApiCall() {
		return client.get()
				.uri(uriBuilder -> uriBuilder
						.path("klines")
						.queryParam("symbol", "BTCUSDT")
						.queryParam("interval", ChartInterval.M1.getInterval())
						.queryParam("limit", 3)
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Candle>>() {
				})
				.block();
	}
}
