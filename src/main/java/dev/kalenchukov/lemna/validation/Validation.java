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

import dev.kalenchukov.lemna.validation.constraints.*;
import dev.kalenchukov.lemna.validation.constraints.Number;
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
	private Locale locale = new Locale("ru", "RU");

	/**
	 * Объект класса в котором необходимо проверить данные.
	 */
	@NotNull
	private final Object object;

	/**
	 * Настырность проверки.
	 */
	@NotNull
	private Boolean pushy = true;

	/**
	 * Логгер для данного класса.
	 */
	@NotNull
	private static final Logger LOG = Logger.getLogger(Validation.class);

	/**
	 * Локализованные тексты логирования.
	 */
	@NotNull
	private ResourceBundle localeLogs = ResourceBundle.getBundle(
		"lemna/validation/localizations/logs",
		this.locale
	);

	/**
	 * Конструктор для {@code Validation}.
	 *
	 * @param object Объект класса в котором необходимо проверить данные.
	 */
	public Validation(@NotNull final Object object)
	{
		Objects.requireNonNull(object);

		this.object = object;
	}

	/**
	 * @see Validating#setLocale(Locale)
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
	 * @see Validating#getLocale()
	 */
	@NotNull
	@Override
	public Locale getLocale()
	{
		return this.locale;
	}

	/**
	 * @see Validating#isPushy()
	 */
	@NotNull
	@Override
	public Boolean isPushy()
	{
		return this.pushy;
	}

	/**
	 * @see Validating#setPushy(Boolean)
	 */
	@Override
	public void setPushy(@NotNull final Boolean pushy)
	{
		Objects.requireNonNull(pushy);

		this.pushy = pushy;
	}

	/**
	 * @see Validating#validate()
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
	 * @param field Поле класса.
	 * @return Коллекцию нарушений.
	 */
	@NotNull
	private List<@NotNull Violating> validateValueField(@NotNull final Field field)
	{
		Objects.requireNonNull(field);

		List<Violating> violations = new ArrayList<>();

		Map<@NotNull String, @NotNull Validator> validators = this.loadValidators();

		for (Annotation constraintField : field.getDeclaredAnnotations())
		{
			Class<? extends Annotation> constraintType = constraintField.annotationType();
			Validator constraintValidator = validators.get(constraintType.getName());

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

	/**
	 * Загружает коллекцию проверяющих.
	 *
	 * @return Коллекцию проверяющих.
	 */
	@UnmodifiableView
	@NotNull
	private Map<@NotNull String, @NotNull Validator> loadValidators()
	{
		Map<String, Validator> validators = new LinkedHashMap<>();

		validators.put(NoNull.class.getName(), new NoNullValidator(this.locale));
		validators.put(Id.class.getName(), new IdValidator(this.locale));
		validators.put(NoEmpty.class.getName(), new NoEmptyValidator(this.locale));
		validators.put(Size.class.getName(), new SizeValidator(this.locale));
		validators.put(Length.class.getName(), new LengthValidator(this.locale));
		validators.put(Localization.class.getName(), new LocalizationValidator(this.locale));
		validators.put(Number.class.getName(), new NumberValidator(this.locale));
		validators.put(NumberFloat.class.getName(), new NumberFloatValidator(this.locale));
		validators.put(Pattern.class.getName(), new PatternValidator(this.locale));
		validators.put(DayOfMonth.class.getName(), new DayOfMonthValidator(this.locale));
		validators.put(DayOfWeek.class.getName(), new DayOfWeekValidator(this.locale));
		validators.put(DayOfYear.class.getName(), new DayOfYearValidator(this.locale));
		validators.put(Hour.class.getName(), new HourValidator(this.locale));
		validators.put(Millisecond.class.getName(), new MillisecondValidator(this.locale));
		validators.put(Minute.class.getName(), new MinuteValidator(this.locale));
		validators.put(MonthOfYear.class.getName(), new MonthOfYearValidator(this.locale));
		validators.put(Second.class.getName(), new SecondValidator(this.locale));
		validators.put(Year.class.getName(), new YearValidator(this.locale));
		validators.put(Letter.class.getName(), new LetterValidator(this.locale));
		validators.put(LetterAlphabet.class.getName(), new LetterAlphabetValidator(this.locale));
		validators.put(DigitSystem.class.getName(), new DigitSystemValidator(this.locale));
		validators.put(Password.class.getName(), new PasswordValidator(this.locale));
		validators.put(IpAddress.class.getName(), new IpAddressValidator(this.locale));
		validators.put(EmailAddress.class.getName(), new EmailAddressValidator(this.locale));

		validators.put(Valid.class.getName(), new ValidValidator(this.locale));
		validators.put(Valid.ManyValid.class.getName(), new ValidValidator(this.locale));

		validators.put(Exist.class.getName(), new ExistValidator(this.locale));
		validators.put(Exist.ManyExist.class.getName(), new ExistValidator(this.locale));

		return Collections.unmodifiableMap(validators);
	}
}
