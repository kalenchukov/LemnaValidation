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

package dev.kalenchukov.lemna.validation.repositories;

import dev.kalenchukov.lemna.validation.constraints.*;
import dev.kalenchukov.lemna.validation.constraints.Number;
import dev.kalenchukov.lemna.validation.validators.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.*;

/**
 * Класс репозиторий всех проверяющих.
 */
public final class ValidatorRepository
{
	/**
	 * Локализация.
	 */
	@NotNull
	private final Locale locale;

	/**
	 * Коллекция проверяющих.
	 */
	@NotNull
	private final Map<@NotNull String, @NotNull Validator> validators;

	/**
	 * Конструктор для {@code ValidatorRepository}.
	 *
	 * @param locale локализация.
	 */
	public ValidatorRepository(@NotNull final Locale locale)
	{
		this.locale = locale;
		this.validators = this.loadValidators();
	}

	/**
	 * Возвращает коллекцию проверяющих.
	 *
	 * @return коллекцию проверяющих.
	 */
	@Unmodifiable
	@NotNull
	public Map<@NotNull String, @NotNull Validator> getValidators()
	{
		return Collections.unmodifiableMap(this.validators);
	}

	/**
	 * Возвращает проверяющего.
	 *
	 * @return проверяющий.
	 */
	@Nullable
	public Validator getValidator(@NotNull final String forConstraint)
	{
		Objects.requireNonNull(forConstraint);

		return this.validators.get(forConstraint);
	}

	/**
	 * Добавляет коллекцию проверяющих.
	 *
	 * @param validators коллекция проверяющих.
	 */
	public void addValidators(@NotNull final Map<@NotNull String, @NotNull Validator> validators)
	{
		Objects.requireNonNull(validators);

		validators.forEach(this::addValidator);
	}

	/**
	 * Добавляет проверяющего.
	 *
	 * @param forConstraint ограничение для которого используется проверяющий.
	 * @param validator класс проверяющего.
	 */
	public void addValidator(@NotNull final String forConstraint,
							 @NotNull final Validator validator)
	{
		Objects.requireNonNull(forConstraint);
		Objects.requireNonNull(validator);

		this.validators.putIfAbsent(forConstraint, validator);
	}

	/**
	 * Загружает проверяющих.
	 */
	@NotNull
	private Map<@NotNull String, @NotNull Validator> loadValidators()
	{
		Map<String, Validator> validators = new HashMap<>();

		validators.put(Id.class.getName(), new IdValidator(this.locale));
		validators.put(Size.class.getName(), new SizeValidator(this.locale));
		validators.put(Length.class.getName(), new LengthValidator(this.locale));
		validators.put(Localization.class.getName(), new LocalizationValidator(this.locale));
		validators.put(Pattern.class.getName(), new PatternValidator(this.locale));
		validators.put(Password.class.getName(), new PasswordValidator(this.locale));

		validators.put(NoNull.class.getName(), new NoNullValidator(this.locale));
		validators.put(NoEmpty.class.getName(), new NoEmptyValidator(this.locale));

		validators.put(Number.class.getName(), new NumberValidator(this.locale));
		validators.put(NumberFloat.class.getName(), new NumberFloatValidator(this.locale));

		validators.put(Year.class.getName(), new YearValidator(this.locale));
		validators.put(MonthOfYear.class.getName(), new MonthOfYearValidator(this.locale));

		validators.put(DayOfWeek.class.getName(), new DayOfWeekValidator(this.locale));
		validators.put(DayOfMonth.class.getName(), new DayOfMonthValidator(this.locale));
		validators.put(DayOfYear.class.getName(), new DayOfYearValidator(this.locale));

		validators.put(Hour.class.getName(), new HourValidator(this.locale));
		validators.put(Minute.class.getName(), new MinuteValidator(this.locale));
		validators.put(Second.class.getName(), new SecondValidator(this.locale));
		validators.put(Millisecond.class.getName(), new MillisecondValidator(this.locale));

		validators.put(Letter.class.getName(), new LetterValidator(this.locale));
		validators.put(LetterAlphabet.class.getName(), new LetterAlphabetValidator(this.locale));

		validators.put(Digit.class.getName(), new DigitValidator(this.locale));
		validators.put(DigitSystem.class.getName(), new DigitSystemValidator(this.locale));

		validators.put(InetAddress.class.getName(), new InetAddressValidator(this.locale));
		validators.put(MacAddress.class.getName(), new MacAddressValidator(this.locale));
		validators.put(EmailAddress.class.getName(), new EmailAddressValidator(this.locale));

		validators.put(RgbNumeric.class.getName(), new RgbNumericValidator(this.locale));
		validators.put(RgbHex.class.getName(), new RgbHexValidator(this.locale));

		validators.put(Valid.class.getName(), new ValidValidator(this.locale));
		validators.put(Valid.ManyValid.class.getName(), new ValidValidator(this.locale));

		validators.put(Exist.class.getName(), new ExistValidator(this.locale));
		validators.put(Exist.ManyExist.class.getName(), new ExistValidator(this.locale));

		validators.put(CountryCodeAlpha2.class.getName(), new CountryCodeAlpha2Validator(this.locale));

		return validators;
	}
}
