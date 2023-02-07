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

import dev.kalenchukov.alphabet.resources.Alphabet;
import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.LetterAlphabet;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки методов класса {@link LetterAlphabetValidator}.
 */
public class LetterAlphabetValidatorTest
{
	/**
	 * Проверка метода {@link Validation#validate()} с некорректным типом поля.
	 */
	@Test
	public void testValidNotCorrectFieldType()
	{
		class Experimental
		{
			@LetterAlphabet(alphabet = Alphabet.RUSSIAN)
			private Integer word = 12345;
		}

		assertThrows(UnsupportedFieldTypeException.class, () -> {
			Validating validation = new Validation(new Experimental());
			validation.validate();
		});
	}

	/**
	 * Проверка метода {@link Validation#validate()} со значением {@code null}.
	 */
	@Test
	public void testValidValueNull()
	{
		class Experimental
		{
			@LetterAlphabet(alphabet = Alphabet.RUSSIAN)
			private String word = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с пустым значением.
	 */
	@Test
	public void testValidValueNotCorrectEmpty()
	{
		class Experimental
		{
			@LetterAlphabet(alphabet = Alphabet.RUSSIAN)
			private String word = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с некорректным значением.
	 */
	@Test
	public void testValidValueNotCorrect()
	{
		class Experimental
		{
			@LetterAlphabet(alphabet = Alphabet.RUSSIAN)
			private Character word = 'W';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с корректным значением в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValue()
	{
		class Experimental
		{
			@LetterAlphabet(alphabet = Alphabet.RUSSIAN)
			private String word = "Печаль";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка метода {@link Validation#validate()} с корректным значением в поле типа {@code Character}.
	 */
	@Test
	public void testValidCharacterTypeValue()
	{
		class Experimental
		{
			@LetterAlphabet(alphabet = Alphabet.RUSSIAN)
			private Character word = 'Ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}
}