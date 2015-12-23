package palache.networkgeneration.networks;

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
   public IntSet getNeighborIndices(int nodeIndex);
   
   /**
    * @param i The first node.
    * @param j The second node.
    * @return true if edge i->j exists in the network, false otherwise.
    */
   public boolean edgeExists(int i, int j);
   
//   /**
//    * @return a matrix where cell [i][j] contains true if there's an edge going from node i to node j,
//    * false otherwise.
//    */
//   public boolean[][] getEdgesMatrix();
   
}