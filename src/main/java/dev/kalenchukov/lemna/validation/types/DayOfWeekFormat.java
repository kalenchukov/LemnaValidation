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

package dev.kalenchukov.lemna.validation.types;

/**
 * Перечисление форматов дней недели.
 *
 * @author Алексей Каленчуков
 */
public enum DayOfWeekFormat
{
	/**
	 * От 1 (понедельник) до 7 (воскресенье).<br>
	 * Соответствует стандарту ISO 8601.
	 */
	ONE_TO_SEVEN(1, 7),

	/**
	 * От 0 (воскресенье) до 6 (суббота).
	 */
	ZERO_TO_SIX(0, 6);

	/**
	 * Первый день недели.
	 */
	private final int firstDay;

	/**
	 * Последний день недели.
	 */
	private final int lastDay;

	/**
	 * Конструктор для {@code DayOfWeekFormat}.
	 *
	 * @param firstDay первый день недели.
	 * @param lastDay последний день недели.
	 */
	DayOfWeekFormat(final int firstDay, final int lastDay)
	{
		this.firstDay = firstDay;
		this.lastDay = lastDay;
	}

	/**
	 * Возвращает первый день недели.
	 *
	 * @return первый день недели.
	 */
	public int getFirstDay()
	{
		return this.firstDay;
	}

	/**
	 * Возвращает последний день недели.
	 *
	 * @return последний день недели.
	 */
	public int getLastDay()
	{
		return this.lastDay;
	}
}
