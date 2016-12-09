package org.camunda.parser

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class OptimizedParserTest extends FlatSpec with Matchers with ParserTest {
  
  "A optimized parser" should behave like parser { exp => OptimizedParser.parse(exp).get }
  
}