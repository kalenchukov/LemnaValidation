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
import dev.kalenchukov.lemna.validation.constraints.MacAddress;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки аннотации {@link MacAddress}.
 *
 * @author Алексей Каленчуков
 */
public class MacAddressValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validNotCorrectFieldType()
	{
		class Experimental
		{
			@MacAddress
			private Integer macAddress = 12345;
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
			@MacAddress
			private String macAddress = null;
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
			@MacAddress
			private String macAddress = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с MAC адресом с учётом регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValue()
	{
		class Experimental
		{
			@MacAddress(ignoreCase = false)
			private String macAddress = "00-EF-CD-EF-11-22";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с MAC адресом без учёта регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueCorrectIgnoreCase()
	{
		class Experimental
		{
			@MacAddress(ignoreCase = true)
			private String macAddress = "00-eF-cd-Ef-11-22";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением из-за запрета учёта регистра MAC адресов в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueNotCorrectIgnoreCase()
	{
		class Experimental
		{
			@MacAddress(ignoreCase = false)
			private String macAddress = "00-eF-cd-Ef-11-22";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}