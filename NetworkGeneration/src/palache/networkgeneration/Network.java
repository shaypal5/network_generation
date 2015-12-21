package palache.networkgeneration;

import it.unimi.dsi.fastutil.ints.IntSet;

/**
 * An interface that represents a network - a collection of nodes connected with edges.
 * @author ShayPalachy
 */
public interface Network {

   /**
    * @return the number of nodes in this network.
    */
   public int getNetworkSize();
   
   /**
    * @param nodeIndex The index of the node.
    * @return a set of the index of all neighbors of the node with the given index.
    */
   public IntSet getNodeNeighbors(int nodeIndex);
   
   
}