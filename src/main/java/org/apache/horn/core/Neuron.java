/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.horn.core;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hama.commons.math.DoubleFunction;

public abstract class Neuron<M extends Writable> implements Writable, NeuronInterface<M> {
  int id;
  double output;
  double weight;
  double delta;

  double momentumWeight;
  double learningRate;

  int layerIndex;
  boolean isOutputLayer;
  
  protected DoubleFunction squashingFunction;

  public void setNeuronID(int id) {
    this.id = id;
  }
  
  public int getID() {
    return id;
  }
  
  public int getLayerIndex() {
    return layerIndex;
  }

  public void setLayerIndex(int index) {
    this.layerIndex = index;
  }
  
  public void feedforward(double sum) {
    this.output = sum;
  }

  public void backpropagate(double gradient) {
    this.delta = gradient;
  }

  public void setDelta(double delta) {
    this.delta = delta;
  }
  
  public double getDelta() {
    return delta;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public void setOutput(double output) {
    this.output = output;
  }

  public double getOutput() {
    return output;
  }

  public void setMomentumWeight(double momentumWeight) {
    this.momentumWeight = momentumWeight;
  }

  public double getMomentumWeight() {
    return momentumWeight;
  }

  public void setLearningRate(double learningRate) {
    this.learningRate = learningRate;
  }

  public double getLearningRate() {
    return learningRate;
  }

  // ////////

  private int i;

  public void push(double weight) {
    weights[i++] = weight;
  }

  public double getUpdate() {
    return weight;
  }

  double[] weights;

  public void setWeightVector(int rowCount) {
    i = 0;
    weights = new double[rowCount];
  }

  public double[] getWeights() {
    return weights;
  }

  public void setSquashingFunction(DoubleFunction squashingFunction) {
    this.squashingFunction = squashingFunction;
  }

  @Override
  public void readFields(DataInput in) throws IOException {
    id = in.readInt();
    output = in.readDouble();
    weight = in.readDouble();
    delta = in.readDouble();

    momentumWeight = in.readDouble();
    learningRate = in.readDouble();
  }

  @Override
  public void write(DataOutput out) throws IOException {
    out.writeInt(id);
    out.writeDouble(output);
    out.writeDouble(weight);
    out.writeDouble(delta);
    
    out.writeDouble(momentumWeight);
    out.writeDouble(learningRate);
  }

}