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
import org.jetbrains.annotations.UnmodifiableView;

import java.util.*;

/**
 * Класс репозиторий всех проверяющих.
 */
public final class ValidatorRepository
{
	/**
	 * Коллекция проверяющих.
	 */
	@NotNull
	private final Map<@NotNull String, @NotNull Validator> validators;

	/**
	 * Локализация.
	 */
	@NotNull
	private final Locale locale;

	/**
	 * Конструктор для {@code ValidatorRepository}.
	 */
	public ValidatorRepository(@NotNull final Locale locale)
	{
		this.validators = new HashMap<>();
		this.locale = locale;

		this.loadValidators();
	}

	/**
	 * Возвращает коллекцию проверяющих.
	 *
	 * @return Коллекцию проверяющих.
	 */
	@UnmodifiableView
	@NotNull
	public Map<@NotNull String, @NotNull Validator> getValidators()
	{
		return Collections.unmodifiableMap(this.validators);
	}

	/**
	 * Возвращает проверяющего.
	 *
	 * @return Проверяющий.
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
	 * @param validators Коллекция проверяющих.
	 */
	public void addValidators(@NotNull final Map<@NotNull String, @NotNull Validator> validators)
	{
		Objects.requireNonNull(validators);

		validators.forEach(this::addValidator);
	}

	/**
	 * Добавляет проверяющего.
	 *
	 * @param forConstraint Ограничение для которого используется проверяющий.
	 * @param validator Класс проверяющего.
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
	private void loadValidators()
	{
		this.addValidator(Id.class.getName(), new IdValidator(this.locale));
		this.addValidator(Size.class.getName(), new SizeValidator(this.locale));
		this.addValidator(Length.class.getName(), new LengthValidator(this.locale));
		this.addValidator(Localization.class.getName(), new LocalizationValidator(this.locale));
		this.addValidator(Pattern.class.getName(), new PatternValidator(this.locale));
		this.addValidator(Password.class.getName(), new PasswordValidator(this.locale));

		this.addValidator(NoNull.class.getName(), new NoNullValidator(this.locale));
		this.addValidator(NoEmpty.class.getName(), new NoEmptyValidator(this.locale));

		this.addValidator(Number.class.getName(), new NumberValidator(this.locale));
		this.addValidator(NumberFloat.class.getName(), new NumberFloatValidator(this.locale));

		this.addValidator(Year.class.getName(), new YearValidator(this.locale));
		this.addValidator(MonthOfYear.class.getName(), new MonthOfYearValidator(this.locale));

		this.addValidator(DayOfWeek.class.getName(), new DayOfWeekValidator(this.locale));
		this.addValidator(DayOfMonth.class.getName(), new DayOfMonthValidator(this.locale));
		this.addValidator(DayOfYear.class.getName(), new DayOfYearValidator(this.locale));

		this.addValidator(Hour.class.getName(), new HourValidator(this.locale));
		this.addValidator(Minute.class.getName(), new MinuteValidator(this.locale));
		this.addValidator(Second.class.getName(), new SecondValidator(this.locale));
		this.addValidator(Millisecond.class.getName(), new MillisecondValidator(this.locale));

		this.addValidator(Letter.class.getName(), new LetterValidator(this.locale));
		this.addValidator(LetterAlphabet.class.getName(), new LetterAlphabetValidator(this.locale));

		this.addValidator(Digit.class.getName(), new DigitValidator(this.locale));
		this.addValidator(DigitSystem.class.getName(), new DigitSystemValidator(this.locale));

		this.addValidator(InetAddress.class.getName(), new InetAddressValidator(this.locale));
		this.addValidator(MacAddress.class.getName(), new MacAddressValidator(this.locale));
		this.addValidator(EmailAddress.class.getName(), new EmailAddressValidator(this.locale));

		this.addValidator(RgbNumeric.class.getName(), new RgbNumericValidator(this.locale));
		this.addValidator(RgbHex.class.getName(), new RgbHexValidator(this.locale));

		this.addValidator(Valid.class.getName(), new ValidValidator(this.locale));
		this.addValidator(Valid.ManyValid.class.getName(), new ValidValidator(this.locale));

		this.addValidator(Exist.class.getName(), new ExistValidator(this.locale));
		this.addValidator(Exist.ManyExist.class.getName(), new ExistValidator(this.locale));
	}
}
