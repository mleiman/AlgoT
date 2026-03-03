package io.github.mleiman.algot.strategy;

import io.github.mleiman.algot.strategy.touchnturn.TouchAndTurnStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartupRunner implements ApplicationRunner {

	private final TouchAndTurnStrategy touchAndTurnStrategy;

	@Override
	public void run(ApplicationArguments args) throws IllegalStateException {
		touchAndTurnStrategy.test();
	}
}
