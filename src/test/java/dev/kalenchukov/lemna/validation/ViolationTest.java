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

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки методов класса {@link Violation}.
 *
 * @author Алексей Каленчуков
 */
public class ViolationTest
{
	/**
	 * Проверка метода {@link Violation#equals(Object)} с одинаковыми объектами.
	 */
	@Test
	public void testEquals()
	{
		Map<String, String> params = new HashMap<>();
		params.put("%FIELD%", "id");

		Violating violation1 = new Violation("id", "Сообщение", params);
		Violating violation2 = new Violation("id", "Сообщение", params);

		assertEquals(violation1, violation2);
	}

	/**
	 * Проверка метода {@link Violation#equals(Object)} с разными объектами.
	 */
	@Test
	public void equalsNotCorrect()
	{
		Map<String, String> params = new HashMap<>();
		params.put("%FIELD%", "id");

		Violating violation1 = new Violation("id", "Сообщение", params);
		Violating violation2 = new Violation("name", "Сообщение", params);

		assertNotEquals(violation1, violation2);
	}

	/**
	 * Проверка метода {@link Violation#hashCode()} с одинаковыми объектами.
	 */
	@Test
	public void hashCodeEquals()
	{
		Map<String, String> params = new HashMap<>();
		params.put("%FIELD%", "id");

		Violating violation1 = new Violation("id", "Сообщение", params);
		Violating violation2 = new Violation("id", "Сообщение", params);

		assertEquals(violation1.hashCode(), violation2.hashCode());
	}

	/**
	 * Проверка метода {@link Violation#hashCode()} с разными объектами.
	 */
	@Test
	public void hashCodeDifferent()
	{
		Map<String, String> params = new HashMap<>();
		params.put("%FIELD%", "id");

		Violating violation1 = new Violation("id", "Сообщение", params);
		Violating violation2 = new Violation("name", "Сообщение", params);

		assertNotEquals(violation1.hashCode(), violation2.hashCode());
	}
}