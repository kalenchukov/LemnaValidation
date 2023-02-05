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
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ValidationTest
{
	/**
	 * Проверка настырности на примере ограничения {@link Localization}.
	 */
	@Test
	public void testValidatePushyTrue()
	{
		class Experimental
		{
			@Localization
			private String localization1 = "value";

			@Localization
			private String localization2 = "ru-RU";

			@Localization
			private String localization3 = "VALUE";
		}

		Validating validation = new Validation(new Experimental());
		validation.setPushy(true);
		List<Violating> violation = validation.validate();

		assertEquals(2, violation.size());
	}

	/**
	 * Проверка не настырности на примере ограничения {@link Localization}.
	 */
	@Test
	public void testValidatePushyFalse()
	{
		class Experimental
		{
			@Localization
			private String localization1 = "ru-RU";

			@Localization
			private String localization2 = "value";

			@Localization
			private String localization3 = "VALUE";
		}

		Validating validation = new Validation(new Experimental());
		validation.setPushy(false);
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка собственного сообщения о нарушении.
	 */
	@Test
	public void testValidateMessage()
	{
		class Experimental
		{
			@Localization(message = "Некорректный формат локализации в поле класса '%FIELD%'")
			private String localization = "value";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals("Некорректный формат локализации в поле класса '%FIELD%'", violation.get(0).getMessage());
	}
}