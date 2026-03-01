package io.github.mleiman.algotdata.binance.json;


import io.github.mleiman.algot.data.binance.json.BinanceCandleDeserializer;
import io.github.mleiman.algot.model.candle.Candle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.module.SimpleModule;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinanceCandleDeserializerTest {

	//BinanceCandleDeserializer deserializer;
	private static ObjectMapper mapper;
	@BeforeAll
	public static void setup() {
		mapper = new ObjectMapper();
	}

	@Test
	public void shouldDeserializeSingleCandleFromBinanceCandleResponse() {
		/*
		1499040000000,         // Kline open time
        "0.01634790",          // Open price
        "0.80000000",          // High price
        "0.01575800",          // Low price
        "0.01577100",          // Close price
        "148976.11427815",     // Volume
        1499644799999,         // Kline Close time
        "2434.19055334",       // Quote asset volume
        308,                   // Number of trades
        "1756.87402397",       // Taker buy base asset volume
        "28.46694368",         // Taker buy quote asset volume
        "0"                    // Unused field, ignore.

		 */
		String singleCandleJsonResponse = "[\n" +
				"        1772298540000,\n" + 		// Kline open time
				"        \"65037.00000000\",\n" +	// Open price
				"        \"65037.00000000\",\n" +	// High price
				"        \"64994.12000000\",\n" +	// Low price
				"        \"64994.97000000\",\n" +	// Close price
				"        \"26.31238000\",\n" +		// Volume
				"        1772298599999,\n" +		// Kline Close time
				"        \"1710477.46770160\",\n" +	// Quote asset volume
				"        2097,\n" +					// Number of trades
				"        \"0.67485000\",\n" +		// Taker buy base asset volume
				"        \"43871.42003670\",\n" +	// Taker buy quote asset volume
				"        \"0\"\n" +					// Unused field, ignore.
				"    ]";
		Candle deserializedCandle = mapper.readValue(singleCandleJsonResponse, Candle.class);

		assertEquals(Instant.ofEpochMilli(1772298540000L), deserializedCandle.getOpenTime());
		assertEquals(new BigDecimal("65037.00000000"), deserializedCandle.getOpen());
		assertEquals(new BigDecimal("65037.00000000"), deserializedCandle.getHigh());
		assertEquals(new BigDecimal("64994.12000000"), deserializedCandle.getLow());
		assertEquals(new BigDecimal("64994.97000000"), deserializedCandle.getClose());
		assertEquals(new BigDecimal("26.31238000"), deserializedCandle.getVolume());
		assertEquals(Instant.ofEpochMilli(1772298599999L), deserializedCandle.getCloseTime());
		assertEquals(2097, deserializedCandle.getTrades());
	}
}
