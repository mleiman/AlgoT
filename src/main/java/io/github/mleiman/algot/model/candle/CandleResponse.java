package io.github.mleiman.algot.model.candle;

import io.github.mleiman.algot.data.common.ChartInterval;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CandleResponse {
	private String symbol;
	private ChartInterval interval;
	private List<Candle> candles;

}
