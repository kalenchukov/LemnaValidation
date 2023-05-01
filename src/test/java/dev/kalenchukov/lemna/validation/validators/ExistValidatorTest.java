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
import dev.kalenchukov.lemna.validation.constraints.Exist;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.supports.existences.GenderExistence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки аннотации {@link Exist}.
 *
 * @author Aleksey Kalenchukov
 */
public class ExistValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void testValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			private Integer gender = 1;
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
			@Exist(existence = GenderExistence.class)
			private String gender = null;
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
			@Exist(existence = GenderExistence.class)
			private String gender = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением поля.
	 */
	@Test
	public void testValidValueNotCorrect()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			private String gender = "ЗНАЧЕНИЕ";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка .
	 */
	@Test
	public void testValidValue()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			private String gender = "MALE";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с множественной проверкой.
	 */
	@Test
	public void testValidValueMany()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			@Exist(existence = GenderExistence.class)
			private String gender = "MALE";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}
}