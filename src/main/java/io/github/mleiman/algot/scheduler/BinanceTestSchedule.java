package io.github.mleiman.algot.scheduler;

import io.github.mleiman.algot.data.BinanceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class BinanceTestSchedule {

	private final BinanceClient client;

	@Scheduled(timeUnit = TimeUnit.MINUTES, fixedRate = 1L)
	public void testRunBinance() {
		System.out.println(client.simpleApiCall());
	}
}
