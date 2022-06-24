/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
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
	public void TestValidNotCorrectFieldType()
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
	public void TestValidValueNull()
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
	public void TestValidArrayTypeValueCorrect()
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
	public void TestValidCollectionTypeValueCorrect()
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
	public void TestValidMapTypeValueCorrect()
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
	public void TestValidArrayTypeValueCorrectZero()
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
	public void TestValidCollectionTypeValueCorrectZero()
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
	public void TestValidMapTypeValueCorrectZero()
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
	public void TestValidArrayTypeValueNotCorrectMin()
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
	public void TestValidCollectionTypeValueNotCorrectMin()
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
	public void TestValidMapTypeValueNotCorrectMin()
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
	public void TestValidArrayTypeValueNotCorrectMax()
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
	public void TestValidCollectionTypeValueNotCorrectMax()
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
	public void TestValidMapTypeValueNotCorrectMax()
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