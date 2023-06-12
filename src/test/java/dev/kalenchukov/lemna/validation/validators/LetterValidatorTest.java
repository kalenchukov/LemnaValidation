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
import dev.kalenchukov.lemna.validation.constraints.Letter;
import dev.kalenchukov.lemna.validation.constraints.LetterAlphabet;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс проверки аннотации {@link Letter}.
 *
 * @author Алексей Каленчуков
 */
public class LetterValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validNotCorrectFieldType()
	{
		class Experimental
		{
			@Letter
			private Integer word = 12345;
		}

		assertThrows(UnsupportedFieldTypeException.class, () -> {
			Validating validation = new Validation(new Experimental());
			validation.validate();
		});
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void validValueNull()
	{
		class Experimental
		{
			@Letter
			private String word = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void validValueEmpty()
	{
		class Experimental
		{
			@Letter
			private String word = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void validValueNotCorrect()
	{
		class Experimental
		{
			@Letter
			private Character word = '#';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с полем типа {@code String}.
	 */
	@Test
	public void validStringTypeValue()
	{
		class Experimental
		{
			@Letter
			private String word = "Печаль";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с полем типа {@code Character}.
	 */
	@Test
	public void validCharacterTypeValue()
	{
		class Experimental
		{
			@Letter
			private Character word = 'Ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с буквами прописного регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private String word = "МАЛЫШ";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с буквами прописного регистра в поле типа {@code Character}.
	 */
	@Test
	public void validCharacterTypeValueCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private Character word = 'Ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с буквами строчного регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private String word = "малыш";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}

	/**
	 * Проверка с буквами строчного регистра в поле типа {@code Character}.
	 */
	@Test
	public void validCharacterTypeValueCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private Character word = 'ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(0, actualSize);
	}


	/**
	 * Проверка с некорректным значением прописного регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueNotCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private String word = "малыш";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с некорректным значением прописного регистра в поле типа {@code Character}.
	 */
	@Test
	public void validCharacterTypeValueNotCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private Character word = 'ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с некорректным значением строчного регистра в поле типа {@code String}.
	 */
	@Test
	public void validStringTypeValueNotCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private String word = "МАЛЫШ";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}

	/**
	 * Проверка с некорректным значением строчного регистра в поле типа {@code Character}.
	 */
	@Test
	public void validCharacterTypeValueNotCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private Character word = 'Ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		Integer actualSize = violation.size();

		assertEquals(1, actualSize);
	}
}