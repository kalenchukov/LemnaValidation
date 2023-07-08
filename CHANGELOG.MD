# Журнал изменений
## v1.6.0 - 08.07.2023
### Изменено
* Изменена версия Java на 17.

## v1.5.0 - 25.05.2023
### Добавлено
* Добавлена аннотация Md5.

## v1.4.0 - 12.02.2023
### Добавлено
* Добавлена аннотация WeekOfMonth.
* Добавлена аннотация WeekOfYear.
* Добавлен метод Violating.equals(Object).
* Добавлен метод Violating.hashCode().
* Добавлен метод Violating.toString().

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

### Изменено
* Переименована аннотация Language -> Localization.
* Изменена директория ресурсных файлов.
* Изменена лицензия.
