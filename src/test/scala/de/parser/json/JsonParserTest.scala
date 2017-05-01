package de.parser.json

import org.scalatest.{FlatSpec,Matchers}

/**
 * @author Philipp
 */
class JsonParserTest extends FlatSpec with Matchers {
  
	private def parse(exp: String) = JsonTokenParser.parse(exp).get
	
	"An implementation" should "parse an empty JSON doc" in {
		
		parse("{}") should be (JsonDocument(Map()))
	}
		
	it should "parse a single string value" in {
		
		parse(""" { "foo": "bar" } """) should be (JsonDocument(
			Map("foo" -> JsonString("bar"))		
		))
	}
	
	it should "parse a number value" in {
		
		parse(""" { "foo": 2.4, "bar": 3 } """) should be (JsonDocument(
			Map("foo" -> JsonNumber(2.4),
					"bar" -> JsonNumber(3))		
		))
	}
	
	it should "parse a boolean value" in {
		
		parse(""" { "foo": true, "bar": false } """) should be (JsonDocument(
			Map("foo" -> JsonBoolean(true),
					"bar" -> JsonBoolean(false))		
		))
	}
	
	it should "parse a null value" in {
		
		parse(""" { "foo": null } """) should be (JsonDocument(
			Map("foo" -> JsonNull)		
		))
	}
	
	it should "parse an empty array value" in {
		
		parse(""" { "foo": [] } """) should be (JsonDocument(
			Map("foo" -> JsonArray(List()))		
		))
	}
	
	it should "parse an array value" in {
		
		parse(""" { "foo": [1,2,3], "bar": ["a","b"] } """) should be (JsonDocument(
			Map("foo" -> JsonArray(List(JsonNumber(1), JsonNumber(2), JsonNumber(3))),
					"bar" -> JsonArray(List(JsonString("a"), JsonString("b"))))		
		))
	}
	
	it should "parse a nested document" in {
		
		parse(""" { "foo": { "bar": 2.4 } } """) should be (JsonDocument(
			Map("foo" -> JsonDocument(
					Map("bar" -> JsonNumber(2.4))
				))		
		))
	}
	
	it should "parse a complex document" in {
		
		parse(""" 
		{
			"address book": {
      	"name": "John Smith", 
      	"address": {
        	"street": "10 Market Street", 
        	"city": "San Francisco, CA", 
        	"zip": 94111
				}, 
      	"phone numbers": ["408 338-4238", "408 111-6892"]
    	}
		}
	  """) should be (JsonDocument(
			Map("address book" -> JsonDocument(
					Map("name" -> JsonString("John Smith"),
						  "address" -> JsonDocument(
						  		Map("street" -> JsonString("10 Market Street"),
						  				"city" -> JsonString("San Francisco, CA"),
						  				"zip" -> JsonNumber(94111)
						  		)),
						  "phone numbers" -> JsonArray(
						  		List(JsonString("408 338-4238"), JsonString("408 111-6892"))
						  ))
					))		
			))
	}
	
}