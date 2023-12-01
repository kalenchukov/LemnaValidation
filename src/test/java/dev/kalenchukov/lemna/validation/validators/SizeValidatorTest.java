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

import dev.kalenchukov.lemna.validation.Validating;
import dev.kalenchukov.lemna.validation.Validation;
import dev.kalenchukov.lemna.validation.Violating;
import dev.kalenchukov.lemna.validation.constraints.Size;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * Класс проверки аннотации {@link Size}.
 *
 * @author Алексей Каленчуков
 */
public class SizeValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void validateWithFieldTypeInvalid()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private String days = "1, 2, 3";
		}

		assertThatExceptionOfType(UnsupportedFieldTypeException.class).isThrownBy(() -> {
			Validating validation = new Validation(new Experimental());
			validation.validate();
		});
	}

	/**
	 * Проверка с значением в виде {@code null}.
	 */
	@Test
	public void validateWithValueNull()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа массив.
	 */
	@Test
	public void validateWithArrayTypeValue()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1, 2, 3};
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Collection}.
	 */
	@Test
	public void validateWithCollectionTypeValue()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1, 2, 3));
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с полем типа {@code Map}.
	 */
	@Test
	public void validateWithMapTypeValue()
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

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка  без элементов в поле типа массив.
	 */
	@Test
	public void validateWithArrayTypeValueZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private Integer[] days = {};
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка  без элементов в поле типа {@code Collection}.
	 */
	@Test
	public void validateWithCollectionTypeValueZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private List<Integer> days = new ArrayList<>();
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка  без элементов в поле типа {@code Map}.
	 */
	@Test
	public void validateWithMapTypeValueZero()
	{
		class Experimental
		{
			@Size(min = 0, max = 4)
			private Map<Integer, Integer> days = new HashMap<>();
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(0);
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа массив.
	 */
	@Test
	public void validateWithArrayTypeMinValue()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1};
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа {@code Collection}.
	 */
	@Test
	public void validateWithCollectionTypeMinValue()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1));
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с меньшим количеством элементов в поле типа {@code Map}.
	 */
	@Test
	public void validateWithMapTypeMinValue()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Map<Integer, Integer> days = new HashMap<>(Map.ofEntries(
				Map.entry(0, 1)
			));
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с большим количеством элементов в поле типа массив.
	 */
	@Test
	public void validateWithArrayTypeMaxValue()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private Integer[] days = {1, 2, 3, 4, 5};
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с большим количеством элементов в поле типа {@code Collection}.
	 */
	@Test
	public void validateWithCollectionTypeMaxValue()
	{
		class Experimental
		{
			@Size(min = 2, max = 4)
			private List<Integer> days = new ArrayList<>(List.of(1, 2, 3, 4, 5));
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}

	/**
	 * Проверка с большим количеством элементов в поле типа {@code Map}.
	 */
	@Test
	public void validateWithMapTypeMaxValue()
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
			));
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		int actualSize = violation.size();

		assertThat(actualSize).isEqualTo(1);
	}
}