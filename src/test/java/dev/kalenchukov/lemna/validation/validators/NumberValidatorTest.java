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
import dev.kalenchukov.lemna.validation.constraints.Number;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Класс проверки аннотации {@link Number}.
 *
 * @author Алексей Каленчуков
 */
public class NumberValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validateWithFieldTypeInvalid()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private String sum = "13";
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
			@Number(min = 0, max = 1000)
			private Long sum = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с значением меньше минимального в поле типа {@code Long}.
	 */
	@Test
	public void validateWithLongTypeLessMinValue()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Long sum = 10L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением меньше минимального в поле типа {@code Integer}.
	 */
	@Test
	public void validateWithIntegerTypeLessMinValue()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Integer sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением меньше минимального в поле типа {@code Short}.
	 */
	@Test
	public void validateWithShortTypeLessMinValue()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Short sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением меньше минимального в поле типа {@code Byte}.
	 */
	@Test
	public void validateWithByteTypeLessMinValue()
	{
		class Experimental
		{
			@Number(min = 100, max = 120)
			private Byte sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void validateWithLongTypeMinValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 0L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void validateWithIntegerTypeMinValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void validateWithShortTypeMinValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void validateWithByteTypeMinValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 0;
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
			@Number(min = 0, max = 1000)
			private Long sum = 785L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Integer}.
	 */
	@Test
	public void validateWithIntegerTypeValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 785;
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
			@Number(min = 0, max = 1000)
			private Short sum = 785;
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
			@Number(min = 0, max = 120)
			private Byte sum = 98;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void validateWithLongTypeMaxValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1000L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void validateWithIntegerTypeMaxValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void validateWithShortTypeMaxValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void validateWithByteTypeMaxValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 120;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с значением больше максимального в поле типа {@code Long}.
	 */
	@Test
	public void validateWithLongTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1164L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением больше максимального в поле типа {@code Integer}.
	 */
	@Test
	public void validateWithIntegerTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением больше максимального в поле типа {@code Short}.
	 */
	@Test
	public void validateWithShortTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с значением больше максимального в поле типа {@code Byte}.
	 */
	@Test
	public void validateWithByteTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 127;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}
}