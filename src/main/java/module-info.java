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

module dev.kalenchukov.lemna.validation
{
	requires org.jetbrains.annotations;
	requires log4j;
	requires dev.kalenchukov.string.formatting;
	requires dev.kalenchukov.alphabet;
	requires dev.kalenchukov.numeralsystem;

	exports dev.kalenchukov.lemna.validation;
	exports dev.kalenchukov.lemna.validation.constraints;
	exports dev.kalenchukov.lemna.validation.resources;
	exports dev.kalenchukov.lemna.validation.interfaces;
	exports dev.kalenchukov.lemna.validation.exceptions;
}