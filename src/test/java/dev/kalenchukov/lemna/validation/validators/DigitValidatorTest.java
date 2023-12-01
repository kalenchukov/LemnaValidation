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
import dev.kalenchukov.lemna.validation.constraints.Digit;
import dev.kalenchukov.lemna.validation.constraints.Length;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Класс проверки аннотации {@link Digit}.
 *
 * @author Алексей Каленчуков
 */
public class DigitValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validateWithFieldTypeInvalid()
	{
		class Experimental
		{
			@Digit(min = 3, max = 13)
			private String digit = "123";
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
			@Digit(min = 3, max = 13)
			private Integer digit = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с некорректным значением в поле.
	 */
	@Test
	public void validateWithMinValue()
	{
		class Experimental
		{
			@Digit(min = 3, max = 13)
			private Integer digit = 12;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с некорректным значением в поле.
	 */
	@Test
	public void validateWithMaxValue()
	{
		class Experimental
		{
			@Digit(min = 3, max = 13)
			private Long digit = 12345678901234L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с полем типа {@code Integer}.
	 */
	@Test
	public void validateWithIntegerTypeValue()
	{
		class Experimental
		{
			@Digit(min = 3, max = 13)
			private Integer digit = 12345;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Long}.
	 */
	@Test
	public void validateWithLongTypeValue()
	{
		class Experimental
		{
			@Digit(min = 3, max = 13)
			private Long digit = 12345L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Short}.
	 */
	@Test
	public void validateWithShortTypeValue()
	{
		class Experimental
		{
			@Digit(min = 3, max = 13)
			private Short digit = 12345;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Byte}.
	 */
	@Test
	public void validateWithByteTypeValue()
	{
		class Experimental
		{
			@Digit(min = 3, max = 13)
			private Byte digit = 123;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}
}