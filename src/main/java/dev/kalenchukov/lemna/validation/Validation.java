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

import dev.kalenchukov.lemna.validation.repositories.ValidatorRepository;
import dev.kalenchukov.lemna.validation.validators.*;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.UnmodifiableView;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс проверки корректности значений полей класса.
 */
public class Validation implements Validating
{
	/**
	 * Локализация.
	 */
	@NotNull
	private Locale locale;

	/**
	 * Коллекция всех возможных проверяющих.
	 */
	@NotNull
	private final ValidatorRepository validatorRepository;

	/**
	 * Объект класса в котором необходимо проверить данные.
	 */
	@NotNull
	private final Object object;

	/**
	 * Настырность проверки.
	 */
	@NotNull
	private Boolean pushy;

	/**
	 * Локализованные тексты логирования.
	 */
	@NotNull
	private ResourceBundle localeLogs;

	/**
	 * Логгер для данного класса.
	 */
	@NotNull
	private static final Logger LOG = Logger.getLogger(Validation.class);

	/**
	 * Конструктор для {@code Validation}.
	 *
	 * @param object объект класса в котором необходимо проверить данные.
	 */
	public Validation(@NotNull final Object object)
	{
		Objects.requireNonNull(object);

		this.object = object;
		this.locale = new Locale("ru", "RU");
		this.localeLogs = ResourceBundle.getBundle(
			"lemna/validation/localizations/logs",
			this.locale
		);
		this.pushy = true;
		this.validatorRepository = new ValidatorRepository(this.locale);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param locale {@inheritDoc}
	 */
	@Override
	public void setLocale(@NotNull final Locale locale)
	{
		Objects.requireNonNull(locale);

		if (!this.locale.equals(locale))
		{
			this.locale = locale;

			this.localeLogs = ResourceBundle.getBundle(
				"lemna/validation/localizations/logs",
				this.locale
			);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@NotNull
	@Override
	public Locale getLocale()
	{
		return this.locale;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@NotNull
	@Override
	public Boolean isPushy()
	{
		return this.pushy;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @param pushy {@inheritDoc}
	 */
	@Override
	public void setPushy(@NotNull final Boolean pushy)
	{
		Objects.requireNonNull(pushy);

		this.pushy = pushy;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @return {@inheritDoc}
	 */
	@UnmodifiableView
	@NotNull
	@Override
	public List<@NotNull Violating> validate()
	{
		LOG.debug(String.format(
			this.localeLogs.getString("00001"),
			this.object.getClass().getName()
		));

		List<Violating> violations = new ArrayList<>();

		for (Field field : this.object.getClass().getDeclaredFields())
		{
			violations.addAll(
				this.validateValueField(field)
			);

			if (!this.pushy && violations.size() > 0) {
				break;
			}
		}

		LOG.debug(String.format(
			this.localeLogs.getString("00002"),
			this.object.getClass().getName()
		));

		return Collections.unmodifiableList(violations);
	}

	/**
	 * Проверяет поле класса на корректность.
	 *
	 * @param field поле класса.
	 * @return коллекцию нарушений.
	 */
	@NotNull
	private List<@NotNull Violating> validateValueField(@NotNull final Field field)
	{
		Objects.requireNonNull(field);

		List<Violating> violations = new ArrayList<>();

		for (Annotation constraintField : field.getDeclaredAnnotations())
		{
			Class<? extends Annotation> constraintType = constraintField.annotationType();

			Validator constraintValidator = this.validatorRepository.getValidator(constraintType.getName());

			if (constraintValidator == null) {
				continue;
			}

			try
			{
				LOG.debug(String.format(
					this.localeLogs.getString("00003"),
					field.getName(),
					constraintType.getSimpleName()
				));

				field.setAccessible(true);

				Violating violation = constraintValidator.valid(field, field.get(this.object));

				field.setAccessible(false);

				if (violation == null)
				{
					LOG.debug(this.localeLogs.getString("00004"));
				}
				else
				{
					LOG.debug(this.localeLogs.getString("00006"));

					violations.add(violation);

					if (!this.pushy) {
						break;
					}
				}
			}
			catch (IllegalAccessException exception)
			{
				exception.printStackTrace();
			}
		}

		return violations;
	}
}
