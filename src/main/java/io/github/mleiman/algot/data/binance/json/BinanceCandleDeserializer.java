package io.github.mleiman.algot.data.binance.json;

import io.github.mleiman.algot.model.candle.Candle;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ValueDeserializer;

import java.math.BigDecimal;
import java.time.Instant;

public class BinanceCandleDeserializer extends ValueDeserializer<Candle> {

	@Override
	public Candle deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
		JsonNode node = p.readValueAsTree();
		return Candle.builder()
				.openTime(Instant.ofEpochMilli(node.get(0).asLong()))
				.open(new BigDecimal(node.get(1).asString()))
				.high(new BigDecimal(node.get(2).asString()))
				.low(new BigDecimal(node.get(3).asString()))
				.close(new BigDecimal(node.get(4).asString()))
				.volume(new BigDecimal(node.get(5).asString()))
				.closeTime(Instant.ofEpochMilli(node.get(6).asLong()))
				.trades(node.get(8).asInt())
				.build();
	}
}
