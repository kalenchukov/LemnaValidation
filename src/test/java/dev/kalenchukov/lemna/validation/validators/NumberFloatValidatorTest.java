/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.NumberFloat;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NumberFloatValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private String sum = "13.13";
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
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@NumberFloat(min = 100, max = 1000.1000)
			private Double sum = 10.10;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением меньше минимального в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeValueNotCorrectLessMin()
	{
		class Experimental
		{
			@NumberFloat(min = 100, max = 1000.1000)
			private Float sum = 10.10F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 0.0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с минимальным значением в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeValueNotCorrectMin()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 0.0F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeValueCorrect()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 785.785;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeValueCorrect()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 785.785F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 1000.1000;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с максимальным значением в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeValueNotCorrectMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 1000.1000F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Double}.
	 */
	@Test
	public void TestValidDoubleTypeMoreMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Double sum = 1164.1164;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка со значением больше максимального в поле типа {@code Float}.
	 */
	@Test
	public void TestValidFloatTypeMoreMax()
	{
		class Experimental
		{
			@NumberFloat(min = 0.0, max = 1000.1000)
			private Float sum = 1164.1164F;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}