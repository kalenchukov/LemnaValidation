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

package dev.kalenchukov.lemna.validation.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Интерфейс для реализации собственного класса для проверки корректности.
 *
 * @param <V> тип поля класса.
 * @author Алексей Каленчуков
 */
public interface Validable<V>
{
	/**
	 * Проверяет корректность значения.
	 *
	 * @param value значение поля класса.
	 * @return {@code true}, если {@code value} корректно, иначе {@code false}.
	 */
	boolean valid(@NotNull V value);
}
