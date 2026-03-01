package io.github.mleiman.algot.data.common;

public enum ChartInterval {
	M1("1m"),
	M15("15m");

	private final String interval;
	ChartInterval(String interval) {
		this.interval = interval;
	}
	public String getInterval() {
		return interval;
	}
}
