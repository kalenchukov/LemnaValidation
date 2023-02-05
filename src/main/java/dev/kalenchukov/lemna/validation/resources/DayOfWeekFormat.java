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

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Перечисление форматов дней недели.
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
	@NotNull
	private final Integer firstDay;

	/**
	 * Последний день недели.
	 */
	@NotNull
	private final Integer lastDay;

	/**
	 * Конструктор для {@code DayOfWeekFormat}.
	 *
	 * @param firstDay первый день недели.
	 * @param lastDay последний день недели.
	 */
	DayOfWeekFormat(@NotNull final Integer firstDay, @NotNull final Integer lastDay)
	{
		Objects.requireNonNull(firstDay);
		Objects.requireNonNull(lastDay);

		this.firstDay = firstDay;
		this.lastDay = lastDay;
	}

	/**
	 * Возвращает первый день недели.
	 *
	 * @return первый день недели.
	 */
	@NotNull
	public Integer getFirstDay()
	{
		return this.firstDay;
	}

	/**
	 * Возвращает последний день недели.
	 *
	 * @return последний день недели.
	 */
	@NotNull
	public Integer getLastDay()
	{
		return this.lastDay;
	}
}
