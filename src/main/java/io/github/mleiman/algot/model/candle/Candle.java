package io.github.mleiman.algot.model.candle;

import io.github.mleiman.algot.data.binance.json.BinanceCandleDeserializer;
import io.github.mleiman.algot.utils.DateFormatterUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

@JsonDeserialize(using = BinanceCandleDeserializer.class)
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

	@Override
	public String toString() {
		return "[" + DateFormatterUtils.DATE_TIME_FORMATTER.format(this.getOpenTime()) + "]" +
				" O: " + this.getOpen() + " |" +
				" H: " + this.getHigh() + " |" +
				" L: " + this.getLow() + " |" +
				" C: " + this.getClose() + " |" +
				" V: " + this.getVolume() + " |" +
				" T: " + this.getTrades() + " |";
	}
}
