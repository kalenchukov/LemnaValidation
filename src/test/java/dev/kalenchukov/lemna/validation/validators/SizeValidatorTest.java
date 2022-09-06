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

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Size;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SizeValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void testValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private String days = "1, 2, 3";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();
	}

	/**
	 * Проверка со значением {@code null}.
	 */
	@Test
	public void testValidValueNull()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа массив.
	 */
	@Test
	public void testValidArrayTypeValueCorrect()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1, 2, 3};
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Collection}.
	 */
	@Test
	public void testValidCollectionTypeValueCorrect()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1, 2, 3));
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Map}.
	 */
	@Test
	public void testValidMapTypeValueCorrect()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Map<Integer, Integer> days = new HashMap<>(Map.ofEntries(
				Map.entry(0, 1),
				Map.entry(1, 2),
				Map.entry(2, 3)
			));
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка без элементов в поле типа массив.
	 */
	@Test
	public void testValidArrayTypeValueCorrectZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private Integer[] days = {};
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка без элементов в поле типа {@code Collection}.
	 */
	@Test
	public void testValidCollectionTypeValueCorrectZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private List<Integer> days = new ArrayList<>();
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка без элементов в поле типа {@code Map}.
	 */
	@Test
	public void testValidMapTypeValueCorrectZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private Map<Integer, Integer> days = new HashMap<>();
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа массив.
	 */
	@Test
	public void testValidArrayTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1}; // 1 элемент
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа {@code Collection}.
	 */
	@Test
	public void testValidCollectionTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1)); // 1 элемент
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа {@code Map}.
	 */
	@Test
	public void testValidMapTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Map<Integer, Integer> days = new HashMap<>(Map.ofEntries(
				Map.entry(0, 1)
			)); // 1 элемент
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с большим количеством элементов в поле типа массив.
	 */
	@Test
	public void testValidArrayTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1, 2, 3, 4, 5}; // 5 элементов
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с большим количеством элементов в поле типа {@code Collection}.
	 */
	@Test
	public void testValidCollectionTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1, 2, 3, 4, 5)); // 5 элементов
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с большим количеством элементов в поле типа {@code Map}.
	 */
	@Test
	public void testValidMapTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Map<Integer, Integer> days = new HashMap<>(Map.ofEntries(
				Map.entry(0, 1),
				Map.entry(1, 2),
				Map.entry(2, 3),
				Map.entry(3, 4),
				Map.entry(4, 5)
			)); // 5 элементов
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}