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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ограничение по требованиям к паролю.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Password
{
	/**
	 * Задаёт минимальное количество букв.
	 *
	 * @return Минимальное количество букв.
	 */
	int minLetters() default 11;

	/**
	 * Задаёт смешанный регистр букв.
	 *
	 * @return {@code True}, если необходимы буквы разных регистров, иначе {@code false}..
	 */
	boolean mixedCase() default true;

	/**
	 * Задаёт минимальное количество цифр.
	 *
	 * @return Минимальное количество цифр.
	 */
	int minDigits() default 1;

	/**
	 * Задаёт минимальное количество специальных символов.
	 * <p>
	 * Специальными символами являются:
	 * <blockquote><pre>
	 * '&#33;', '&#64;', '&#35;', '&#36;', '&#37;', '&#94;', '&#38;',
	 * '&#42;', '&#40;', '&#41;', '&#45;', '&#95;', '&#43;', '&#61;',
	 * '&#59;', '&#58;', '&#44;', '&#39;', '&#46;', '&#47;', '&#63;',
	 * '&#92;', '&#124;', '&#96;', '&#126;', '&#91;', '&#93;', '&#123;',
	 * '&#125;', '&#34;', '&#60;', '&#62;'
	 * </pre></blockquote>
	 * </p>
	 * @return Минимальное количество специальных символов.
	 */
	int minSpecial() default 0;

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
}
