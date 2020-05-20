package com.compulsory;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class Info implements LocaleCommand {
    @Override
    public String execute() {
        Locale locale = Locale.getDefault();
        String currenttag = new MessageFormat(LocaleManager.getInfo()).format(new Object[]{locale});
        StringBuilder returnValue = new StringBuilder(currenttag + "\n");
        returnValue.append(LocaleManager.getCountry() + locale.getDisplayCountry() + '\n');
        returnValue.append(LocaleManager.getLanguage() + locale.getDisplayLanguage() + '\n');
        returnValue.append(LocaleManager.getCurrency() + Currency.getInstance(locale).getCurrencyCode() + '(' + Currency.getInstance(locale).getSymbol() + ')' + '\n');
        returnValue.append(getWeekdays(locale) + '\n');
        returnValue.append(getMonths(locale) + '\n');
        returnValue.append(LocaleManager.getToday() + getTodaysData(locale) + '\n');
        return returnValue.toString();
    }

    public String getWeekdays(Locale locale) {
        WeekFields wf = WeekFields.of(locale);
        DayOfWeek day = wf.getFirstDayOfWeek();
        StringBuilder returnValue = new StringBuilder(LocaleManager.getWeekdays());
        for (int i = 0; i < DayOfWeek.values().length - 1; i++) {
            returnValue.append(day.getDisplayName(TextStyle.FULL, locale) + ", ");
            day = day.plus(1);
        }
        returnValue.append(day.getDisplayName(TextStyle.FULL, locale));
        return returnValue.toString();
    }

    public String getMonths(Locale locale) {
        DateFormatSymbols dfs = new DateFormatSymbols(locale);
        String[] months = dfs.getMonths();
        StringBuilder returnValue = new StringBuilder(LocaleManager.getMonths());
        int i =0;
        for (; i < months.length-2; i++) {
            returnValue.append(months[i]+", ");
        }
        returnValue.append(months[i]);

        return returnValue.toString();
    }

    public String getTodaysData(Locale locale){
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL,locale);
        Date today = new Date();
        return df.format(today);
    }
}
