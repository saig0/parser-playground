package de.parser.math

import org.scalatest.FlatSpec
import org.scalatest.Matchers

class MathParserTest extends FlatSpec with Matchers {
  	
  private def parse(exp: String): MathExp = MathParserStage3.parse(exp).get
  
  "A parser" should "parse 1" in {
    
    parse("1") should be(Number(1))    
  }
  
  it should "parse 1 + 2 + 3" in {
    
    parse("1 + 2 + 3") should be(Add(Add(Number(1), Number(2)), Number(3)))  
    
  }
  
  it should "parse 7 + 2 - 3 + 1" in {
    
    parse("7 + 2 - 3 + 1") should be(Add(Sub(Add(Number(7), Number(2)), Number(3)), Number(1)))  
    
  }
  
  it should "parse 1 * 2 * 3" in {
    
    parse("1 * 2 * 3") should be(Times(Times(Number(1), Number(2)), Number(3)))  
    
  }
  
  it should "parse 1 + 2 * 3" in {
    
    parse("1 + 2 * 3") should be(Add(Number(1), Times(Number(2), Number(3))))    
  }
  
  it should "parse 1 * 2 + 3" in {
    
    parse("1 * 2 + 3") should be(Add(Times(Number(1), Number(2)), Number(3)))    
  }
  
}