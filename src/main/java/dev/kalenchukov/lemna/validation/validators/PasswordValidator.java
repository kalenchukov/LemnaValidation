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
import dev.kalenchukov.lemna.validation.constraints.Password;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.string.formatting.StringFormat;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Класс проверяющего для ограничения {@link Password}.
 */
public final class PasswordValidator extends AbstractValidator
{
	/**
	 * @see AbstractValidator#AbstractValidator(Locale)
	 */
	public PasswordValidator(@NotNull final Locale locale)
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

		Password constraint = field.getDeclaredAnnotation(Password.class);

		boolean valid = this.isValid(field, constraint, value);

		if (!valid)
		{
			this.setParam("FIELD", field.getName());
			this.setParam("MIN_LETTERS", String.valueOf(constraint.minLetters()));
			this.setParam("MIN_DIGITS", String.valueOf(constraint.minDigits()));
			this.setParam("MIN_SPECIAL", String.valueOf(constraint.minSpecial()));

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
	private boolean isValid(@NotNull final Field field, @NotNull final Password constraint, @Nullable final Object value)
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
	 * @return {@code true}, если значение поля корректно, иначе {@code false}.
	 */
	private boolean isValidString(@NotNull final Password constraint, @NotNull final String value)
	{
		Objects.requireNonNull(constraint);
		Objects.requireNonNull(value);

		final List<Character> specialCharacters = List.of(
			'!', '@', '#', '$', '%', '^', '&', '*', '(', ')',
			'-', '_', '+', '=', ';', ':', ',', '\'', '.', '/', '?',
			'\\', '|', '`', '~', '[', ']', '{', '}', '"', '<', '>'
		);

		int letter = 0;
		int letterUpperCase = 0;
		int letterLowerCase = 0;
		int digit = 0;
		int specialCharacter = 0;

		for (Character symbol : value.toCharArray())
		{
			if (Character.isLetter(symbol)) {
				letter++;
			}

			if (Character.isDigit(symbol)) {
				digit++;
			}

			if (Character.isUpperCase(symbol)) {
				letterUpperCase++;
			}

			if (Character.isLowerCase(symbol)) {
				letterLowerCase++;
			}

			if (specialCharacters.contains(symbol)) {
				specialCharacter++;
			}
		}

		if (letter < constraint.minLetters())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90022")
			));

			return false;
		}

		if (constraint.mixedCase())
		{
			if (letterUpperCase == 0 || letterLowerCase == 0)
			{
				this.setMessage(StringFormat.format(
					constraint.message(),
					"DEFAULT_MESSAGE",
					this.localeViolations.getString("90023")
				));

				return false;
			}
		}

		if (digit < constraint.minDigits())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90021")
			));

			return false;
		}

		if (specialCharacter < constraint.minSpecial())
		{
			this.setMessage(StringFormat.format(
				constraint.message(),
				"DEFAULT_MESSAGE",
				this.localeViolations.getString("90020")
			));

			return false;
		}

		return true;
	}
}
