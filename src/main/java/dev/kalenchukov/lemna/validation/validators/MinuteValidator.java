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

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.Violation;
import dev.kalenchukov.lemna.validation.constraints.Minute;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.string.formatting.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link Minute}.
 */
public final class MinuteValidator extends AbstractValidator
{
	/**
	 * Минимальное значение минут.
	 */
	@NotNull
	private static final Integer MIN_MINUTE = 0;

	/**
	 * Максимальное значение минут.
	 */
	@NotNull
	private static final Integer MAX_MINUTE = 59;

	/**
	 * @see AbstractValidator#AbstractValidator(Locale)
	 */
	public MinuteValidator(@NotNull final Locale locale)
	{
		super(Objects.requireNonNull(locale));
	}

	/**
	 * @see Validator#valid(Field, Object)
	 */
	@Nullable
	@Override
	public Violating valid(@NotNull final Field field, @Nullable final Object value)
	{
		Objects.requireNonNull(field);

		Minute constraint = field.getDeclaredAnnotation(Minute.class);

		boolean valid = this.isValid(field, constraint, value);

		if (!valid)
		{
			this.setParam("FIELD", field.getName());
			this.setParam("MIN", String.valueOf(MIN_MINUTE));
			this.setParam("MAX", String.valueOf(MAX_MINUTE));

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
	private boolean isValid(@NotNull final Field field, @NotNull final Minute constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().equals(Byte.class))
		{
			return this.isValidByte(constraint, (Byte) value);
		}
		else if (value.getClass().equals(Long.class))
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
	 * Проверка значения поля класса типа {@code Byte}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidByte(@NotNull final Minute constraint, @NotNull final Byte value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, Long.parseLong(String.valueOf(value)));
	}

	/**
	 * Проверка значения поля класса типа {@code Short}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidShort(@NotNull final Minute constraint, @NotNull final Short value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, Long.parseLong(String.valueOf(value)));
	}

	/**
	 * Проверка значения поля класса типа {@code Integer}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidInteger(@NotNull final Minute constraint, @NotNull final Integer value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, Long.parseLong(String.valueOf(value)));
	}

	/**
	 * Проверка значения поля класса типа {@code Long}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidLong(@NotNull final Minute constraint, @NotNull final Long value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, value);
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется тип {@code Long}.
	 *
	 * @param constraint проверяемое ограничение.
	 * @param value значение поля класса.
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidAbstract(@NotNull final Minute constraint, @NotNull final Long value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		if (value < MIN_MINUTE)
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90008")
			));

			return false;
		}

		if (value > MAX_MINUTE)
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90009")
			));

			return false;
		}

		return true;
	}
}
