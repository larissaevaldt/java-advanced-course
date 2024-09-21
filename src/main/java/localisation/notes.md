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