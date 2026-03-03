package io.github.mleiman.algot.strategy.common;

import io.github.mleiman.algot.model.candle.Candle;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Slf4j
public class TradingCalculatorUtils {

	public static final BigDecimal FIB_38_2 = new BigDecimal("0.382");
	public static final BigDecimal FIB_61_8 = new BigDecimal("0.618");

	private TradingCalculatorUtils() {}

	public static boolean isLiquidity(BigDecimal avg, Candle m15) {
		BigDecimal range =  m15.getHigh().subtract(m15.getLow()).abs();
		BigDecimal threshold = avg.multiply(new BigDecimal("0.20")); // DEFAULT: 0.25
		log.info("Threshold: {}", threshold);
		log.info("Range: {}", range);
		return range.compareTo(threshold) >= 0;
	}

	public static BigDecimal calculateAvg(List<Candle> last14Days) {
		if (last14Days == null || last14Days.isEmpty()) {
			throw new IllegalStateException("Cannot calculate average from empty candle list");
		}
		BigDecimal sum = BigDecimal.ZERO;
		for (Candle candle : last14Days) {
			sum = sum.add(candle.getHigh().subtract(candle.getLow()));
		}
		return sum.divide(new BigDecimal(last14Days.size()), 8, RoundingMode.HALF_UP);
	}

	public static boolean isUptrend(Candle candle) throws IllegalStateException {
		if (candle.getClose().compareTo(candle.getOpen()) == 0) {
			throw new IllegalStateException("Not applicable, try next day");
		}
		return candle.getClose().compareTo(candle.getOpen()) > 0;
	}

	public static BigDecimal calculateFibLevel(Candle candle, boolean isUptrend, BigDecimal level) {
		BigDecimal range = candle.getHigh().subtract(candle.getLow());
		if (isUptrend) {
			return candle.getHigh().subtract(range.multiply(level));
		}
		return candle.getLow().add(range.multiply(level));
	}
}
