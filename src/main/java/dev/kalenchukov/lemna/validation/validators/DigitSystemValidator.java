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
import dev.kalenchukov.lemna.validation.constraints.DigitSystem;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.numeralsystem.Numerable;
import dev.kalenchukov.string.formatting.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link DigitSystem}.
 */
public final class DigitSystemValidator extends AbstractValidator
{
	/**
	 * @see AbstractValidator#AbstractValidator(Locale)
	 */
	public DigitSystemValidator(@NotNull final Locale locale)
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

		DigitSystem constraint = field.getDeclaredAnnotation(DigitSystem.class);

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
	 * @param field Поле класса.
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля класса корректно, иначе {@code false}.
	 * @throws UnsupportedFieldTypeException Если тип поля класса не поддерживается данным ограничением.
	 */
	private boolean isValid(@NotNull final Field field, @NotNull final DigitSystem constraint, @Nullable final Object value)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(constraint);

		if (value == null) {
			return true;
		}

		if (value.getClass().equals(Character.class))
		{
			return this.isValidCharacter(constraint, (Character) value);
		}
		else if (value.getClass().equals(String.class))
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
	 * Проверка значения поля класса типа {@code Character}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidCharacter(@NotNull final DigitSystem constraint, @NotNull final Character value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		return this.isValidAbstract(constraint, value);
	}

	/**
	 * Проверка значения поля класса типа {@code String}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidString(@NotNull final DigitSystem constraint, @NotNull final String value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		if (value.isEmpty()) {
			return false;
		}

		for (Character character : value.toCharArray())
		{
			if (!this.isValidAbstract(constraint, character)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Проверка значения поля класса абстрактного типа.
	 * В качестве абстракции используется тип {@code Character}.
	 *
	 * @param constraint Проверяемое ограничение.
	 * @param value Значение поля класса.
	 * @return {@code True} если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidAbstract(@NotNull final DigitSystem constraint, @NotNull final Character value)
	{
		Numerable alphabet = constraint.numeralSystem().getNumeralSystem();

		if (!alphabet.contains(value))
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90016")
			));

			return false;
		}

		return true;
	}
}