package com.compulsory;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocale implements LocaleCommand{
    @Override
    public String execute() {
        Locale locale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("res/Messages", locale);
        String locales = messages.getString("locales");
        StringBuilder returnValue = new StringBuilder("");
        returnValue.append(locales+"\n");
        for(Locale locale1: Locale.getAvailableLocales()){
            returnValue.append(locale1.getDisplayLanguage(locale)+ "   " + locale1.getDisplayCountry()+"\n");
        }
        return returnValue.toString();
    }
}
