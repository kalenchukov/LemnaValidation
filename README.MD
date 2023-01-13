# LemnaValidation

Проверка значений полей класса.

## Использование

### Аннотации

Данными аннотациями необходимо помечать поля классов.

#### Аннотация "@NoNull"

Значение поля класса аннотированное данной аннотацией не должно быть null.

##### Параметры

|     Параметр      | Описание                   |  Обязательный  |
|:-----------------:|:---------------------------|:--------------:|
|      message      | Задаёт сообщение нарушения |      Нет       |

##### Поддерживаемые типы полей

Поддерживаются любые типы полей.

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.NoNull;

public class Experimental
{
    @NoNull
    private Long id = 1L;
}
```

#### Аннотация "@NoEmpty"

Значение поля класса аннотированное данной аннотацией не должно быть пустым.

##### Параметры

|  Параметр  | Описание                     | Обязательный |
|:----------:|:-----------------------------|:------------:|
|  message   | Задаёт сообщение нарушения   |     Нет      |
| allowSpace | Разрешить пробельные символы |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.NoEmpty;

public class Experimental
{
    @NoEmpty
    private String message = "сообщение";
}
```

#### Аннотация "@Length"

Значение поля класса аннотированное данной аннотацией ограничивается количеством символов.

##### Параметры

| Параметр | Описание                         | Обязательный |
|:--------:|:---------------------------------|:------------:|
| message  | Задаёт сообщение нарушения       |     Нет      |
|   min    | Минимальное количество символов  |     Нет      |
|   max    | Максимальное количество символов |      Да      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Length;

public class Experimental
{
    @Length(min = 3, max = 13)
    private String comment = "значение";
}
```

#### Аннотация "@Localization"

Значение поля класса аннотированное данной аннотацией ограничивается по локализации.

##### Параметры

| Параметр | Описание                         | Обязательный |
|:--------:|:---------------------------------|:------------:|
| message  | Задаёт сообщение нарушения       |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Localization;

public class Experimental
{
	@Localization
	private String localization = "ru-RU";
}
```

#### Аннотация "@CountryCodeAlpha2"

Значение поля класса аннотированное данной аннотацией ограничивается по коду страны.

##### Параметры

| Параметр | Описание                         | Обязательный |
|:--------:|:---------------------------------|:------------:|
| message  | Задаёт сообщение нарушения       |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.CountryCodeAlpha2;

public class Experimental
{
	@CountryCodeAlpha2
	private String countryCode = "RU";
}
```

#### Аннотация "@CountryCodeAlpha3"

Значение поля класса аннотированное данной аннотацией ограничивается по коду страны.

##### Параметры

| Параметр | Описание                         | Обязательный |
|:--------:|:---------------------------------|:------------:|
| message  | Задаёт сообщение нарушения       |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.CountryCodeAlpha3;

public class Experimental
{
	@CountryCodeAlpha3
	private String countryCode = "RUS";
}
```

#### Аннотация "@CountryCodeNumeric3"

Значение поля класса аннотированное данной аннотацией ограничивается по коду страны.

##### Параметры

| Параметр | Описание                         | Обязательный |
|:--------:|:---------------------------------|:------------:|
| message  | Задаёт сообщение нарушения       |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.CountryCodeNumeric3;

public class Experimental
{
	@CountryCodeNumeric3
	private String countryCode = "052";
}
```

#### Аннотация "@Password"

Значение поля класса аннотированное данной аннотацией ограничивается по требованиям к паролю.

##### Параметры

|  Параметр  | Описание                                    | Обязательный |
|:----------:|:--------------------------------------------|:------------:|
| minLetters | Минимальное количество букв                 |     Нет      |
| mixedCase  | Необходимость прописных и строчных букв     |     Нет      |
| minDigits  | Минимальное количество цифр                 |     Нет      |
| minSpecial | Минимальное количество специальных символов |     Нет      |
|  message   | Задаёт сообщение нарушения                  |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Password;

public class Experimental
{
	@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
	private String password = "helloMyWORLD!5";
}
```

#### Аннотация "@InetAddress"

Значение поля класса аннотированное данной аннотацией ограничивается по IP адресу.

##### Параметры

| Параметр | Описание                             | Обязательный |
|:--------:|:-------------------------------------|:------------:|
|    v4    | Разрешить IP адреса четвёртой версии |     Нет      |
|    v6    | Разрешить IP адреса шестой версии    |     Нет      |
| message  | Задаёт сообщение нарушения           |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.InetAddress;

public class Experimental
{
	@InetAddress(v6 = false)
	private String inetAddress = "192.168.1.1";
}
```

#### Аннотация "@MacAddress"

Значение поля класса аннотированное данной аннотацией ограничивается по MAC адресу.

##### Параметры

|  Параметр  | Описание                   | Обязательный |
|:----------:|:---------------------------|:------------:|
| ignoreCase | Игнорирование регистра     |     Нет      |
|  message   | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.MacAddress;

public class Experimental
{
	@MacAddress
	private String macAddress = "00-eF-cd-Ef-11-22";
}
```

#### Аннотация "@EmailAddress"

Значение поля класса аннотированное данной аннотацией ограничивается по адресу электронной почты.

##### Параметры

| Параметр | Описание                             | Обязательный |
|:--------:|:-------------------------------------|:------------:|
| message  | Задаёт сообщение нарушения           |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.EmailAddress;

public class Experimental
{
	@EmailAddress
	private String emailAddress = "aleksey.kalenchukov@yandex.ru";
}
```

#### Аннотация "@RgbNumeric"

Значение поля класса аннотированное данной аннотацией ограничивается по RGB модели в числовом представлении.

##### Параметры

|  Параметр  | Описание                   | Обязательный |
|:----------:|:---------------------------|:------------:|
|  message   | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.RgbNumeric;

public class Experimental
{
	@RgbNumeric
	private String rgbNumeric = "100,150, 200";
}
```

#### Аннотация "@RgbHex"

Значение поля класса аннотированное данной аннотацией ограничивается по RGB модели в шестнадцатеричной системе счисления.

##### Параметры

|  Параметр  | Описание                   | Обязательный |
|:----------:|:---------------------------|:------------:|
| ignoreCase | Игнорирование регистра     |     Нет      |
|  message   | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.RgbHex;

public class Experimental
{
	@RgbHex
	private String rgbHex = "#FFffFF";
}
```

#### Аннотация "@LetterAlphabet"

Значение поля класса аннотированное данной аннотацией ограничивается по буквам алфавита.

##### Параметры

| Параметр  | Описание                   | Обязательный |
|:---------:|:---------------------------|:------------:|
| alphabet  | Задаёт алфавит             |      Да      |
|  message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Character
* String

#### Пример

```java
import dev.kalenchukov.alphabet.resources.Alphabet;
import dev.kalenchukov.lemna.validation.constraints.LetterAlphabet;

public class Experimental
{
	@LetterAlphabet(alphabet = Alphabet.RUSSIAN)
	private String word = "Печаль";
}
```

#### Аннотация "@Letter"

Значение поля класса аннотированное данной аннотацией ограничивается по буквам.

##### Параметры

| Параметр  | Описание                   | Обязательный |
|:---------:|:---------------------------|:------------:|
| upperCase | Прописной регистр букв     |     Нет      |
| lowerCase | Строчный регистр букв      |     Нет      |
|  message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Character
* String

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Letter;

public class Experimental
{
	@Letter(upperCase = false)
	private String word = "печаль";
}
```

#### Аннотация "@Digit"

Значение поля класса аннотированное данной аннотацией ограничивается количеством цифр.

##### Параметры

| Параметр | Описание                         | Обязательный |
|:--------:|:---------------------------------|:------------:|
| message  | Задаёт сообщение нарушения       |     Нет      |
|   min    | Минимальное количество символов  |     Нет      |
|   max    | Максимальное количество символов |      Да      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Digit;

public class Experimental
{
    @Digit(min = 3, max = 13)
    private Integer digit = 123;
}
```

#### Аннотация "@DigitSystem"

Значение поля класса аннотированное данной аннотацией ограничивается по цифрам системы счисления.

##### Параметры

|    Параметр    | Описание                   | Обязательный |
|:--------------:|:---------------------------|:------------:|
| numeralSystem  | Задаёт систему счисления   |      Да      |
|    message     | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Character
* String

#### Пример

```java
import dev.kalenchukov.numeralsystem.resources.NumeralSystem;
import dev.kalenchukov.lemna.validation.constraints.DigitSystem;

public class Experimental
{
	@DigitSystem(numeralSystem = NumeralSystem.DECIMAL)
	private String word = "0123456789";
}
```

#### Аннотация "@Size"

Значение поля класса аннотированное данной аннотацией ограничивается количеством элементов.

##### Параметры

| Параметр | Описание                          | Обязательный |
|:--------:|:----------------------------------|:------------:|
| message  | Задаёт сообщение нарушения        |     Нет      |
|   min    | Минимальное количество элементов  |     Нет      |
|   max    | Максимальное количество элементов |      Да      |

##### Поддерживаемые типы полей

* Array
* Collection
* Map

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Size;

public class Experimental
{
    @Size(min = 2, max = 4)
    private Integer[] days = {1, 2, 3};
}
```

#### Аннотация "@Id"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом больше 0.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Id;

public class Experimental
{
    @Id
    private Long id = 13;
}
```

#### Аннотация "@DayOfWeek"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 0 до 6 или от 1 до 7.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|  format  | Задаёт формат дней недели  |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.DayOfWeek;

public class Experimental
{
	@DayOfWeek
	private Byte day = 5;
}
```

#### Аннотация "@DayOfMonth"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 1 до 31.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.DayOfMonth;

public class Experimental
{
	@DayOfMonth
	private Byte day = 24;
}
```

#### Аннотация "@DayOfYear"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 1 до 365.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.DayOfYear;

public class Experimental
{
	@DayOfYear
	private Short day = 256;
}
```

#### Аннотация "@WeekOfMonth"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 1 до 4.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.WeekOfMonth;

public class Experimental
{
	@WeekOfMonth
	private Byte week = 3;
}
```

#### Аннотация "@MonthOfYear"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 1 до 12.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.MonthOfYear;

public class Experimental
{
	@MonthOfYear
	private Byte month = 11;
}
```

#### Аннотация "@Millisecond"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 0 до 999.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Millisecond;

public class Experimental
{
	@Millisecond
	private Short millisecond = 256;
}
```

#### Аннотация "@Second"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 0 до 59.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Second;

public class Experimental
{
	@Second
	private Byte second = 46;
}
```

#### Аннотация "@Minute"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 0 до 59.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Minute;

public class Experimental
{
	@Minute
	private Byte minute = 32;
}
```

#### Аннотация "@Hour"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом от 1 до 12 или от 0 до 23.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|  format  | Задаёт формат времени      |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Hour;

public class Experimental
{
	@Hour
	private Byte hour = 23;
}
```

#### Аннотация "@Year"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом года.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|   min    | Задаёт минимальный год     |      Да      |
|   max    | Задаёт максимальный год    |      Да      |

##### Поддерживаемые типы полей

* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Year;

public class Experimental
{
	@Year(min = 1950, max = 2022)
	private Integer year = 1961;
}
```

#### Аннотация "@Number"

Значение поля класса аннотированное данной аннотацией ограничивается целым числом.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|   min    | Минимальное значение       |     Нет      |
|   max    | Максимальное значение      |     Нет      |

##### Поддерживаемые типы полей

* Byte
* Short
* Integer
* Long

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Number;

public class Experimental
{
    @Number(min = 0, max = 1000)
    private Integer sum = 785;
}
```

#### Аннотация "@NumberFloat"

Значение поля класса аннотированное данной аннотацией ограничивается дробным числом.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|   min    | Минимальное значение       |     Нет      |
|   max    | Максимальное значение      |     Нет      |

##### Поддерживаемые типы полей

* Float
* Double

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.NumberFloat;

public class Experimental
{
    @NumberFloat(min = 0.0, max = 1000.1000)
    private Double sum = 785.785;
}
```

#### Аннотация "@Pattern"

Значение поля класса аннотированное данной аннотацией ограничивается шаблоном регулярного выражения.

##### Параметры

| Параметр | Описание                   | Обязательный |
|:--------:|:---------------------------|:------------:|
| message  | Задаёт сообщение нарушения |     Нет      |
|  regexp  | Регулярное выражение       |      Да      |

##### Поддерживаемые типы полей

* String
* Character

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.Pattern;

public class Experimental
{
    @Pattern(regexp = "[0-9A-F]+")
    private String hexadecimal = "08A6D9";
}
```

#### Аннотация "@Exist"

Значение поля класса аннотированное данной аннотацией ограничивается собственной реализацией проверки существования.
Данную аннотацию можно применять несколько раз для одного поля класса.

##### Параметры

| Параметр  | Описание                     | Обязательный |
|:---------:|:-----------------------------|:------------:|
|  message  | Задаёт сообщение нарушения   |     Нет      |
| existence | Класс проверки существования |      Да      |

##### Создание собственного класса проверки существования

Создать собственный класс проверки существования можно создав класс реализующий интерфейс "Existable".

#### Пример

```java
import dev.kalenchukov.lemna.validation.interfaces.Existable;

public class GenderExistence implements Existable<String>
{
	@Override
	public boolean exist(@NotNull String value)
	{
		return "MALE".equals(value) || "FEMALE".equals(value);
	}
}
```

```java
import dev.kalenchukov.lemna.validation.constraints.Exist;

public class Experimental
{
    @Exist(existence = GenderExistence.class)
    private String gender = "MALE";
}
```

#### Аннотация "@Valid"

Значение поля класса аннотированное данной аннотацией ограничивается собственной реализацией проверки корректности.
Данную аннотацию можно применять несколько раз для одного поля класса.

##### Параметры

| Параметр  | Описание                    | Обязательный |
|:---------:|:----------------------------|:------------:|
|  message  | Задаёт сообщение нарушения  |     Нет      |
| validator | Класс проверки корректности |      Да      |

##### Создание собственного класса проверки корректности

Создать собственный класс проверки корректности можно создав класс реализующий интерфейс "Validable".

#### Пример

```java
import dev.kalenchukov.lemna.validation.interfaces.Validable;

public class BrowserValidator implements Validable<String>
{
	@Override
	public boolean valid(@NotNull final String value)
	{
		return "FIREFOX".equals(value) || "CHROME".equals(value);
	}
}
```

```java
import dev.kalenchukov.lemna.validation.constraints.Valid;

public class Experimental
{
    @Valid(validator = BrowserValidator.class)
    private String browser = "FIREFOX";
}
```

### Проверка значений полей класса

Создадим класс поля которого необходимо проверить:

#### Пример

```java
import dev.kalenchukov.lemna.validation.constraints.NoNull;
import dev.kalenchukov.lemna.validation.constraints.NoEmpty;
import dev.kalenchukov.lemna.validation.constraints.Length;
import dev.kalenchukov.lemna.validation.constraints.Size;

public class Experimental
{
    @NoNull
    private Long id = null;
    
    @NoEmpty
    private String message = "";
    
    @Length(min = 10, max = 13)
    private String comment = "значение";
    
    @Size(min = 5, max = 10)
    private Integer[] days = {1, 2, 3};
}
```

Проверка всех полей класса и возврат нарушений:

```java
import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.Validation;

Validating validation = new Validation(new Experimental());
List<Violating> violations = validation.validate();
/*
Результат выполнения:
[
    Violation {
        field='id',
        message='Значение поля '%FIELD%' не должно быть null',
        params={FIELD=id}
    },
    Violation {
        field='message',
        message='Значение поля '%FIELD%' не должно быть пустым',
        params={FIELD=message}
    }, 
    Violation {
        field='comment',
        message='Количество символов значения поля '%FIELD%' должно быть больше %MIN%',
        params={MIN=10, MAX=13, FIELD=comment}
    }, 
    Violation {
        field='days',
        message='Количество элементов поля '%FIELD%' должны быть больше %MIN%',
        params={MIN=5, MAX=10, FIELD=days}
    }
]
*/
```

Проверка всех полей класса и возврат первого найденного нарушения:

```java
import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.Validation;

Validating validation = new Validation(new Experimental());
validation.setPushy(false);
List<Violating> violations = validation.validate();
/*
Результат выполнения:
[
    Violation {
        field='id', 
        message='Значение поля '%FIELD%' не должно быть null', 
        params={FIELD=id}
    }
]
*/
```
