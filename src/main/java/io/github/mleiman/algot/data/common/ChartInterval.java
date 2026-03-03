package io.github.mleiman.algot.data.common;

public enum ChartInterval {
	M1("1m"),
	M15("15m"),
	D1("1d");

	private final String interval;
	ChartInterval(String interval) {
		this.interval = interval;
	}
	@Override
	public String toString() {
		return interval;
	}
}
