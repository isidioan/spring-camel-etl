package com.iioannou.cameletlprocessor.util;

import org.apache.camel.dataformat.bindy.Format;
import org.apache.camel.dataformat.bindy.format.FormatException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {


    public static class CustomConverter implements Format<Date> {

        @Override
        public String format(Date object) throws Exception {
            DateFormat format = getDateFormat("dd/MM/yyyy HH:mm");

            return format.format(object);
        }

        @Override
        public Date parse(String string) throws Exception {

            DateFormat format = getDateFormat("dd/MM/yyyy HH:mm");

            try {
               return format.parse(string);
            } catch (Exception ex) {
                try {

                    DateFormat oformat = getDateFormat("yyyy-mm-dd HH:mm:ss");
                    return oformat.parse(string);
                } catch (Exception e) {
                    throw new FormatException("Date provided does not fit dd/MM/yyyy HH:mm or yyyy-mm-dd HH:mm:ss");
                }

            }
        }


        protected DateFormat getDateFormat(String pattern) {
            SimpleDateFormat result;
                result = new SimpleDateFormat(pattern);
                result.setLenient(true);
            return result;
        }
    }


}
