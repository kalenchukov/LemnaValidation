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
import dev.kalenchukov.lemna.validation.constraints.Id;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.string.formatting.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link Id}.
 *
 * @author Алексей Каленчуков
 */
public final class IdValidator extends AbstractValidator
{
	/**
	 * Минимальный идентификатор.
	 */
	private static final long MIN_ID = 1L;

	/**
	 * Максимальный идентификатор.
	 */
	private static final long MAX_ID = Long.MAX_VALUE;

	/**
	 * Конструктор для {@code IdValidator}.
	 * @param locale локализация.
	 */
	public IdValidator(@NotNull final Locale locale)
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

		Id constraint = field.getDeclaredAnnotation(Id.class);

		boolean valid = this.isValid(field, constraint, value);

		if (!valid)
		{
			this.setParam("FIELD", field.getName());
			this.setParam("MIN", String.valueOf(MIN_ID));
			this.setParam("MAX", String.valueOf(MAX_ID));

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
	 * @throws UnsupportedFieldTypeException если тип {@code field} не поддерживается данным ограничением.
	 */
	private boolean isValid(@NotNull final Field field,
							@NotNull final Id constraint,
							@Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().equals(Long.class))
		{
			return this.isValidLong(constraint, (Long) value);
		}
		else if (value.getClass().equals(Integer.class))
		{
			return this.isValidInteger(constraint, (Integer) value);
		}
		else if (value.getClass().equals(Short.class))
		{
			return this.isValidShort(constraint, (Short) value);
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
	 * Проверка значения поля класса типа {@code Short}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно, иначе {@code false}.
	 */
	private boolean isValidShort(@NotNull final Id constraint, final short value)
	{
		Objects.requireNonNull(constraint);

		return this.isValidAbstract(constraint, Long.parseLong(String.valueOf(value)));
	}

	/**
	 * Проверка значения поля класса типа {@code Integer}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно, иначе {@code false}.
	 */
	private boolean isValidInteger(@NotNull final Id constraint, final int value)
	{
		Objects.requireNonNull(constraint);

		return this.isValidAbstract(constraint, Long.parseLong(String.valueOf(value)));
	}

	/**
	 * Проверка значения поля класса типа {@code Long}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно, иначе {@code false}.
	 */
	private boolean isValidLong(@NotNull final Id constraint, final long value)
	{
		Objects.requireNonNull(constraint);

		return this.isValidAbstract(constraint, value);
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется тип {@code Long}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно, иначе {@code false}.
	 */
	private boolean isValidAbstract(@NotNull final Id constraint, final long value)
	{
		Objects.requireNonNull(constraint);

		if (value < MIN_ID)
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90007")
			));

			return false;
		}

		return true;
	}
}
