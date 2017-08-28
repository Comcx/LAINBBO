package com.scala.test
import scala.math

abstract class Problem {
  def cost(list: Array[Individual] , config: Config): Any
  def init(list: Array[Individual] , config: Config): Any
  def feasible(list: Array[Individual] , config: Config): Any
  
}


class DigiProblem extends Problem {
  
  def init(list: Array[Individual] , config: Config) = {
    for( i <- 0 to (list.length - 1) ){
        list(i) = new Individual(config);

    }
  }//end init
  
  def cost(list: Array[Individual] , config: Config) = {
    
    /*for( listIndex <- 0 to config.populationSize-1 ){
      list(listIndex).cost = 0f;
      for( chromIndex <- 1 to config.chromLength-1 ){
        if( list(listIndex).chrom(chromIndex).value < 
                              list(listIndex).chrom(chromIndex-1).value )
        {
          list(listIndex).cost = list(listIndex).cost + 0.1;
        }
      }
    }*/
    
    for( listIndex <- 0 to config.populationSize-1 ){
      var sum: Double = 0.0;
      for( chromIndex <- 0 to config.chromLength-1 ){
        sum = sum + list(listIndex).chrom(chromIndex).value;
      }
      list(listIndex).cost = math.abs(sum - 0);
    }
  }
  
  def feasible(list: Array[Individual] , config: Config) = {
    for( i <- 0 to (list.length - 1) ){
      for( j <- 0 to (config.chromLength-1) ){
        if( list(i).chrom(j).value < config.chromPerElemRange(0) ||
            list(i).chrom(j).value > config.chromPerElemRange(1))
        {
          list(i).chrom(j).value = (new util.Random).nextDouble();}
      }//end for j
    }//end for i
  }//end feasible
  
  
}







