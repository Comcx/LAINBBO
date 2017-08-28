package com.scala.test
import scala.collection.mutable.ArrayBuffer
import scala.math


class Signal( val fromIndex: Int , var value: Double ) {}

class Link( val fromIndex: Int , val toIndex: Int , var value: Double ) {}


class Neuron( val index: Int ) {
  
  var bias: Double = 0;
  var links = new ArrayBuffer[Link];
  var signals = new ArrayBuffer[Signal];
  var threshhold: Double = 1;
  
  def sum: Double = {
    var sum: Double = 0;
    for( index <- 0 to links.length ){
      sum = sum + signals(index).value * links(index).value;
    }
    return sum;
  }
  
  def outputFunction: Double = {
    var sum = this.sum;
    var slope = 7;
    var res = 1 / ( 1 + math.exp(-slope*sum) );
    
    return res;
  }
  
  def linkTo( toIndex: Int ,value: Double ) = {
    this.links += new Link(this.index,toIndex,value);
    this.signals += new Signal(toIndex,0);
  }
  
  def getSignal( fromIndex: Int , value: Double ) = {
    for( index <- 0 to signals.length-1 ){
      if( signals(index).fromIndex == fromIndex ){
        signals(index).value = value;
      }//end if
    }//end for
  }//end getSignal
  
  
}




