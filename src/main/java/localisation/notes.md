# Localisation
* Localisation involves ensuring that your program is adaptable to the location from which it is being executed.
* This involves translating strings, use of different spellings and formatting of dates/numbers for the locale.
* A locale, in its simplest terms, is a language/country pairing.
* Locale format: language_COUNTRY
  * COUNTRY is in capitals and is optional
  * "l_C" = "lake Como"
* To create/select a locale that is not default locale, there are 3 popular options:
  * constructors:
    * pass in a language only or both a language and a country
  * built-in constants:
    * the *Locale* class provides constants for the most popular locales
  * builder design pattern
    * flexible - specify the properties you want, in any order
    * locale is built at the end

## Localising Numbers and Currencies
* Currencies and numbers differ between locales.
* The *NumberFormat* class has several *static* factory methods that enable us to both in both directions i.e. from a *String* to a number and a number to a *String*
  * getInstance(), getInstance(locale)
  * getNumberInstance(), getNumberInstance(locale)
  * getCurrencyInstance(), getCurrencyInstance(locale)
* Once you have the *NumberFormat* instance you require, you can invoke *format()* to convert a number to a *String* and *parse()* to convert a *String* into a number.

## Localising Dates
* Date format vary by locale
* The *DateTimeFormatter* contains factory methods to obtain formatters for dates (and times) for the current locale:
    * DateTimeFormatter.ofLocalizedDate(dateStyle)
    * DateTimeFormatter.ofLocalizedTime(timeStyle)
    * DateTimeFormatter.ofLocalizedDateTime(dateTimeStyle)
* To customise for a specific locale, append *withLocale(locale)*

## Category enums
* When you change the default locale with *Locale.setDefault()*, certain option regarding display and formatting are set automatically.
* We can set these options individually ourselves using the Locale.Category enums: DISPLAY and FORMAT.
  * DISPLAY - relates to display information
  * FORMAT - formatting currencies, dates and numbers.