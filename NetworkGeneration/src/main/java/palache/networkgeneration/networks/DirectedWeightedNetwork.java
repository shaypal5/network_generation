package palache.networkgeneration.networks;

import it.unimi.dsi.fastutil.ints.Int2DoubleMap;

/**
 * @author ShayPalachy
 *
 */
public interface DirectedWeightedNetwork extends WeightedNetwork {
   
   /**
    * @param nodeIndex The index of the node.
    * @return a mapping of node indices to the weight of the edge going from the given node to them, if any.
    * Unconnected nodes have no corresponding entries.
    */
   public Int2DoubleMap getSparseOutgoingEdgeWeights(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the node.
    * @return a mapping of node indices to the weight of the edge going from them to the given node, if any.
    * Unconnected nodes have no corresponding entries.
    */
   public Int2DoubleMap getSparseIngoingEdgeWeights(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the node.
    * @return an double array where the weight of the edge going from the given node to node i is in the i'th 
    * cell.
    */
   public double[] getDenseOutgoingEdgeWeights(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the node.
    * @return an double array where the weight of the edge going from node i to the given node is in the i'th 
    * cell.
    */
   public double[] getDenseIngoingEdgeWeights(int nodeIndex);

}
