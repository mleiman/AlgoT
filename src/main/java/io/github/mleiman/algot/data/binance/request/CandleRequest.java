package io.github.mleiman.algot.data.binance.request;

import io.github.mleiman.algot.data.common.ChartInterval;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Builder
@Getter
public class CandleRequest {
	@NonNull
	private String symbol;
	@NonNull
	ChartInterval interval;
	ZonedDateTime startTime;
	ZonedDateTime endTime;
	@Builder.Default
	String timeZone = "0"; // UTC by default
	@NonNull
	Integer limit;

	public Long getStartTime() {
		if (startTime == null) {
			return null;
		}
		ZoneId zoneId = ZoneId.of(timeZone);
		return startTime.toInstant().atZone(zoneId).toInstant().toEpochMilli();
	}
	public Long getEndTime() {
		if (endTime == null) {
			return null;
		}
		ZoneId zoneId = ZoneId.of(timeZone);
		return endTime.toInstant().atZone(zoneId).toInstant().toEpochMilli();
	}
}
