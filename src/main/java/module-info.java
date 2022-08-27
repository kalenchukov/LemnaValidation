module dev.kalenchukov.lemna.validation
{
	requires org.jetbrains.annotations;
	requires log4j;
	requires dev.kalenchukov.string.formatting;
	requires dev.kalenchukov.alphabet;
	requires dev.kalenchukov.numeralsystem;
	requires dev.kalenchukov.string.regexp;

	exports dev.kalenchukov.lemna.validation;
	exports dev.kalenchukov.lemna.validation.constraints;
	exports dev.kalenchukov.lemna.validation.resources;
	exports dev.kalenchukov.lemna.validation.interfaces;
	exports dev.kalenchukov.lemna.validation.exceptions;
}