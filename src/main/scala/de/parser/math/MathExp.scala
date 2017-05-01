package de.parser.math

sealed trait MathExp

case class Number(x: Double) extends MathExp

case class Add(x: MathExp, y: MathExp) extends MathExp

case class Sub(x: MathExp, y: MathExp) extends MathExp

case class Times(x: MathExp, y: MathExp) extends MathExp

case class Div(x: MathExp, y: MathExp) extends MathExp