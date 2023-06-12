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
import dev.kalenchukov.lemna.validation.constraints.DayOfWeek;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.resources.DayOfWeekFormat;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки аннотации {@link DayOfWeek}.
 *
 * @author Алексей Каленчуков
 */
public class DayOfWeekValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validNotCorrectFieldType()
	{
		class Experimental
		{
			@DayOfWeek
			private String dayOfWeek = "1";
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
			@DayOfWeek
			private Integer dayOfWeek = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void validValueNotCorrect()
	{
		class Experimental
		{
			@DayOfWeek
			private Integer dayOfWeek = 20;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с некорректным значением по выбранному формату.
	 */
	@Test
	public void validValueNotCorrectFormat1()
	{
		class Experimental
		{
			@DayOfWeek(format = DayOfWeekFormat.ONE_TO_SEVEN)
			private Integer dayOfWeek = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с некорректным значением по выбранному формату.
	 */
	@Test
	public void validValueNotCorrectFormat2()
	{
		class Experimental
		{
			@DayOfWeek(format = DayOfWeekFormat.ZERO_TO_SIX)
			private Integer dayOfWeek = 7;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с полем типа {@code Byte}.
	 */
	@Test
	public void validByteTypeValue()
	{
		class Experimental
		{
			@DayOfWeek
			private Byte dayOfWeek = 4;
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
			@DayOfWeek
			private Short dayOfWeek = 4;
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
			@DayOfWeek
			private Integer dayOfWeek = 4;
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
			@DayOfWeek
			private Long dayOfWeek = 4L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}
}