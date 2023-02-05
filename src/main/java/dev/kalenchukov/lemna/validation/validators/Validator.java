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

import dev.kalenchukov.lemna.validation.Violating;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Интерфейс для реализации проверяющего.
 */
public interface Validator
{
	/**
	 * Проверяет корректность значения поля класса.
	 *
	 * @param field поле класса.
	 * @param value значение поля класса.
	 * @return нарушение, или {@code null} если значение поля класса корректно.
	 */
	@Nullable
	Violating
	valid(@NotNull Field field, @Nullable Object value);

	/**
	 * Возвращает параметры нарушения.
	 *
	 * @return коллекцию параметров нарушения.
	 */
	@NotNull
	Map<@NotNull String, @NotNull String> getParams();

	/**
	 * Устанавливает параметр нарушения.
	 *
	 * @param key название параметра.
	 * @param value значение параметра.
	 */
	void setParam(@NotNull String key, @NotNull String value);

	/**
	 * Возвращает сообщение нарушения.
	 *
	 * @return сообщение нарушения.
	 */
	@NotNull
	String getMessage();

	/**
	 * Устанавливает сообщение нарушения.
	 *
	 * @param message сообщение нарушения.
	 */
	void setMessage(@NotNull String message);
}
