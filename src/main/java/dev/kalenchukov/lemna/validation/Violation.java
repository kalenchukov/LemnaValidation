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
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Класс нарушения.
 *
 * @author Алексей Каленчуков
 */
public final class Violation implements Violating
{
	/**
	 * Название поля класса.
	 */
	@NotNull
	private final String field;

	/**
	 * Сообщение о нарушении.
	 */
	@NotNull
	private final String message;

	/**
	 * Параметры нарушения.
	 */
	@NotNull
	private final Map<@NotNull String, @NotNull String> params;

	/**
	 * Конструктор для {@code Violation}.
	 *
	 * @param field название поля класса.
	 * @param message сообщение о нарушении.
	 * @param params параметры нарушения.
	 * <ul>
	 * 		<li><b>key</b> - название.</li>
	 * 		<li><b>value</b> - значение.</li>
	 * </ul>
	 */
	public Violation(@NotNull final String field,
					 @NotNull final String message,
					 @NotNull final Map<@NotNull String, @NotNull String> params)
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(message);
		Objects.requireNonNull(params);

		this.field = field;
		this.message = message;
		this.params = new HashMap<>(params);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@NotNull
	public String getField()
	{
		return this.field;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@NotNull
	public String getMessage()
	{
		return this.message;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@UnmodifiableView
	@NotNull
	public Map<@NotNull String, @NotNull String> getParams()
	{
		return Collections.unmodifiableMap(this.params);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param obj {@inheritDoc}
	 * @return {@inheritDoc}
	 */
	@Override
	public boolean equals(@Nullable final Object obj)
	{
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Violating)) {
			return false;
		}

		final Violating violation = (Violating) obj;

		if (!Objects.equals(this.getField(), violation.getField())) {
			return false;
		}

		if (!Objects.equals(this.getMessage(), violation.getMessage())) {
			return false;
		}

		if (!Objects.equals(this.getParams(), violation.getParams())) {
			return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@Override
	public int hashCode()
	{
		int result = 0;

		result = 31 * result + this.getField().hashCode();
		result = 31 * result + this.getMessage().hashCode();
		result = 31 * result + this.getParams().hashCode();

		return result;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@NotNull
	@Override
	public String toString()
	{
		return "Violation{" +
			"field='" + this.getField() + "', " +
			"message='" + this.getMessage() + "', " +
			"params=" + this.getParams() +
			"}";
	}
}
