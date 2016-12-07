package org.camunda.parser

import org.camunda.parser._

import scala.util.parsing.combinator.JavaTokenParsers

object SimpleParser extends JavaTokenParsers {

  def parse(in: String): ParseResult[Exp] = parseAll(expr, in)
      
  def expr: Parser[Exp] = term ~ rep("+" ~ term | "-" ~ term) ^^ { case s ~ ts => ts.foldLeft(s){ case (a,t) => 
          t match {
            case "+" ~ b => Add(a,b)
            case "-" ~ b => Sub(a,b)
          }
        }}
  
  def term: Parser[Exp] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ { case s ~ fs => fs.foldLeft(s){ case (a,f) => 
          f match {
            case "*" ~ b => Times(a,b)
            case "/" ~ b => Div(a,b)
          }
        }}
  
  def factor: Parser[Exp] = ( 
       floatingPointNumber ^^ ( x => Number(x.toDouble) ) 
     | "(" ~> expr <~ ")"
  )
  
}