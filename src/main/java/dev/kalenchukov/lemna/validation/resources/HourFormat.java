/*
 * Copyright 2022 Алексей Каленчуков
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
 * Перечисление форматов времени.
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
	@NotNull
	private final Integer minHour;

	/**
	 * Максимальное количество часов.
	 */
	@NotNull
	private final Integer maxHour;

	/**
	 * Конструктор для {@code HourFormat}.
	 *
	 * @param minHour Минимальное количество часов.
	 * @param maxHour Максимальное количество часов.
	 */
	HourFormat(@NotNull final Integer minHour, @NotNull final Integer maxHour)
	{
		Objects.requireNonNull(minHour);
		Objects.requireNonNull(maxHour);

		this.minHour = minHour;
		this.maxHour = maxHour;
	}

	/**
	 * Возвращает минимальное количество часов.
	 *
	 * @return Минимальное количество часов.
	 */
	@NotNull
	public Integer getMinHour()
	{
		return this.minHour;
	}

	/**
	 * Возвращает максимальное количество часов.
	 *
	 * @return Максимальное количество часов.
	 */
	@NotNull
	public Integer getMaxHour()
	{
		return this.maxHour;
	}
}
