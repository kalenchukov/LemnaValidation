/*
 * Copyright © 2022-2023 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Letter;
import dev.kalenchukov.lemna.validation.constraints.Password;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Класс проверки аннотации {@link Password}.
 *
 * @author Алексей Каленчуков
 */
public class PasswordValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validateWithFieldTypeInvalid()
	{
		class Experimental
		{
			@Password
			private Integer password = 12345;
		}

		assertThatExceptionOfType(UnsupportedFieldTypeException.class).isThrownBy(() -> {
			Validating validation = new Validation(new Experimental());
			validation.validate();
		});
	}

	/**
	 * Проверка с значением в виде {@code null}.
	 */
	@Test
	public void validateWithValueNull()
	{
		class Experimental
		{
			@Password
			private String password = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void validateWithValueEmpty()
	{
		class Experimental
		{
			@Password
			private String password = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с полем типа {@code String}.
	 */
	@Test
	public void validateWithStringTypeValue()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloMyWORLD!5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с некорректным значением из-за недостатка букв в поле типа {@code String}.
	 */
	@Test
	public void validateWithStringTypeMinLettersValue()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloWORLD!5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с некорректным значением из-за недостатка цифр в поле типа {@code String}.
	 */
	@Test
	public void validateWithStringTypeMinDigitsValue()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloMyWORLD!";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с некорректным значением из-за недостатка специальных символов в поле типа {@code String}.
	 */
	@Test
	public void validateWithStringTypeMinSpecialValue()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloMyWORLD5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с некорректным значением из-за недостатка букв разного регистра в поле типа {@code String}.
	 */
	@Test
	public void validateWithStringTypeMixedCaseValue()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "hellomyworld!5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}
}