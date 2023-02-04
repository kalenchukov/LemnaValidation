# Журнал изменений
## vX.X.X - XX.XX.XXXX
### Добавлено
* Добавлена аннотация WeekOfMonth.
* Добавлена аннотация WeekOfYear.

## v1.3.0 - 04.12.2022
### Добавлено
* Добавлена аннотация Documented к аннотациям.
* Добавлена аннотация CountryCodeAlpha2.
* Добавлена аннотация CountryCodeAlpha3.
* Добавлена аннотация CountryCodeNumeric3.
* Добавлен AUTHOR.MD.
* Добавлен INSTALL.MD.
* Добавлен CHANGELOG.MD.

### Изменено
* Выставлена печать логов уровня INFO.
* Обновлён README.MD.
* Изменена версия требуемого модуля string-regexp до 1.4.0.

## v1.2.0 - 16.10.2022
### Добавлено
* Добавлена аннотация MacAddress.
* Добавлена аннотация RgbHex.
* Добавлена аннотация RgbNumeric.
* Добавлен плагин maven-assembly в pom.xml.
* Добавлен плагин maven-compiler в pom.xml.
* Добавлен плагин maven-dependency в pom.xml.
* Добавлен плагин maven-javadoc в pom.xml.
* Добавлен плагин maven-source в pom.xml.

### Изменено
* Изменена версия требуемого модуля Alphabet.
* Изменена версия требуемого модуля NumeralSystem.
* Изменена версия требуемого модуля StringFormatting.
* Изменена версия требуемого модуля StringRegexp.
* Пакет dev.kalenchukov.lemna.validation открыт для рефлексии.

### Исправлено
* Исправлен баг в методе RgbNumericValidator.isValidString(RgbNumeric, String).

## v1.1.0 - 15.09.2022
### Добавлено
* Добавлена аннотация DigitSystem.
* Добавлена аннотация EmailAddress.
* Добавлена аннотация InetAddress.
* Добавлена аннотация Letter.
* Добавлена аннотация LetterAlphabet.
* Добавлена аннотация Password.
* Внедрён модуль StringRegexp.

### Изменено
* Переименована аннотация Language -> Localization.
* Изменена версия требуемого модуля Alphabet.
* Изменена версия требуемого модуля NumeralSystem.
* Изменена директория ресурсных файлов.
* Изменена лицензия.
