/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.validation.validators;

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.NoNull;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class NoNullValidatorTest
{
	/**
	 * Проверка с корректным значением.
	 */
	@Test
	public void TestValidValueCorrect()
	{
		class Experimental
		{
			@NoNull
			private Long id = 1L;
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
			@NoNull
			private Long id = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}