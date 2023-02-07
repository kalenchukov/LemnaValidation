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
import dev.kalenchukov.lemna.validation.constraints.RgbHex;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки аннотации {@link RgbHex}.
 */
public class RgbHexValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void testValidNotCorrectFieldType()
	{
		class Experimental
		{
			@RgbHex
			private Integer rgbHex = 12345;
		}

		assertThrows(UnsupportedFieldTypeException.class, () -> {
			Validating validation = new Validation(new Experimental());
			validation.validate();
		});
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void testValidValueNull()
	{
		class Experimental
		{
			@RgbHex
			private String rgbHex = null;
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
			@RgbHex
			private String rgbHex = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с RGB моделью в шестнадцатеричной системе счисления в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValue()
	{
		class Experimental
		{
			@RgbHex
			private String rgbHex = "#FFffFF";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с RGB моделью в шестнадцатеричной системе счисления без учёта регистра в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueCorrectIgnoreCase()
	{
		class Experimental
		{
			@RgbHex(ignoreCase = false)
			private String rgbHex = "#FFFFFF";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением из-за учёта регистра RGB модели в шестнадцатеричной системе счисления в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectIgnoreCase()
	{
		class Experimental
		{
			@RgbHex(ignoreCase = false)
			private String rgbHex = "#FFffFF";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}