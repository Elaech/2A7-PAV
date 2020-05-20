package com.compulsory;

import java.util.Locale;
import java.util.ResourceBundle;

public class Error implements LocaleCommand {
    @Override
    public String execute() {
        Locale locale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("res/Messages", locale);
        return messages.getString("invalid");
    }
}
