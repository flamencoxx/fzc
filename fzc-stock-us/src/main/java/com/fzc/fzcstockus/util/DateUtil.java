package com.fzc.fzcstockus.util;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * @author admin
 */
public class DateUtil {

    private static final Logger logger = Logger.getLogger(DateUtil.class.getName());

    public static Date dateFormatConversion(String parameterName, HttpServletRequest request){
        String str = request.getParameter(parameterName);
        if(StringUtils.isNotBlank(str) && str != null){
            SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy");
            Date date;
            try {
                date = dtf.parse(str);
                logger.info(str);
                return date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
