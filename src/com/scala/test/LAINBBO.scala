package com.scala.test

class LAINBBO(config: Config) {
  
  var population = new Population(config);
  
  
  def hrLine = {
      var outLine: String = "-";
      for( i <- 0 to 99 ){
        outLine += "-";
      }
      println(outLine);
    }
    
    def show(genIndex: Int) = {
      hrLine//------------------------------------
      println(">> Generation "+genIndex);
      population.show;  println();
    }
  
  def start = {
    
    for( genIndex <- 0 to config.generation )
    {
      population.cost;
      population.listSort;
      population.calculateCost;
      this.show(genIndex);
      population.keepElite;
      
      population.modify(genIndex);
      population.mutate;
      
      population.refresh;
           
    }//end for genIndex
    population.cost;
    population.listSort;
    
     
    
    
  }//end start
  
  
}
