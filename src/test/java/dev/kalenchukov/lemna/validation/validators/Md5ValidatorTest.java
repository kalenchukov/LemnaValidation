/*
 * Copyright © 2023 Алексей Каленчуков
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
import dev.kalenchukov.lemna.validation.constraints.Md5;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Класс проверки аннотации {@link Md5}.
 *
 * @author Алексей Каленчуков
 */
public class Md5ValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validNotCorrectFieldType()
	{
		class Experimental
		{
			@Md5
			private Integer md5 = 12345;
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
			@Md5
			private String md5 = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void validValueEmpty()
	{
		class Experimental
		{
			@Md5
			private String md5 = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с MD5-хеша с учётом регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValue()
	{
		class Experimental
		{
			@Md5(ignoreCase = false)
			private String md5 = "D41D8CD98F00B204E9800998ECF8427E";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с MD5-хеша без учёта регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueCorrectIgnoreCase()
	{
		class Experimental
		{
			@Md5(ignoreCase = true)
			private String md5 = "D41D8CD98f00B204e9800998ecF8427E";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением из-за запрета учёта регистра MD5-хеша в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueNotCorrectIgnoreCase()
	{
		class Experimental
		{
			@Md5(ignoreCase = false)
			private String md5 = "d41D8CD98f00B204e9800998ECF8427E";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}