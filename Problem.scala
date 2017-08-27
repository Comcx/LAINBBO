package com.scala.test

abstract class Problem {
  def cost(list: Array[Individual] , config: Config): Any
  def init(list: Array[Individual] , config: Config): Any
  def feasible(list: Array[Individual] , config: Config): Any
  
}


class SortProblem extends Problem {
  
  def init(list: Array[Individual] , config: Config) = {
    for( i <- 0 to (list.length - 1) ){
        list(i) = new Individual(config);

    }
  }//end init
  
  def cost(list: Array[Individual] , config: Config) = {
    
    for( listIndex <- 0 to config.populationSize-1 ){
      list(listIndex).cost = 0f;
      for( chromIndex <- 1 to config.chromLength-1 ){
        if( list(listIndex).chrom(chromIndex) < list(listIndex).chrom(chromIndex-1) )
        {
          list(listIndex).cost = list(listIndex).cost + 1f;
        }
      }
    }
    
    /*for( listIndex <- 0 to config.populationSize-1 ){
      list(listIndex).cost = list(listIndex).chrom.sum;
    }*/
  }
  
  def feasible(list: Array[Individual] , config: Config) = {
    for( i <- 0 to (list.length - 1) ){
      for( j <- 0 to (config.chromLength-1) ){
        if( list(i).chrom(j) < config.chromPerElemRange(0) ||
            list(i).chrom(j) > config.chromPerElemRange(1))
        {
          list(i).chrom(j) = (new util.Random).nextFloat();}
      }//end for j
    }//end for i
  }//end feasible
  
  
}







