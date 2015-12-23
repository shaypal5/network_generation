package palache.networkgeneration.networks;

import it.unimi.dsi.fastutil.ints.Int2DoubleMap;

/**
 * @author ShayPalachy
 */
public interface WeightedNetwork extends Network {
   
   /**
    * @param i the index of the first node.
    * @param j the index of the second node.
    * @return The weight of the edge going from node i to node j. Zero represents a missing edge.
    */
   public double getEdgeWeight(int i, int j);
   
   /**
    * @param nodeIndex The index of the node.
    * @return a mapping of node indices to the weight of the edge they share with the given node, if any.
    * Unconnected nodes have no corresponding entries.
    */
   public Int2DoubleMap getSparseEdgeWeights(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the node.
    * @return a double array where the weight of the edge the given node shares with node i is in the i'th 
    * cell.
    */
   public double[] getDenseEdgeWeights(int nodeIndex);
   
//   /**
//    * @return a matrix where cell [i][j] contains the weight of the edge going from node i to node j.
//    */
//   public double[][] getEdgesWeightMatrix();

}
