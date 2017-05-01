package de.parser.json

/**
 * @author Philipp
 */
sealed trait JsonObject

case object JsonNull extends JsonObject

case class JsonNumber(value: Double) extends JsonObject
case class JsonString(value: String) extends JsonObject
case class JsonBoolean(value: Boolean) extends JsonObject

case class JsonArray(value: List[JsonObject]) extends JsonObject
case class JsonDocument(value: Map[String, JsonObject]) extends JsonObject