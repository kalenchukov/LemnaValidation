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

package dev.kalenchukov.lemna.validation.constraints;

import dev.kalenchukov.lemna.validation.interfaces.Validable;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * Ограничение по корректности с помощью собственной реализации.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(Valid.ManyValid.class)
public @interface Valid
{
	/**
	 * Задаёт класс с собственной реализацией проверки корректности.
	 *
	 * @return класс с собственной реализацией проверки корректности.
	 */
	@NotNull
	Class<? extends Validable<?>> validator();

	/**
	 * Задаёт сообщение о нарушении.
	 * Переменные: <ul>
	 *     <li>{@code %FIELD%} - название поля класса</li>
	 * </ul>
	 * @return сообщение о нарушении.
	 */
	@NotNull
	String message() default "%DEFAULT_MESSAGE%";

	/**
	 * Ограничение множественным {@code Valid}.
	 */
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface ManyValid
	{
		/**
		 * Задаёт множество {@code Valid}.
		 *
		 * @return массив из {@code Valid}.
		 */
		@NotNull
		Valid @NotNull [] value();
	}
}
