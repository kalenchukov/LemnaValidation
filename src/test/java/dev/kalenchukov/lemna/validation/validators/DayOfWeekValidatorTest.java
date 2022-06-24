/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.DayOfWeek;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.resources.DayOfWeekFormat;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DayOfWeekValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@DayOfWeek
			private String dayOfWeek = "1";
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
			@DayOfWeek
			private Integer dayOfWeek = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением.
	 */
	@Test
	public void TestValidValueNotCorrect()
	{
		class Experimental
		{
			@DayOfWeek
			private Integer dayOfWeek = 20;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением по выбранному формату.
	 */
	@Test
	public void TestValidValueNotCorrectFormat1()
	{
		class Experimental
		{
			@DayOfWeek(format = DayOfWeekFormat.ONE_TO_SEVEN)
			private Integer dayOfWeek = 0;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением по выбранному формату.
	 */
	@Test
	public void TestValidValueNotCorrectFormat2()
	{
		class Experimental
		{
			@DayOfWeek(format = DayOfWeekFormat.ZERO_TO_SIX)
			private Integer dayOfWeek = 7;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Byte}.
	 */
	@Test
	public void TestValidByteTypeValueCorrect()
	{
		class Experimental
		{
			@DayOfWeek
			private Byte dayOfWeek = 4;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Short}.
	 */
	@Test
	public void TestValidShortTypeValueCorrect()
	{
		class Experimental
		{
			@DayOfWeek
			private Short dayOfWeek = 4;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Integer}.
	 */
	@Test
	public void TestValidIntegerTypeValueCorrect()
	{
		class Experimental
		{
			@DayOfWeek
			private Integer dayOfWeek = 4;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением в поле типа {@code Long}.
	 */
	@Test
	public void TestValidLongTypeValueCorrect()
	{
		class Experimental
		{
			@DayOfWeek
			private Long dayOfWeek = 4L;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}
}