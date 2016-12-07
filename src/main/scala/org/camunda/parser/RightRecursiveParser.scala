package org.camunda.parser

import org.camunda.parser._

import scala.util.parsing.combinator.JavaTokenParsers

object RightRecursiveParser extends JavaTokenParsers {

  def parse(in: String): ParseResult[Exp] = parseAll(expr, in)
      
  def expr: Parser[Exp] = ( 
        term ~ "+" ~ expr ^^ { case a ~ _ ~ b => Add(a,b) }
      | term ~ "-" ~ expr ^^ { case a ~ _ ~ b => Sub(a,b) }
      | term
  )
  
  def term: Parser[Exp] = ( 
        factor ~ "*" ~ expr ^^ { case a ~ _ ~ b => Times(a,b) }
      | factor ~ "/" ~ expr ^^ { case a ~ _ ~ b => Div(a,b) }
      | factor
  )
  
  def factor: Parser[Exp] = ( 
       floatingPointNumber ^^ ( x => Number(x.toDouble) ) 
     | "(" ~> expr <~ ")"
  )
  
}