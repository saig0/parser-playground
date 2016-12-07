package org.camunda.parser

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class SimpleParserTest extends FlatSpec with Matchers with ParserTest {
  
  "A simple parser" should behave like parser { exp => SimpleParser.parse(exp).get }
  
}