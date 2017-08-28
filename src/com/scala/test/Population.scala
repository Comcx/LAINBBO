package com.scala.test
import java.lang.Math


class Factor( var value: Double ) {
  
  //var link = new Link(fromIndex,toIndex,value);
  
}



class Individual(config: Config) extends Comparable {
  var length = config.chromLength;
  var chrom = new Array[Factor](length)
  var cost: Double = 0.0
  this.init

  def init = {
    var counter = 0;
    for( i <- 0 to length-1 ){
        var randomNumber = (new util.Random).nextDouble() * config.chromPerElemRange(1);
        this.chrom(i) = new Factor(randomNumber);
    }
    //println(counter);
   
  }//init
  
  def show = {
    for( i <- 0 to (this.length-1) ){
      printf("%.4f\t", this.chrom(i).value);
    }
    
  }
  
  
  def equal: Boolean = {
    return true
  }
  
  def less: Boolean = {
    return false
  }
  
  def sort = {}
  
}



class Population(config: Config) {
  private var size = config.populationSize;
  private var list = new Array[Individual](this.size)
  private var island = new Array[Individual](this.size)
  private var elite = new Array[Individual](config.eliteSize)
  private var lambda = new Array[Float](this.size);
  private var mu = new Array[Float](this.size);
  
  private var avgCost: Double = 0.0;
  private var minCost: Double = 0.0;
  private var maxCost: Double = 0.0;
  this.init
  
  
  def init = {
    config.problem.init( this.list , config )
    for( listIndex <- 0 to this.size-1 ){
      this.island(listIndex) = this.list(listIndex);
    }
  }//end init
  
  def feasible = config.problem.feasible(this.list , config)
  def cost = config.problem.cost(list , config)
  def listSort = {
    
    this.list = this.list.sortWith( (a,b) => a.cost < b.cost );
  }
  
  def keepElite = {
    for( i <- 0 to (config.eliteSize-1) ){
      elite(i) = this.list(i);
    }
  }
  
  def getLambdaMu = {
    for( listIndex <- 0 to this.size-1 ){
      this.mu(listIndex) = (this.size-listIndex+0f) / this.size;
      this.lambda(listIndex) = 1 - this.mu(listIndex);
    }
  }
  
  
  def modify(genIndex: Int) = {
    this.getLambdaMu;
    
    for( listIndex <- 0 to this.size-1 )
    {
      var randomNumber = (new util.Random).nextDouble();
      if( randomNumber <= config.probability_modify ){
        var lambdaScale = ( lambda(listIndex) - lambda.min ) / 
                                              ( lambda.max - lambda.min );
        for( chromIndex <- 0 to config.chromLength-1 ){
          if( (new util.Random).nextFloat() < lambdaScale )
          {
            randomNumber = (new util.Random).nextDouble() * mu.sum;
            var select = mu(1);
            var selectIndex = 1;
            while( (randomNumber > select) && (selectIndex < this.size-1) )
            {
              selectIndex = selectIndex + 1;
              select = select + mu(selectIndex);
            }
            island(listIndex).chrom(chromIndex).value = 
                    ( 0.0+genIndex.toDouble/config.generation ) * list(listIndex).chrom(chromIndex).value + 
                    (1.0-genIndex.toDouble/config.generation) * list(selectIndex).chrom(chromIndex).value;
            
          }//end if lambdaScale
        }//end for chromIndex
        
      }//end if modify
    }//end for listIndex
  }
  
  def mutate = {
    this.cost;
    this.listSort;
    for( listIndex <- 0 to this.size-1 ){
      for( chromIndex <- 0 to config.chromLength-1 ){
        if( (new util.Random).nextFloat() < config.probability_mutate ){
          var randomNum1 = (new util.Random).nextDouble();
          var randomNum2 = (new util.Random).nextDouble();
          
          island(listIndex).chrom(chromIndex).value = 
						list(listIndex).chrom(chromIndex).value + config.K(0) *
						( list(1).chrom(chromIndex).value - list(listIndex).chrom(chromIndex).value ) + config.K(0)*
						( list( (randomNum1*config.populationSize).toInt ).chrom(chromIndex).value -
							list( (randomNum2*config.populationSize).toInt ).chrom(chromIndex).value );
          
        }//end if mutate
      }//end for chromIndex
    }//end for listIndex
  }
  
  def refresh = {
    for( listIndex <- 0 to this.size-1 ){
      this.list(listIndex) = this.island(listIndex)
    }
    for( eliteIndex <- 0 to config.eliteSize-1 ){
      this.list( size-1-eliteIndex ) = this.elite(eliteIndex);
    }
    
    this.feasible;
    this.cost;
    this.listSort;
    this.calculateCost;
    
    var randomIndex = (new util.Random).nextInt(config.populationSize);
    if( list(randomIndex).cost >= avgCost ){
      config.probability_modify = config.K(1)*(maxCost-list(randomIndex).cost)/(maxCost-avgCost);
      config.probability_mutate = config.K(3)*(maxCost-maxCost-list(randomIndex).cost)/(maxCost-avgCost);
    }else{
      config.probability_modify = config.K(2);
      config.probability_mutate = config.K(4);
    }
    
  }
  
  def calculateCost = {
    this.avgCost = 0.0;  this.minCost = list(0).cost;  this.maxCost = list(0).cost;

    for( listIndex <- 0 to this.size-1 ){
      this.avgCost = this.avgCost + this.list(listIndex).cost.toDouble;
      if( list(listIndex).cost < minCost ){
        minCost = list(listIndex).cost;
      }else if( list(listIndex).cost > maxCost ){
        maxCost = list(listIndex).cost;
      }
    }
    this.avgCost = this.avgCost / this.size;
  }
  
  
  def show = {
    this.list(0).show;
    printf("\n->avgCost: %.4f",this.avgCost);
    printf("\n->bestCost: %.4f",list(0).cost);
  }
  
  def listShow = {
    if( this.list(0) != null )
    {
      println("Population.total size: "+this.size);
      for( i <- 0 to (this.size - 1) ){
        printf("%d.\t",i);  this.list(i).show;  printf(">cost:\t%.4f",this.list(i).cost);
        println();
      }
    }else{
      println("Population has not been inited!");
    }//end if
  }//end listShow
  
  def eliteShow = {
    if( this.elite(0) != null )
    {
      println("Elite.total size: "+config.eliteSize);
      for( i <- 0 to (config.eliteSize - 1) ){
        printf("%d.\t",i);  this.elite(i).show;
        println();
      }
    }else{
      println("Elite has not been inited!");
    }//end if
  }//end eliteShow
  
  def lambdaMuShow = {
    if(lambda != null && mu != null)
    {
      println("Lambda&Mu: ");
      for( listIndex <- 0 to this.size-1 ){
        printf("%.4f\t%.4f", lambda(listIndex), mu(listIndex));
        println();
      }
    }
  }
  
  
  
  
  
  
  
}





















