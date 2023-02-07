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
import dev.kalenchukov.lemna.validation.constraints.Year;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки методов класса {@link YearValidator}.
 */
public class YearValidatorTest
{
	/**
	 * Проверка метода {@link Validation#validate()} с некорректным типом поля.
	 */
	@Test
	public void testValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Year(min = 1900, max = 2022)
			private String year = "1";
		}

		assertThrows(UnsupportedFieldTypeException.class, () -> {
			Validating validation = new Validation(new Experimental());
			validation.validate();
		});
	}

	/**
	 * Проверка метода {@link Validation#validate()} со значением {@code null}.
	 */
	@Test
	public void testValidValueNull()
	{
		class Experimental
		{
			@Year(min = 1900, max = 2022)
			private Integer year = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с некорректным значением.
	 */
	@Test
	public void testValidValueNotCorrect()
	{
		class Experimental
		{
			@Year(min = 1900, max = 2022)
			private Integer year = 1800;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с корректным значением до н. э..
	 */
	@Test
	public void testValidValueCorrectBc()
	{
		class Experimental
		{
			@Year(min = -3000, max = 2022)
			private Integer year = -1312;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с корректным значением в поле типа {@code Short}.
	 */
	@Test
	public void testValidShortTypeValue()
	{
		class Experimental
		{
			@Year(min = 1900, max = 2022)
			private Short year = 1971;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с корректным значением в поле типа {@code Integer}.
	 */
	@Test
	public void testValidIntegerTypeValue()
	{
		class Experimental
		{
			@Year(min = 1900, max = 2022)
			private Integer year = 1990;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с корректным значением в поле типа {@code Long}.
	 */
	@Test
	public void testValidLongTypeValue()
	{
		class Experimental
		{
			@Year(min = 1900, max = 2022)
			private Long year = 2001L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}
}