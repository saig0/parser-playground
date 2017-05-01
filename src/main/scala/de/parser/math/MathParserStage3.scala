package de.parser.math

import de.parser.math._

import scala.util.parsing.combinator.JavaTokenParsers

object MathParserStage3 extends JavaTokenParsers {

  def parse(in: String): ParseResult[MathExp] = parseAll(expr, in)
      
  lazy val expr: Parser[MathExp] = chainl1(term, "+" ^^^ Add | "-" ^^^ Sub )
  
  lazy val term: Parser[MathExp] = chainl1(factor, "*" ^^^ Times | "/" ^^^ Div)
  
  lazy val factor: Parser[MathExp] = ( 
       floatingPointNumber ^^ ( x => Number(x.toDouble) ) 
     | "(" ~! expr ~! ")" ^^ { case _ ~ e ~ _ => e }
  )
  
}