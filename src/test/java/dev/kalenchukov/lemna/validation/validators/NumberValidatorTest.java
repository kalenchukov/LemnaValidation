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
import dev.kalenchukov.lemna.validation.constraints.Number;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NumberValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private String sum = "13";
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
			@Number(min = 0, max = 1000)
			private Long sum = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Long sum = 10L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Integer sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 1000)
			private Short sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@Number(min = 100, max = 120)
			private Byte sum = 10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 0L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeValueCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 785L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeValueCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 785;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeValueCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 785;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeValueCorrect()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 98;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1000L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 120;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Long sum = 1164L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Integer sum = 1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 1000)
			private Short sum = 1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeMoreMax()
	{
		class Experimental
		{
			@Number(min = 0, max = 120)
			private Byte sum = 127;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}