package org.camunda.parser

import org.camunda.parser._

import scala.util.parsing.combinator.JavaTokenParsers

object OptimizedParser extends JavaTokenParsers {

  def parse(in: String): ParseResult[Exp] = parseAll(expr, in)
      
  lazy val expr: Parser[Exp] = chainl1(term, "+" ^^^ Add | "-" ^^^ Sub )
  
  lazy val term: Parser[Exp] = chainl1(factor, "*" ^^^ Times | "/" ^^^ Div)
  
  lazy val factor: Parser[Exp] = ( 
       floatingPointNumber ^^ ( x => Number(x.toDouble) ) 
     | "(" ~! expr ~! ")" ^^ { case _ ~ e ~ _ => e }
  )
  
//  implicit class AdvancedParser[T](p: Parser[T]) {
//    
//    def ~>! [U](q: => Parser[U]): Parser[U] = p ~! q ^^ {case a ~ b => b} named ("~>!") 
//    
//    def <~! [U](q: => Parser[U]): Parser[T] = p ~! q ^^ {case a ~ b => a} named ("<~!")
//    
//  }
  
}