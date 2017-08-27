package com.scala.test
import scala.util._

object Main{
   
   def main(args: Array[String]) {
     
     var problem = new SortProblem;
     var config = new Config(problem);
       config.populationSize = 100
       config.eliteSize = 10
       config.chromLength = 7
       config.generation = 100
       config.probability_modify = 0.75
       config.probability_mutate = 0.05
       
     
     var lain = new LAINBBO(config);
     lain.start;
       
   }
   
}