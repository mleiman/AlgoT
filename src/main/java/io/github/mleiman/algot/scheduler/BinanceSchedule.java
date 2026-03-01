package io.github.mleiman.algot.scheduler;

import io.github.mleiman.algot.data.binance.BinanceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BinanceSchedule {

	private final BinanceClient client;

	@Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1L)
	public void runBinance() {
		System.out.println(client.simpleApiCall());
	}
}
