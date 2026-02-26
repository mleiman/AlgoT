package io.github.mleiman.algot.model.candle;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
@Getter
public class Candle {
	private Instant openTime;
	private Instant closeTime;
	private BigDecimal open;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal close;
	private BigDecimal volume;
	private Integer trades;
}
