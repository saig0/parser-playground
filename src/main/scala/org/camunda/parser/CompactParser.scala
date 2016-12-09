package org.camunda.parser

import org.camunda.parser._

import scala.util.parsing.combinator.JavaTokenParsers

object CompactParser extends JavaTokenParsers {

  def parse(in: String): ParseResult[Exp] = parseAll(expr, in)
      
  def expr: Parser[Exp] = chainl1(term, "+" ^^^ Add | "-" ^^^ Sub )
  
  def term: Parser[Exp] = chainl1(factor, "*" ^^^ Times | "/" ^^^ Div)
  
  def factor: Parser[Exp] = ( 
       floatingPointNumber ^^ ( x => Number(x.toDouble) ) 
     | "(" ~> expr <~ ")"
  )
  
}