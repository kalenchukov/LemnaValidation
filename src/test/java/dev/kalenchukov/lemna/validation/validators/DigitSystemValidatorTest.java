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
import dev.kalenchukov.lemna.validation.constraints.DigitSystem;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.numeralsystem.resources.NumeralSystem;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DigitSystemValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@DigitSystem(numeralSystem = NumeralSystem.DECIMAL)
			private Integer digit = 12345;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void TestValidValueNull()
	{
		class Experimental
		{
			@DigitSystem(numeralSystem = NumeralSystem.DECIMAL)
			private String digit = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void TestValidValueNotCorrectEmpty()
	{
		class Experimental
		{
			@DigitSystem(numeralSystem = NumeralSystem.DECIMAL)
			private String digit = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void TestValidValueNotCorrect()
	{
		class Experimental
		{
			@DigitSystem(numeralSystem = NumeralSystem.DECIMAL)
			private Character digit = 'A';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeValueCorrect()
	{
		class Experimental
		{
			@DigitSystem(numeralSystem = NumeralSystem.DECIMAL)
			private String digit = "12345";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeValueCorrect()
	{
		class Experimental
		{
			@DigitSystem(numeralSystem = NumeralSystem.DECIMAL)
			private Character digit = '0';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}
}