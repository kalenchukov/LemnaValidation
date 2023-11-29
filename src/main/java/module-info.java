/**
 * Определяет API для проверки корректности значений полей классов.
 */
module dev.kalenchukov.lemna.validation
{
	requires org.jetbrains.annotations;
	requires org.apache.logging.log4j;
	requires dev.kalenchukov.string.formatting;
	requires dev.kalenchukov.alphabet;
	requires dev.kalenchukov.numeralsystem;
	requires dev.kalenchukov.string.regexp;

	exports dev.kalenchukov.lemna.validation;
	exports dev.kalenchukov.lemna.validation.constraints;
	exports dev.kalenchukov.lemna.validation.types;
	exports dev.kalenchukov.lemna.validation.interfaces;
	exports dev.kalenchukov.lemna.validation.exceptions;

	opens dev.kalenchukov.lemna.validation;
}