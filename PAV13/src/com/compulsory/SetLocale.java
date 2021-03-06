package com.compulsory;

import java.text.MessageFormat;
import java.util.*;

public class SetLocale implements LocaleCommand {
    static List<String> correct_locales = Arrays.asList(new String[]{"en-US", "ro-RO"});
    String lang;
    String country;
    public SetLocale(String tag) {
        if(correct_locales.contains(tag)) {
            String[] aux = tag.split("-");
            lang = aux[0];
            country = aux[1];
        }
        else {
            lang = "en";
            country = "US";
        }
    }

    @Override
    public String execute() {
        Locale.setDefault(new Locale(lang,country));
        LocaleManager.updateLocale();
        Locale locale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("res/Messages", locale);
        Object[] args = {locale};
        String localeset = new MessageFormat(LocaleManager.getLocalSet()).format(args);

        return localeset;
    }
}
