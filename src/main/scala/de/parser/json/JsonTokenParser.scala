package de.parser.json

import de.parser.json._
import scala.util.parsing.combinator.JavaTokenParsers

/**
 * @author Philipp
 */
object JsonTokenParser extends JavaTokenParsers {
  
	def parse(in: String): ParseResult[JsonDocument] = parseAll(doc, in)
	
	lazy val doc: Parser[JsonDocument] = "{" ~> repsep(member, ",") <~ "}" ^^ 
		( members => JsonDocument(members.toMap) )
	
	lazy val member: Parser[(String, JsonObject)] = stringLiteraL ~ ":" ~ value ^^ 
		{ case key ~ _ ~ value => (key, value) }
	
	lazy val array: Parser[JsonArray] = "[" ~> repsep(value, ",") <~ "]" ^^ 
		( values => JsonArray(values) )
		
	lazy val value: Parser[JsonObject] = (
			doc 
		| array
		| stringLiteraL ^^ JsonString
		| floatingPointNumber ^^ ( x => JsonNumber(x.toDouble))
		| "true" ^^^ JsonBoolean(true)
		| "false" ^^^ JsonBoolean(false)
		| "null" ^^^ JsonNull
	)
	
	lazy val stringLiteraL: Parser[String] = stringLiteral ^^ (s => s.substring(1, s.length - 1))
	
}