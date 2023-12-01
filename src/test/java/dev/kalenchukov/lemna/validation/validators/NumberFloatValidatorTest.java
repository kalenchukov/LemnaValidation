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
import dev.kalenchukov.lemna.validation.constraints.NumberFloat;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Класс проверки аннотации {@link NumberFloat}.
 *
 * @author Алексей Каленчуков
 */
public class NumberFloatValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validateWithFieldTypeInvalid()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private String sum = "13.13";
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
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с значением меньше минимального в поле типа {@code Double}.
	 */
	@Test
	public void validateWithDoubleTypeLessMinValue()
	{
		class Experimental
		{
			@NumberFloat(min = 100, max = 1000.1000)
			private Double sum = 10.10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением меньше минимального в поле типа {@code Float}.
	 */
	@Test
	public void validateWithFloatTypeLessMinValue()
	{
		class Experimental
		{
			@NumberFloat(min = 100, max = 1000.1000)
			private Float sum = 10.10F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Double}.
	 */
	@Test
	public void validateWithDoubleTypeMinValue()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 0.0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Float}.
	 */
	@Test
	public void validateWithFloatTypeMinValue()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 0.0F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Double}.
	 */
	@Test
	public void validateWithDoubleTypeValue()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 785.785;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Float}.
	 */
	@Test
	public void validateWithFloatTypeValue()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 785.785F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Double}.
	 */
	@Test
	public void validateWithDoubleTypeMaxValue()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 1000.1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Float}.
	 */
	@Test
	public void validateWithFloatTypeMaxValue()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 1000.1000F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с значением больше максимального в поле типа {@code Double}.
	 */
	@Test
	public void validateWithDoubleTypeMoreMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 1164.1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением больше максимального в поле типа {@code Float}.
	 */
	@Test
	public void validateWithFloatTypeMoreMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 1164.1164F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}
}