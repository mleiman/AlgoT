package io.github.mleiman.algot.data.binance;

import io.github.mleiman.algot.data.binance.request.CandleRequest;
import io.github.mleiman.algot.data.common.ChartInterval;
import io.github.mleiman.algot.model.candle.Candle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class BinanceClient {

	private final WebClient client;

	public BinanceClient(WebClient client) {
		this.client = client;
	}

	public List<Candle> fetchCandles(CandleRequest request) {
		log.info("Fetching candles for {}", request.getSymbol());
		return client.get()
				.uri(uriBuilder -> {
					uriBuilder.path("klines")
							.queryParam("symbol", request.getSymbol())
							.queryParam("interval", request.getInterval().toString())
							.queryParam("limit", request.getLimit());
					if (request.getStartTime() != null) uriBuilder.queryParam("startTime", request.getStartTime());
					if (request.getEndTime() != null) uriBuilder.queryParam("endTime", request.getEndTime());
					if (request.getTimeZone() != null) uriBuilder.queryParam("timeZone", request.getTimeZone());

					log.info(uriBuilder.toUriString());
					return uriBuilder.build();
				})
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatusCode::isError, resp ->
					resp.bodyToMono(String.class)
							.flatMap(body -> {
								log.error("Binance API error: {}", body);
								return Mono.error(new RuntimeException("Binance API error: " + body));
							})
				)
				.bodyToMono(new ParameterizedTypeReference<List<Candle>>() {})
				.doOnNext(candles -> candles.forEach(candle -> {log.info("{}", candle);}))
				.block();
	}

	public List<Candle> simpleApiCall() {
		log.info("Fetching candles for {}", "BTCUSDT");
		return client.get()
				.uri(uriBuilder -> uriBuilder
						.path("klines")
						.queryParam("symbol", "BTCUSDT")
						.queryParam("interval", ChartInterval.M1.toString())
						.queryParam("limit", 3)
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<Candle>>() {
				})
				.block();
	}
}
