package com.scala.test

class Config(var problem: Problem) {
  
  var populationSize: Int = 100
  var eliteSize: Int = 10
  var chromLength: Int = 10
  var chromPerElemRange = Array(0,1)
  var generation = 100
  var probability_modify = 0.75
  var probability_mutate = 0.05
  var F = 0.5
  
}
