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
import dev.kalenchukov.lemna.validation.constraints.InetAddress;
import dev.kalenchukov.lemna.validation.exceptions.UnsupportedFieldTypeException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InetAddressValidatorTest
{
	/**
	 * Проверка с некорректным типом поля.
	 */
	@Test
	public void testValidNotCorrectFieldType()
	{
		class Experimental
		{
			@InetAddress
			private Integer inetAddress = 12345;
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
	public void testValidValueNull()
	{
		class Experimental
		{
			@InetAddress
			private String inetAddress = null;
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с пустым значением.
	 */
	@Test
	public void testValidValueNotCorrectEmpty()
	{
		class Experimental
		{
			@InetAddress
			private String inetAddress = "";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с корректным значением IP адреса четвёртой версии в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueCorrectVersion4()
	{
		class Experimental
		{
			@InetAddress(v6 = false)
			private String inetAddress = "192.168.1.1";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с корректным значением IP адреса шестой версии в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueCorrectVersion6()
	{
		class Experimental
		{
			@InetAddress(v4 = false)
			private String inetAddress = "2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(0, violation.size());
	}

	/**
	 * Проверка с некорректным значением из-за запрета четвёртой версии IP адресов в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectVersion4()
	{
		class Experimental
		{
			@InetAddress(v4 = false)
			private String inetAddress = "192.168.1.1";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением из-за запрета шестой версии IP адресов в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectVersion6()
	{
		class Experimental
		{
			@InetAddress(v6 = false)
			private String inetAddress = "2001:0DB8:11A3:09D7:1F34:8A2E:07A0:765D";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}

	/**
	 * Проверка с некорректным значением из-за запрета четвёртой и шестой версии IP адресов в поле типа {@code String}.
	 */
	@Test
	public void testValidStringTypeValueNotCorrectVersion4And6()
	{
		class Experimental
		{
			@InetAddress(v4 = false, v6 = false)
			private String inetAddress = "192.168.1.1";
		}

		Validating validation = new Validation(new Experimental());
		List<Violating> violation = validation.validate();

		assertEquals(1, violation.size());
	}
}