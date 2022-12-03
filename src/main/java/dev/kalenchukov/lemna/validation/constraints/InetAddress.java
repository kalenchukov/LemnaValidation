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

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * Ограничение по IP адресу.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface InetAddress
{
	/**
	 * Задаёт разрешение на IP адреса четвёртой версии.
	 *
	 * @return {@code true}, IP адреса четвёртой версии разрешены, иначе {@code false}.
	 */
	boolean v4() default true;

	/**
	 * Задаёт разрешение на IP адреса шестой версии.
	 *
	 * @return {@code true}, IP адреса шестой версии разрешены, иначе {@code false}.
	 */
	boolean v6() default true;

	/**
	 * Задаёт сообщение о нарушении.
	 * Переменные: <ul>
	 *     <li>{@code %FIELD%} - название поля класса</li>
	 * </ul>
	 *
	 * @return сообщение о нарушении.
	 */
	@NotNull
	String message() default "%DEFAULT_MESSAGE%";
}
