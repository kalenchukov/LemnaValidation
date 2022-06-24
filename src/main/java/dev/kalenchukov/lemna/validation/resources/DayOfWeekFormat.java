/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
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
	 * @param firstDay Первый день недели.
	 * @param lastDay Последний день недели.
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
	 * @return Первый день недели.
	 */
	@NotNull
	public Integer getFirstDay()
	{
		return this.firstDay;
	}

	/**
	 * Возвращает последний день недели.
	 *
	 * @return Последний день недели.
	 */
	@NotNull
	public Integer getLastDay()
	{
		return this.lastDay;
	}
}
