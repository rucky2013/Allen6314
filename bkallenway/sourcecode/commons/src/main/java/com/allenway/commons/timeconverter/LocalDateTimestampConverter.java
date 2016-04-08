package com.allenway.commons.timeconverter;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;

@Slf4j
public class LocalDateTimestampConverter implements AttributeConverter<LocalDateTime, Timestamp> {
	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime ld) {
		return ld == null ? null : new Timestamp(ld.toDateTime().getMillis());
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp ts) {
		if (ts != null) {
            try {
                return LocalDateTime.fromDateFields(ts);
            } catch (IllegalArgumentException ex) {
                log.warn("Can't convert {} to LocalDate", ts, ex);
            }
        }
        return null;
	}
}
