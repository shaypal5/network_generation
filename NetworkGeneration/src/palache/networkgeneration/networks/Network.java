package palache.networkgeneration.networks;

import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import it.unimi.dsi.fastutil.ints.IntSet;

/**
 * An interface that represents a network - a collection of nodes connected with edges. Edges might be
 * directional, but might also be undirectional. Similarly, edges might be weighted, where an unweighted
 * network will be represented by using a constant weight.
 * @author ShayPalachy
 */
public interface Network {

   /**
    * @return the number of nodes in this network.
    */
   public int getNetworkSize();
   
   /**
    * @param nodeIndex The index of the node.
    * @return a set of the indices of all neighbors of the node with the given index. Neighbors are nodes with
    * both incoming 
    */
   public IntSet getNodeNeighborIndices(int nodeIndex);
   
   /**
    * @param i the index of the first node.
    * @param j the index of the second node.
    * @return The weight of the edge going from node i to node j. Zero represents a missing edge.
    */
   public double getEdgeWeight(int i, int j);
   
   /**
    * @param nodeIndex The index of the node.
    * @return an int array where the weight of the edge going from the given node to node i is in the i'th 
    * cell.
    */
   public Int2DoubleMap getSparseOutgoingEdgeWeights(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the node.
    * @return an int array where the weight of the edge going from the given node to node i is in the i'th 
    * cell.
    */
   public Int2DoubleMap getSparseIngoingEdgeWeights(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the node.
    * @return an int array where the weight of the edge going from the given node to node i is in the i'th 
    * cell.
    */
   public double[] getDenseOutgoingEdgeWeights(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the node.
    * @return an int array where the weight of the edge going from node i to the given node is in the i'th 
    * cell.
    */
   public double[] getDenseIngoingEdgeWeights(int nodeIndex);
   
   /**
    * @return a matrix where cell [i][j] contains the weight of the edge going from node i to node j.
    */
   public double[][] getEdgesWeightMatrix();
   
}