package com.scala.test

class LAINBBO(config: Config) {
  
  def start = {
    var population = new Population(config);
    for( genIndex <- 0 to config.generation )
    {
      population.cost;
      population.listSort;
      population.getAvgCost;
      hrLine//------------------------------------
      println(">> Generation "+genIndex);
      population.show;  println();
      population.keepElite;
      
      population.modify(genIndex);
      population.mutate;
      
      population.refresh;
           
    }//end for genIndex
    population.cost;
    population.listSort;
    
     
    def hrLine = {
      var outLine: String = "-";
      for( i <- 0 to 99 ){
        outLine += "-";
      }
      println(outLine);}
    
  }//end start
  
  
}