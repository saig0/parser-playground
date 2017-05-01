package de.parser.math

import de.parser.math._
import scala.util.parsing.combinator.JavaTokenParsers

object MathParserStage1 extends JavaTokenParsers {

  def parse(in: String): ParseResult[MathExp] = parseAll(expr, in)
      
  def expr: Parser[MathExp] = term ~ rep("+" ~ term | "-" ~ term) ^^ { case s ~ ts => (s /: ts){ case (a,t) => 
          t match {
            case "+" ~ b => Add(a,b)
            case "-" ~ b => Sub(a,b)
          }
        }}
  
  def term: Parser[MathExp] = factor ~ rep("*" ~ factor | "/" ~ factor) ^^ { case s ~ fs => (s /: fs){ case (a,f) => 
          f match {
            case "*" ~ b => Times(a,b)
            case "/" ~ b => Div(a,b)
          }
        }}
  
  def factor: Parser[MathExp] = ( 
       floatingPointNumber ^^ ( x => Number(x.toDouble) ) 
     | "(" ~> expr <~ ")"
  )
  
}