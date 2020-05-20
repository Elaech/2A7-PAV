package app;

import com.compulsory.*;
import com.compulsory.Error;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleExplore {


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Locale locale = Locale.getDefault();
        LocaleManager.updateLocale();

        while (true) {
            System.out.println(LocaleManager.getPrompt());
            String input = reader.readLine();
            LocaleCommand localeCommand ;
            if (input.equals(LocaleManager.getQuit())) {
                break;
            } else if (input.equals(LocaleManager.getShow())) {
                localeCommand = new DisplayLocale();
            } else if (input.equals(LocaleManager.getSet())) {
                System.out.println(LocaleManager.getTag());
                String  input_tag =reader.readLine();
                localeCommand = new SetLocale(input_tag);
            } else if (input.equals(LocaleManager.Info())) {
                localeCommand = new Info();
            } else {
                localeCommand = new Error();
            }
            System.out.println(localeCommand.execute());
        }
        reader.close();
    }
}
