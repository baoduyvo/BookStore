package org.voduybao.bookstorebackend.tools.utils;

import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

import static org.voduybao.bookstorebackend.tools.utils.StrUtil.FORMAT_DATE_AND_TIME;

public class DateUtils {

    public static Instant parsePublicationDate(String date, String format) {
        if (Objects.isNull(date))
            return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault());

        try {
            if (format.contains("H") || format.contains("h") || format.contains("m") || format.contains("s")) {
                // Định dạng có giờ, phút hoặc giây
                LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
                return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            } else {
                // Định dạng chỉ có ngày
                LocalDate localDate = LocalDate.parse(date, formatter);
                return localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
            }
        } catch (Exception e) {
            if (format.equals(FORMAT_DATE_AND_TIME)) {
                throw new ResponseException(ResponseErrors.INVALID_FORMAT_DATE_AND_TIME);
            }

            throw new ResponseException(ResponseErrors.INVALID_FORMAT_DATE);
        }
    }
}
