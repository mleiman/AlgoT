package io.github.mleiman.algot.strategy.common;

import io.github.mleiman.algot.model.candle.Candle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TradingCalculatorUtilsTest {

	@Test
	public void shouldReturnTrueWhenOpenIsLowerThanClose() {
		Candle g = Candle.builder().open(new BigDecimal("14.145")).close(new BigDecimal("16.27")).build();
		assertTrue(TradingCalculatorUtils.isUptrend(g));
	}

	@Test
	public void shouldReturnFalseOpenIsHigherThanClose() {
		Candle r = Candle.builder().open(new BigDecimal("16.145")).close(new BigDecimal("14.27")).build();
		assertFalse(TradingCalculatorUtils.isUptrend(r));
	}

	@Test
	public void shouldThrowExceptionWhenCandleOpenAndCloseIsEqual() {
		Candle e = Candle.builder().open(new BigDecimal("16.145")).close(new BigDecimal("16.145")).build();
		assertThrows(IllegalStateException.class, () -> TradingCalculatorUtils.isUptrend(e));
	}

	@Test
	public void shouldProperlyCalculateAverage() {
		Candle c1 = Candle.builder().high(new BigDecimal("100")).low(new BigDecimal("80")).build();
		Candle c2 = Candle.builder().high(new BigDecimal("120")).low(new BigDecimal("80")).build();

		assertEquals(0,  new BigDecimal("30").compareTo(TradingCalculatorUtils.calculateAvg(List.of(c1, c2))));
	}

	@Test
	public void shouldProperlyCalculateAverageBasedOnOnlyOneCandle() {
		Candle c1 = Candle.builder().high(new BigDecimal("100")).low(new BigDecimal("80")).build();

		assertEquals(0,  new BigDecimal("20").compareTo(TradingCalculatorUtils.calculateAvg(List.of(c1))));
	}

	@Test
	public void shouldThrowAnExceptionWhenListOfCandlesIsEmpty() {
		assertThrows(IllegalStateException.class, () -> TradingCalculatorUtils.calculateAvg(Collections.emptyList()));
	}

	@Test
	public void shouldProperlyRecognizeLiquidityCandle() {
		Candle c1 = Candle.builder().high(new BigDecimal("100")).low(new BigDecimal("80")).build();

		assertTrue(TradingCalculatorUtils.isLiquidity(new BigDecimal(6), c1));
	}

	@Test
	public void shouldProperlyRecognizeLiquidityCandleEvenIfRangeIsExactlyEqualToADR() {
		Candle c1 = Candle.builder().high(new BigDecimal("100")).low(new BigDecimal("75")).build();

		assertTrue(TradingCalculatorUtils.isLiquidity(new BigDecimal(100), c1));
	}

	@Test
	public void shouldProperlyDenyLiquidityCandle() {
		Candle c1 = Candle.builder().high(new BigDecimal("100")).low(new BigDecimal("80")).build();

		assertFalse(TradingCalculatorUtils.isLiquidity(new BigDecimal(100), c1));
	}

	@Test
	public void shouldCalculateFib61_8LevelOnCandleThatIsUptrending() {
		Candle c1 = Candle.builder().high(new BigDecimal("100")).low(new BigDecimal("80")).build();
		assertEquals(0, new BigDecimal("87.64").compareTo(TradingCalculatorUtils.calculateFibLevel(c1, true, TradingCalculatorUtils.FIB_61_8)));
	}

	@Test
	public void shouldCalculateFib31_2LevelOnCandleThatIsDowntrading() {
		Candle c1 = Candle.builder().high(new BigDecimal("100")).low(new BigDecimal("80")).build();
		assertEquals(0, new BigDecimal("87.64").compareTo(TradingCalculatorUtils.calculateFibLevel(c1, false, TradingCalculatorUtils.FIB_38_2)));
	}
}
