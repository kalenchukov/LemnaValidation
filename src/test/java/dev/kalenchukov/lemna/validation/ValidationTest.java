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

package dev.kalenchukov.lemna.validation;

import dev.kalenchukov.lemna.validation.constraints.Localization;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Класс проверки методов класса {@link Validation}.
 *
 * @author Алексей Каленчуков
 */
public class ValidationTest
{
	/**
	 * Проверка метода {@link Validation#setPushy(Boolean)} со значением {@code true}.
	 */
	@Test
	public void validatePushyTrue()
	{
		class Experimental
		{
			@Localization
			private String variable1 = "value";

			@Localization
			private String variable2 = "ru-RU";

			@Localization
			private String variable3 = "VALUE";
		}

		Validating validation = new Validation(new Experimental());
		validation.setPushy(true);
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(2);
	}

	/**
	 * Проверка метода {@link Validation#setPushy(Boolean)} со значением {@code false}.
	 */
	@Test
	public void validatePushyFalse()
	{
		class Experimental
		{
			@Localization
			private String variable1 = "ru-RU";

			@Localization
			private String variable2 = "value";

			@Localization
			private String variable3 = "VALUE";
		}

		Validating validation = new Validation(new Experimental());
		validation.setPushy(false);
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка метода {@link Validation#validate()} с собственным сообщением о нарушении.
	 */
	@Test
	public void validateMessage()
	{
		class Experimental
		{
			@Localization(message = "Некорректный формат локализации в поле класса '%FIELD%'")
			private String variable = "value";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		String actualMessage = violation.get(0).getMessage();

		assertThat(actualMessage).isEqualTo("Некорректный формат локализации в поле класса '%FIELD%'");
	}
}