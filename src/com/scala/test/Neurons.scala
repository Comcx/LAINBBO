package com.scala.test
import scala.collection.mutable.ArrayBuffer

class Neurons(config: Config) {
  
  val size = config.neuronSize;
  var list = new ArrayBuffer[Neuron];
  var startSize = 1;
  var endSize = 2;
  this.init;
  
  
  def init = {
    for( index <- 0 to size-1 ){
      list += new Neuron(index);
    }
  }
  
  
  
  
}