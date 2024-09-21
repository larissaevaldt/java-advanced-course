package localisation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocalisingDates {
    public static void main(String[] args) {
        Locale locUS = Locale.US;                                    // en_US
        Locale locFrench = new Locale("fr", "FR");   // fr_FR
        Locale locGerman = Locale.GERMAN;                            // de_DE

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);   // 2024-09-21T13:23:57.854166400

        DateTimeFormatter dateMediumStyle = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        System.out.println(dateMediumStyle.withLocale(locUS).format(ldt));       // Sep 21, 2024
        System.out.println(dateMediumStyle.withLocale(locFrench).format(ldt));   // 21 sept. 2024

        DateTimeFormatter timeShotStyle = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(timeShotStyle.withLocale(locUS).format(ldt));        // 1:28 PM
        System.out.println(timeShotStyle.withLocale(locGerman).format(ldt));    // 13:28

        DateTimeFormatter dateTimeShortStyle = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println(dateTimeShortStyle.format(ldt));                     // 21/09/2024, 13:33
        System.out.println(dateTimeShortStyle.withLocale(locUS).format(ldt));   // 9/21/24, 1:33 PM
    }
}
