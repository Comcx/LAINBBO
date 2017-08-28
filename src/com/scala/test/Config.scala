package com.scala.test

class Config(var problem: Problem) {
  
  var populationSize: Int = 100
  var eliteSize: Int = 10
  var chromLength: Int = 6
  var neuronSize = 4
  var chromPerElemRange: Array[Double] = Array(0,1)
  var generation = 100
  var probability_modify = 0.75
  var probability_mutate = 0.05
  var K = Array( 0.02, 0.4, 0.95, 0.1, 0.25 )
  
}
