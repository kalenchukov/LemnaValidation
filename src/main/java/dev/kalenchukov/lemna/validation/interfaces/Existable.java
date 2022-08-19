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

package dev.kalenchukov.lemna.validation.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для реализации собственного класса для проверки существования.
 *
 * @param <T> Объект типа поля класса.
 */
public interface Existable<T>
{
	/**
	 * Проверяет существование значения.
	 *
	 * @param value Значение поля класса.
	 * @return {@code True} если значение существует, иначе {@code false}.
	 */
	boolean exist(@NotNull T value);
}
