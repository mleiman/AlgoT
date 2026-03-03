package io.github.mleiman.algot.scheduler;

import io.github.mleiman.algot.data.binance.BinanceClient;
import io.github.mleiman.algot.data.binance.request.CandleRequest;
import io.github.mleiman.algot.data.common.ChartInterval;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class BinanceSchedule {

	private final BinanceClient client;

	@Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1L)
	public void runBinance() {
		/*CandleRequest request = CandleRequest.builder()
				.symbol("BTCUSDT")
				.interval(ChartInterval.M1)
				.limit(1)
				.timeZone("UTC")
				.build();
		client.fetchCandles(request).forEach(c -> log.info(c.toString()));*/
	}
}
