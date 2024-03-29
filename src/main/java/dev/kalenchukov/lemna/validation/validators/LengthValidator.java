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
import dev.kalenchukov.lemna.validation.constraints.Length;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.Violation;
import dev.kalenchukov.string.formatting.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link Length}.
 *
 * @author Алексей Каленчуков
 */
public final class LengthValidator extends AbstractValidator
{
	/**
	 * Конструктор для {@code LengthValidator}.
	 * @param locale локализация.
	 * @throws NullPointerException если в качестве {@code locale} передан {@code null}.
	 */
	public LengthValidator(@NotNull final Locale locale)
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

		Length constraint = field.getDeclaredAnnotation(Length.class);

		boolean valid = this.isValid(field, constraint, value);

		if (!valid)
		{
			this.setParam("FIELD", field.getName());
			this.setParam("MIN", String.valueOf(constraint.min()));
			this.setParam("MAX", String.valueOf(constraint.max()));

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
							@NotNull final Length constraint,
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
	private boolean isValidString(@NotNull final Length constraint, @NotNull final String value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, value);
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется тип {@code String}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно, иначе {@code false}.
	 * @throws NullPointerException если в качестве {@code constraint} передан {@code null}.
	 * @throws NullPointerException если в качестве {@code value} передан {@code null}.
	 */
	private boolean isValidAbstract(@NotNull final Length constraint, @NotNull final String value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		if (value.length() < constraint.min())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90012")
			));

			return false;
		}

		if (value.length() > constraint.max())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90013")
			));

			return false;
		}

		return true;
	}
}
