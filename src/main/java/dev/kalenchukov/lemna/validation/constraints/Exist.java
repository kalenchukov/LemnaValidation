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

import dev.kalenchukov.lemna.validation.interfaces.Existable;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * Ограничение по существованию с помощью собственной реализации.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(Exist.ManyExist.class)
public @interface Exist
{
	/**
	 * Задаёт класс с собственной реализацией проверки существования.
	 *
	 * @return Класс с собственной реализацией проверки существования.
	 */
	@NotNull
	Class<? extends Existable<?>> existence();

	/**
	 * Задаёт сообщение о нарушении.
	 * Переменные: <ul>
	 *     <li>{@code %FIELD%} - название поля класса</li>
	 * </ul>
	 *
	 * @return Сообщение о нарушении.
	 */
	@NotNull
	String message() default "%DEFAULT_MESSAGE%";

	/**
	 * Ограничение множественным {@code Exist}.
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface ManyExist
	{
		/**
		 * Задаёт множество {@code Exist}.
		 *
		 * @return Массив из {@code Exist}.
		 */
		@NotNull
		Exist @NotNull [] value();
	}
}
