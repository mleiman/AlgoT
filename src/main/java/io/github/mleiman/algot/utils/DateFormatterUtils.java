package io.github.mleiman.algot.utils;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtils {

	public final static String DEFAULT_TIME_ZONE = "UTC";
	public final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
			.withZone(ZoneId.of("UTC"));
}
