package palache.networkgeneration.networks;

import it.unimi.dsi.fastutil.ints.IntSet;

/**
 * @author ShayPalachy
 */
public interface DirectedNetwork extends Network {
   
   /**
    * @param nodeIndex The index of the given node.
    * @return a set of indices of all nodes with an edge incoming from the given node.
    */
   public IntSet getOutgoingNeighborIndices(int nodeIndex);
   
   /**
    * @param nodeIndex The index of the given node.
    * @return a set of indices of all nodes with an edge outgoing to the given node.
    */
   public IntSet getIngoingNeighborIndices(int nodeIndex);

}