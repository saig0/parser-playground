package org.camunda.parser

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class RightRecursiveParserTest extends FlatSpec with Matchers with ParserTest {
  
  "A right-recursive parser" should behave like parser { exp => RightRecursiveParser.parse(exp).get }
  
}