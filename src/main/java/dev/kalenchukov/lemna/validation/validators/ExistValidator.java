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
import dev.kalenchukov.lemna.validation.constraints.Exist;
import dev.kalenchukov.lemna.validation.exceptions.InvalidExistenceClassException;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.interfaces.Existable;
import dev.kalenchukov.lemna.validation.Violation;
import dev.kalenchukov.string.formatting.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link Exist}.
 *
 * @author Aleksey Kalenchukov
 */
public final class ExistValidator extends AbstractValidator
{
	/**
	 * Конструктор для {@code ExistValidator}.
	 * @param locale локализация.
	 */
	public ExistValidator(@NotNull final Locale locale)
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

		Exist[] constraints = field.getDeclaredAnnotationsByType(Exist.class);

		for (Exist constraint : constraints)
		{
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
	 * @throws InvalidExistenceClassException если класс проверки существования некорректный.
	 */
	private boolean isValid(@NotNull final Field field, @NotNull final Exist constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		Class<? extends Existable<?>> existence = constraint.existence();

		try
		{
			Method method = existence.getMethod("exist", field.getType());

			boolean exists = (boolean) method.invoke(
				existence.getConstructor().newInstance(),
				value
			);

			if (!exists)
			{
				this.setMessage(StringFormat.format(
					constraint.message(),
					"DEFAULT_MESSAGE",
					this.localeViolations.getString("90004")
				));

				return false;
			}
		}
		catch (NoSuchMethodException exception)
		{
			throw new UnsupportedFieldTypeException(String.format(
				this.localeExceptions.getString("20004"),
				existence.getName()
			));
		}
		catch (Exception exception)
		{
			throw new InvalidExistenceClassException(String.format(
				this.localeExceptions.getString("20002"),
				existence.getName()
			));
		}

		return true;
	}
}
