package edu.gatech.seclass.jobcompare6300.utility;

import androidx.room.TypeConverter;

import java.math.BigDecimal;

public class Converter {
    @TypeConverter
    public static String fromBigDecimalToString(BigDecimal b) {
        return b.toString();
    }

    @TypeConverter
    public static BigDecimal fromStringToBigDecimal(String s) {
        return new BigDecimal(s);
    }

}
