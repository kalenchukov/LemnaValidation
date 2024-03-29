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

import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.Violation;
import dev.kalenchukov.lemna.validation.constraints.InetAddress;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.string.formatting.StringFormat;
import dev.kalenchukov.string.regexp.StringRegexp;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link InetAddress}.
 *
 * @author Алексей Каленчуков
 */
public final class InetAddressValidator extends AbstractValidator
{
	/**
	 * Конструктор для {@code InetAddressValidator}.
	 * @param locale локализация.
	 * @throws NullPointerException если в качестве {@code locale} передан {@code null}.
	 */
	public InetAddressValidator(@NotNull final Locale locale)
	{
		super(Objects.requireNonNull(locale));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param field {@inheritDoc}
	 * @param value {@inheritDoc}
	 * @return {@inheritDoc}
	 * @throws NullPointerException если в качестве {@code field} передан {@code null}.
	 */
	@Nullable
	@Override
	public Violating valid(@NotNull final Field field, @Nullable final Object value)
	{
		Objects.requireNonNull(field);

		InetAddress constraint = field.getDeclaredAnnotation(InetAddress.class);

		boolean valid = this.isValid(field, constraint, value);

		if (!valid)
		{
			this.setParam("FIELD", field.getName());

			return new Violation(
				field.getName(),
				this.getMessage(),
				this.getParams()
			);
		}

		return null;
	}

	/**
	 * Проверяет корректность значения поля класса.
	 *
	 * @param field поле класса.
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно в {@code field}, иначе {@code false}.
	 * @throws NullPointerException если в качестве {@code field} передан {@code null}.
	 * @throws NullPointerException если в качестве {@code constraint} передан {@code null}.
	 * @throws UnsupportedFieldTypeException если тип {@code field} не поддерживается данным ограничением.
	 */
	private boolean isValid(@NotNull final Field field,
							@NotNull final InetAddress constraint,
							@Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().equals(String.class))
		{
			return this.isValidString(constraint, (String) value);
		}
		else
		{
			throw new UnsupportedFieldTypeException(String.format(
				this.localeExceptions.getString("20001"),
				constraint.getClass().getSimpleName()
			));
		}
	}

	/**
	 * Проверка значения поля класса типа {@code String}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно, иначе {@code false}.
	 * @throws NullPointerException если в качестве {@code constraint} передан {@code null}.
	 * @throws NullPointerException если в качестве {@code value} передан {@code null}.
	 */
	private boolean isValidString(@NotNull final InetAddress constraint, @NotNull final String value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		if (constraint.v4() && StringRegexp.isInet4Address(value)) {
			return true;
		}

		if (constraint.v6() && StringRegexp.isInet6Address(value)) {
			return true;
		}

		this.setMessage(StringFormat.format(
			constraint.message(),
			"DEFAULT_MESSAGE",
			this.localeViolations.getString("90024")
		));

		return false;
	}
}
