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

package dev.kalenchukov.lemna.validation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Интерфейс для реализации класса нарушения.
 */
public interface Violating
{
	/**
	 * Возвращает название поля класса.
	 *
	 * @return Название поля класса.
	 */
	@NotNull
	String getField();

	/**
	 * Возвращает сообщение о нарушении.
	 *
	 * @return Сообщение о нарушении.
	 */
	@NotNull
	String getMessage();

	/**
	 * Возвращает параметры нарушения.
	 * <ul>
	 * 		<li><b>key</b> - название.</li>
	 * 		<li><b>value</b> - значение.</li>
	 * </ul>
	 * @return Коллекцию параметров нарушения.
	 */
	@UnmodifiableView
	@NotNull
	Map<@NotNull String, @NotNull String> getParams();
}
