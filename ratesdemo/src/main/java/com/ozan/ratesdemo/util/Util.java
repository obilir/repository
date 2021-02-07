package com.ozan.ratesdemo.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.ozan.ratesdemo.exception.RatesException;
import com.ozan.ratesdemo.exception.RatesExceptionType;

public class Util {
	public static BigDecimal getBigDecimalValueOfString(String value) throws RatesException {
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			throw new RatesException(RatesExceptionType.INVALID_NUMERIC_VALUE_EXCEPTION, String.format("Invalid number : %s", value));
		}
	}
	
	public static int getIntValueOfString(String value) throws RatesException {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			throw new RatesException(RatesExceptionType.INVALID_NUMERIC_VALUE_EXCEPTION, String.format("Invalid number : %s", value));
		}
	}
	
	public static long getLongValueOfString(String value) throws RatesException {
		try {
			return Long.parseLong(value);
		} catch (Exception e) {
			throw new RatesException(RatesExceptionType.INVALID_NUMERIC_VALUE_EXCEPTION, String.format("Invalid number : %s", value));
		}
	}

	public static String nullToEmpty(String value) {
		String retval = value;
		if(retval == null) retval= "";
		return retval;
	}
	
	public static boolean isEmpty(String value, boolean trimBeforeCheck) {
		String val = nullToEmpty(value);
		if(trimBeforeCheck) val = val.trim();
		return val.equals("");
	}
	
	public static boolean isEmpty(String value) {
		return isEmpty(value, false);
	}
	
	public static String cutIfLonger(String str, int length) {
		String ret = nullToEmpty(str);
		if(ret.length() > length)
			ret = ret.substring(0, length);
		return ret;
	}

	public static java.util.Date getDateFromStr(String date, String format) throws RatesException {
		java.util.Date retval = null;
		if(!isEmpty(date, true)) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				retval = sdf.parse(date.trim());
			} catch (Exception e) {
				throw new RatesException(RatesExceptionType.DATE_FORMAT_EXCEPTION, String.format("Invalid date : %s. Correct format should be : %s", date, format));
			}
		}
		return retval;
	}
	
	public static String getStackTrace(Exception e) {
		String ret = "";
		if(!(e instanceof RatesException)) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			ret = sw.toString();		
		}
		return ret;
	}
}