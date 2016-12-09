package org.camunda.parser

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class CompactParserTest extends FlatSpec with Matchers with ParserTest {
  
  "A compact parser" should behave like parser { exp => CompactParser.parse(exp).get }
  
}