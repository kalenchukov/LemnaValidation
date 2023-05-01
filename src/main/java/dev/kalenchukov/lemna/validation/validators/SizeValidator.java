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
import dev.kalenchukov.lemna.validation.constraints.Size;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.Violation;
import dev.kalenchukov.string.formatting.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс проверяющего для ограничения {@link Size}.
 *
 * @author Aleksey Kalenchukov
 */
public final class SizeValidator extends AbstractValidator
{
	/**
	 * Конструктор для {@code SizeValidator}.
	 * @param locale локализация.
	 */
	public SizeValidator(@NotNull final Locale locale)
	{
		super(Objects.requireNonNull(locale));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param field {@inheritDoc}
	 * @param value {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Nullable
	@Override
	public Violating valid(@NotNull final Field field, @Nullable final Object value)
	{
		Objects.requireNonNull(field);

		Size constraint = field.getDeclaredAnnotation(Size.class);

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
	 * @return {@code true}, если значение поля класса корректно, иначе {@code false}.
	 * @throws UnsupportedFieldTypeException если тип поля класса не поддерживается данным ограничением.
	 */
	private boolean isValid(@NotNull final Field field, @NotNull final Size constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().isArray())
		{
			return this.isValidArray(constraint, (Object) value);
		}
		else if (value instanceof Collection<?>)
		{
			return this.isValidCollection(constraint, (Collection<?>) value);
		}
		else if (value instanceof Map<?, ?>)
		{
			return this.isValidMap(constraint, (Map<?, ?>) value);
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
	 * Проверка значения поля класса типа {@code Map}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidMap(@NotNull final Size constraint, @NotNull final Map<?, ?> value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, value.size());
	}

	/**
	 * Проверка значения поля класса типа {@code Collection}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidCollection(@NotNull final Size constraint, @NotNull final Collection<?> value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, value.size());
	}

	/**
	 * Проверка значения поля класса типа массив.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidArray(@NotNull final Size constraint, @NotNull final Object value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, Array.getLength(value));
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется количество элементов.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param length количество элементов значения поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidAbstract(@NotNull final Size constraint, @NotNull final Integer length)
	{
		Objects.requireNonNull(constraint);

		if (length < constraint.min())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90005")
			));

			return false;
		}

		if (length > constraint.max())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90006")
			));

			return false;
		}

		return true;
	}
}
