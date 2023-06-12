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
import dev.kalenchukov.lemna.validation.constraints.Password;
import dev.kalenchukov.lemna.validation.constraints.Pattern;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Класс проверки аннотации {@link Pattern}.
 *
 * @author Алексей Каленчуков
 */
public class PatternValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validNotCorrectFieldType()
	{
		class Experimental
		{
			@Pattern(regexp = "[0-9A-F]+")
			private Integer hexadecimal = 12345;
		}

		assertThatExceptionOfType(UnsupportedFieldTypeException.class).isThrownBy(() -> {
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
			@Pattern(regexp = "[0-9A-F]+")
			private String hexadecimal = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void validValueEmpty()
	{
		class Experimental
		{
			@Pattern(regexp = "[A-F]+")
			private String hexadecimal = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void validValueNotCorrect()
	{
		class Experimental
		{
			@Pattern(regexp = "[0-9A-F]")
			private Character hexadecimal = 'Z';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с полем типа {@code String}.
	 */
	@Test
	public void validStringTypeValue()
	{
		class Experimental
		{
			@Pattern(regexp = "[0-9A-F]+")
			private String hexadecimal = "08A6D9";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Character}.
	 */
	@Test
	public void validCharacterTypeValue()
	{
		class Experimental
		{
			@Pattern(regexp = "[0-9A-F]")
			private Character hexadecimal = 'C';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}
}