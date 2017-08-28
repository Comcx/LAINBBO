/*                     __                                               *\
**     ________ ___   / /  ___     Scala                                **
**    / __/ __// _ | / /  / _ |    (c) 2003-2013, LAMP/EPFL             **
**  __\ \/ /__/ __ |/ /__/ __ |    Comcx.lain.main                      **
** /____/\___/_/ |_/____/_/ | |                                         **
**                          |/                                          **
\*                                                                      */



package com.scala.test
import scala.util._

object Main{
   
   def main(args: Array[String]) {
     
     var problem = new DigiProblem;
     var config = new Config(problem);
       config.populationSize = 200
       config.eliteSize = config.populationSize / 10;
       //config.neuronSize = 4
       config.chromLength = 7
       config.chromPerElemRange = Array(0,1)
       config.generation = 200
       config.probability_modify = 0.75
       config.probability_mutate = 0.05
       config.K = Array( 0.5, 0.4, 0.95, 0.1, 0.25 )
       
     var Agumon = new Digimon( "Agumon", config );
     Agumon.grow;
       
   }
   
}
