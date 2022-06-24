/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Exist;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import dev.kalenchukov.lemna.validation.supports.existences.GenderExistence;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExistValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test(expected = UnsupportedFieldTypeException.class)
	public void TestValidNotCorrectFieldType()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			private Integer gender = 1;
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
			@Exist(existence = GenderExistence.class)
			private String gender = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением поля.
	 */
	@Test
	public void TestValidValueNotCorrect()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			private String gender = "ЗНАЧЕНИЕ";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с корректным значением поля.
	 */
	@Test
	public void TestValidValueCorrect()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			private String gender = "MALE";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с множественной проверкой и корректным значением поля.
	 */
	@Test
	public void TestValidValueCorrectMany()
	{
		class Experimental
		{
			@Exist(existence = GenderExistence.class)
			@Exist(existence = GenderExistence.class)
			private String gender = "MALE";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}
}