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
import dev.kalenchukov.lemna.validation.constraints.CountryCodeAlpha2;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки методов класса {@link CountryCodeAlpha2Validator}.
 *
 * @author Алексей Каленчуков
 */
public class CountryCodeAlpha2ValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validNotCorrectFieldType()
	{
		class Experimental
		{
			@CountryCodeAlpha2
			private Integer countryCode = 1;
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
			@CountryCodeAlpha2
			private String countryCode = null;
		}
		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void validValueEmpty()
	{
		class Experimental
		{
			@CountryCodeAlpha2
			private String countryCode = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void validValueNotCorrect()
	{
		class Experimental
		{
			@CountryCodeAlpha2
			private String countryCode = "Код страны";
		}
		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с полем типа {@code String}.
	 */
	@Test
	public void validStringTypeValue()
	{
		class Experimental
		{
			@CountryCodeAlpha2
			private String countryCode = "RU";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}
}