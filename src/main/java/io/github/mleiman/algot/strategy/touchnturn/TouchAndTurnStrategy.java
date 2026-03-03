package io.github.mleiman.algot.strategy.touchnturn;

import io.github.mleiman.algot.data.binance.BinanceClient;
import io.github.mleiman.algot.data.binance.request.CandleRequest;
import io.github.mleiman.algot.data.common.ChartInterval;
import io.github.mleiman.algot.model.candle.Candle;
import io.github.mleiman.algot.strategy.common.TradingCalculatorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TouchAndTurnStrategy {
	private final BinanceClient binanceClient;

	public void test() throws IllegalStateException {
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC-5"));
		//withDayOfMonth(28).withMonth(2);
		Candle m15 = binanceClient.fetchCandles(CandleRequest.builder()
				.limit(1)
				.interval(ChartInterval.M15)
				.symbol("BTCUSDT")
				.timeZone("-5")
				.startTime(now
						.minusDays(1).withHour(9).withMinute(30).withSecond(0))
				.build()).getFirst();

		boolean isUptrend = TradingCalculatorUtils.isUptrend(m15);

		List<Candle> last14Days = binanceClient.fetchCandles(CandleRequest.builder()
				.limit(14)
				.interval(ChartInterval.D1)
				.symbol("BTCUSDT")
				.startTime(now
						.minusDays(15).withHour(9).withMinute(30).withSecond(0))
				.timeZone("-5").build());

		BigDecimal avg = TradingCalculatorUtils.calculateAvg(last14Days);

		log.info("Avg: {}", avg);
		log.info("isUptrend: {}", isUptrend);

		if (!TradingCalculatorUtils.isLiquidity(avg, m15)) {
			throw new IllegalStateException("Not a liquidity candle.");
		}
		log.info("LIQUIDITY detected!");

		BigDecimal fibLevel = TradingCalculatorUtils.calculateFibLevel(m15, isUptrend, isUptrend ? TradingCalculatorUtils.FIB_61_8 : TradingCalculatorUtils.FIB_38_2);

		/*Entry — limit order at high (short) or low (long) of the 15min candle
		Profit target — the fib level
		Stop loss — 2:1 risk ratio*/

		// go short when uptrend, go long when downtrend
		BigDecimal entry = isUptrend ? m15.getHigh() : m15.getLow(); // limit order
		BigDecimal stopLoss = calculateStopLoss(fibLevel, entry, isUptrend);
		log.info("Going {} with entry: {}", isUptrend ? "SHORT" : "LONG", entry);
		log.info("Profit Level: {}", fibLevel);
		log.info("Stop Loss: {}", stopLoss);


	}

	private BigDecimal calculateStopLoss(BigDecimal fibLevel, BigDecimal entry, boolean isUptrend) {
		BigDecimal risk = fibLevel.subtract(entry).abs();
		if (isUptrend) {
			return entry.add(risk.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP));
		}
		return entry.subtract(risk.divide(BigDecimal.valueOf(2), 2, RoundingMode.HALF_UP));
	}
}
