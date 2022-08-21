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

import dev.kalenchukov.alphabet.resources.Alphabet;
import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Letter;
import dev.kalenchukov.lemna.validation.constraints.LetterAlphabet;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LetterValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Letter
			private Integer word = 12345;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void TestValidValueNull()
	{
		class Experimental
		{
			@Letter
			private String word = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void TestValidValueNotCorrectEmpty()
	{
		class Experimental
		{
			@Letter
			private String word = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void TestValidValueNotCorrect()
	{
		class Experimental
		{
			@Letter
			private Character word = '#';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeValueCorrect()
	{
		class Experimental
		{
			@Letter
			private String word = "Печаль";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeValueCorrect()
	{
		class Experimental
		{
			@Letter
			private Character word = 'Ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением прописного регистра в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeValueCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private String word = "МАЛЫШ";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением прописного регистра в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeValueCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private Character word = 'Ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением строчного регистра в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeValueCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private String word = "малыш";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением строчного регистра в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeValueCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private Character word = 'ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}


	/**
	 * Проверка с не корректным значением прописного регистра в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeValueNotCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private String word = "малыш";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с не корректным значением прописного регистра в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeValueNotCorrectUpperCase()
	{
		class Experimental
		{
			@Letter(lowerCase = false)
			private Character word = 'ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с не корректным значением строчного регистра в поле типа {@code String}.
	 */
	@Test
	public void TestValidStringTypeValueNotCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private String word = "МАЛЫШ";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с не корректным значением строчного регистра в поле типа {@code Character}.
	 */
	@Test
	public void TestValidCharacterTypeValueNotCorrectLowerCase()
	{
		class Experimental
		{
			@Letter(upperCase = false)
			private Character word = 'Ж';
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}