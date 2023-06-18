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

package dev.kalenchukov.lemna.validation.resources;

/**
 * Перечисление форматов времени.
 *
 * @author Алексей Каленчуков
 */
public enum HourFormat
{
	/**
	 * 12 часовой.
	 */
	HOUR_12(1, 12),

	/**
	 * 24-часовой.
	 */
	HOUR_24(0, 23);

	/**
	 * Минимальное количество часов.
	 */
	private final int minHour;

	/**
	 * Максимальное количество часов.
	 */
	private final int maxHour;

	/**
	 * Конструктор для {@code HourFormat}.
	 *
	 * @param minHour минимальное количество часов.
	 * @param maxHour максимальное количество часов.
	 */
	HourFormat(final int minHour, final int maxHour)
	{
		this.minHour = minHour;
		this.maxHour = maxHour;
	}

	/**
	 * Возвращает минимальное количество часов.
	 *
	 * @return минимальное количество часов.
	 */
	public int getMinHour()
	{
		return this.minHour;
	}

	/**
	 * Возвращает максимальное количество часов.
	 *
	 * @return максимальное количество часов.
	 */
	public int getMaxHour()
	{
		return this.maxHour;
	}
}
