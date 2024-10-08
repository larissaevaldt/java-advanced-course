package localisation;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class LocalisingNumbers {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);    // en_IE
        formatNumbers();
        formatCurrencies();
        parseNumbers();
        parseCurrencies();
        customFormats();
    }

    public static void formatNumbers() {
        // format number ---> string
        double n = 77_000.11;
        // getInstance without passing a Locale will use the default one
        System.out.println(NumberFormat.getInstance().format(n));                    // 77,000.11 (en_IE locale)
        System.out.println(NumberFormat.getNumberInstance(Locale.US).format(n));     // 77,000.11
        System.out.println(NumberFormat.getNumberInstance(Locale.ITALY).format(n));  // 77.000,11
        System.out.println(NumberFormat.getNumberInstance(Locale.FRANCE).format(n)); // 77 000,11
    }

    public static void formatCurrencies() {
        // format number ---> string
        double n = 23.22;
        // getInstance without passing a Locale will use the default one
        System.out.println(NumberFormat.getCurrencyInstance().format(n));                    // €23.22 (en_IE locale)
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(n));           // $23.22
        System.out.println(NumberFormat.getCurrencyInstance(Locale.ITALY).format(n));        // 23,22 €
        System.out.println(NumberFormat.getCurrencyInstance(Locale.UK).format(n));           // £23.22
    }

    public static void parseNumbers() {
        // format string ---> number
        String unitedStatesNumber = "77,000.11", italianNumber = "77.000,11", frenchNumber = "77 000,11";
        try {
            System.out.println(NumberFormat.getNumberInstance(Locale.US).parse(unitedStatesNumber));  // 77000.11
            System.out.println(NumberFormat.getNumberInstance(Locale.ITALY).parse(italianNumber));    // 77000.11
            System.out.println(NumberFormat.getNumberInstance(Locale.FRANCE).parse(frenchNumber));    // 77000.11
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static void parseCurrencies() {
        // format string ---> number
        String irishCurrency = "€23.22", unitedStatesCurrency = "$23.22";
        String italianCurrency = "23,22 €", unitedKingdomCurrency = "£23.22";
        try {
            System.out.println(NumberFormat.getCurrencyInstance().parse(irishCurrency));                  // 23.22
            System.out.println(NumberFormat.getCurrencyInstance(Locale.US).parse(unitedStatesCurrency));  // 23.22
            System.out.println(NumberFormat.getCurrencyInstance(Locale.ITALY).parse(italianCurrency));    // 23.22
            System.out.println(NumberFormat.getCurrencyInstance(Locale.UK).parse(unitedKingdomCurrency)); // 23.22
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static void customFormats() {
        // DecimalFormat specifies the custom format
        // # means leave it out if we don't have a digit in this position
        // 0 means insert 0 if we don't have a digit in this position
        // format number ---> string
        double n = 77_000.17;

        System.out.println(new DecimalFormat("€#,###,###.#").format(n));   // €77,000.2
        System.out.println(new DecimalFormat("€0,000,000.0").format(n));   // €0,077,000.2
    }
}
