/*
 * Copyright 2022 Алексей Каленчуков
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
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PasswordValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void testValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Password
			private Integer password = 12345;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void testValidValueNull()
	{
		class Experimental
		{
			@Password
			private String password = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void testValidValueNotCorrectEmpty()
	{
		class Experimental
		{
			@Password
			private String password = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueCorrect()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloMyWORLD!5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с не корректным значением из-за недостатка букв в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectMinLetters()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloWORLD!5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с не корректным значением из-за недостатка цифр в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectMinDigits()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloMyWORLD!";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с не корректным значением из-за недостатка специальных символов в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectMinSpecial()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "helloMyWORLD5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с не корректным значением из-за недостатка букв разного регистра в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectMixedCase()
	{
		class Experimental
		{
			@Password(minLetters = 11, mixedCase = true, minDigits = 1, minSpecial = 1)
			private String password = "hellomyworld!5";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}