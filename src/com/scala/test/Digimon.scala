package com.scala.test

class Digimon(var name: String , config: Config) {
  
  var neuron = new Neuron(0);
  var lain = new LAINBBO(config);
  
  
  def grow = {
    lain.start;
  }

  
  
}













