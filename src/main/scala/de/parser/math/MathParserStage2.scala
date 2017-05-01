package de.parser.math

import de.parser.math._

import scala.util.parsing.combinator.JavaTokenParsers

object MathParserStage2 extends JavaTokenParsers {

  def parse(in: String): ParseResult[MathExp] = parseAll(expr, in)
      
  def expr: Parser[MathExp] = chainl1(term, "+" ^^^ Add | "-" ^^^ Sub )
  
  def term: Parser[MathExp] = chainl1(factor, "*" ^^^ Times | "/" ^^^ Div)
  
  def factor: Parser[MathExp] = ( 
       floatingPointNumber ^^ ( x => Number(x.toDouble) ) 
     | "(" ~> expr <~ ")"
  )
  
}