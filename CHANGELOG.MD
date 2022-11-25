# Журнал изменений

## vX.X.X - XX.XX.XXXX

### Добавлено
* Добавлена аннотация Documented к аннотациям.

### Изменено
* Выставлена печать логов уровня INFO.

### Исправлено
*

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
