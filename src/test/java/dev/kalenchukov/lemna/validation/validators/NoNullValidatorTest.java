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
import dev.kalenchukov.lemna.validation.constraints.NoNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Класс проверки аннотации {@link NoNull}.
 *
 * @author Алексей Каленчуков
 */
public class NoNullValidatorTest
{
	/**
	 * Проверка с корректным значением.
	 */
	@Test
	public void validateWithValue()
	{
		class Experimental
		{
			@NoNull
			private Long id = 1L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void validateWithValueInvalid()
	{
		class Experimental
		{
			@NoNull
			private Long id = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}
}