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

package dev.kalenchukov.lemna.validation.supports.existences;

import dev.kalenchukov.lemna.validation.interfaces.Existable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GenderExistence implements Existable<String>
{
	@Override
	public boolean exist(@NotNull String value)
	{
		Objects.requireNonNull(value);

		return value.equals("MALE") || value.equals("FEMALE");
	}
}
