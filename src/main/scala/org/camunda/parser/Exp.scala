package org.camunda.parser

sealed trait Exp

case class Number(x: Double) extends Exp

case class Add(x: Exp, y: Exp) extends Exp

case class Sub(x: Exp, y: Exp) extends Exp

case class Times(x: Exp, y: Exp) extends Exp

case class Div(x: Exp, y: Exp) extends Exp