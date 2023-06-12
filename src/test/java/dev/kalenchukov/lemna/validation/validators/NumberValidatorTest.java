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

import static org.junit.jupiter.api.Assertions.*;

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
	public void validNotCorrectFieldType()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private String sum = "13";
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
	public void validValueNull()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Long}.
	 */
	@Test
	public void validLongTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Long sum = 10L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Integer}.
	 */
	@Test
	public void validIntegerTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Integer sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Short}.
	 */
	@Test
	public void validShortTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Short sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Byte}.
	 */
	@Test
	public void validByteTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 120)
			private Byte sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void validLongTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 0L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void validIntegerTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void validShortTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void validByteTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с полем типа {@code Long}.
	 */
	@Test
	public void validLongTypeValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 785L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с полем типа {@code Integer}.
	 */
	@Test
	public void validIntegerTypeValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 785;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с полем типа {@code Short}.
	 */
	@Test
	public void validShortTypeValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 785;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с полем типа {@code Byte}.
	 */
	@Test
	public void validByteTypeValue()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 98;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void validLongTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1000L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void validIntegerTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void validShortTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void validByteTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 120;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Long}.
	 */
	@Test
	public void validLongTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1164L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Integer}.
	 */
	@Test
	public void validIntegerTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Short}.
	 */
	@Test
	public void validShortTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Byte}.
	 */
	@Test
	public void validByteTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 127;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}
}