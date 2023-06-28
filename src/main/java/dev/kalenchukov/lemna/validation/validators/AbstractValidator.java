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

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Класс абстрактного проверяющего.
 *
 * @author Алексей Каленчуков
 */
public abstract class AbstractValidator implements Validator
{
	/**
	 * Локализация.
	 */
	@NotNull
	protected Locale locale = new Locale("ru", "RU");

	/**
	 * Локализованные тексты логирования.
	 */
	@NotNull
	protected ResourceBundle localeLogs = ResourceBundle.getBundle(
		"lemna/validation/localizations/logs",
		this.locale
	);

	/**
	 * Локализованные тексты нарушений.
	 */
	@NotNull
	protected ResourceBundle localeViolations = ResourceBundle.getBundle(
		"lemna/validation/localizations/violations",
		this.locale
	);

	/**
	 * Локализованные тексты исключений.
	 */
	@NotNull
	protected ResourceBundle localeExceptions = ResourceBundle.getBundle(
		"lemna/validation/localizations/exceptions",
		this.locale
	);

	/**
	 * Сообщение нарушения.
	 */
	@NotNull
	protected String message = "";

	/**
	 * Параметры нарушения.
	 */
	@NotNull
	protected Map<@NotNull String, @NotNull String> params = new HashMap<>();

	/**
	 * Конструктор для {@code AbstractValidator}.
	 *
	 * @param locale локализация.
	 * @throws NullPointerException если в качестве {@code locale} передан {@code null}.
	 */
	protected AbstractValidator(@NotNull final Locale locale)
	{
		Objects.requireNonNull(locale);

		if (!this.locale.equals(locale))
		{
			this.locale = locale;

			this.localeLogs = ResourceBundle.getBundle(
				"lemna/validation/localizations/logs",
				this.locale
			);

			this.localeViolations = ResourceBundle.getBundle(
				"lemna/validation/localizations/violations",
				this.locale
			);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@NotNull
	@Override
	public Map<@NotNull String, @NotNull String> getParams()
	{
		return this.params;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param key {@inheritDoc}
	 * @param value {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws NullPointerException если в качестве {@code key} передан {@code null}.
	 * @throws NullPointerException если в качестве {@code value} передан {@code null}.
	 */
	@Override
	public void setParam(@NotNull final String key, @NotNull final String value)
	{
		Objects.requireNonNull(key);
		Objects.requireNonNull(value);

		this.params.put(key, value);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@NotNull
	@Override
	public String getMessage()
	{
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param message {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws NullPointerException если в качестве {@code message} передан {@code null}.
	 */
	@Override
	public void setMessage(@NotNull final String message)
	{
		Objects.requireNonNull(message);

		this.message = message;
	}
}
