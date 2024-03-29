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

package dev.kalenchukov.lemna.validation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Locale;

/**
 * Интерфейс для реализации класса по проверке корректности значений полей класса.
 *
 * @author Алексей Каленчуков
 */
public interface Validating
{
	/**
	 * Устанавливает локализацию.
	 *
	 * @param locale локализация.
	 */
	void setLocale(@NotNull Locale locale);

	/**
	 * Возвращает локализацию.
	 *
	 * @return локализация.
	 */
	@NotNull
	Locale getLocale();

	/**
	 * Определяет настырность проверки.
	 *
	 * @return {@code true}, если проверка настырная, иначе {@code false}.
	 */
	boolean isPushy();

	/**
	 * Устанавливает настырность проверки.
	 *
	 * @param pushy настырность.
	 */
	void setPushy(boolean pushy);

	/**
	 * Проверяет поля класса на корректность.
	 *
	 * @return коллекцию нарушений.
	 */
	@UnmodifiableView
	@NotNull
	List<@NotNull Violating> validate();
}
