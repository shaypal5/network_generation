package palache.networkgeneration.networks;

import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;

/**
 * @author ShayPalachy
 */
public class NeighborListBasedWeightedNetwork implements WeightedNetwork {

   /*----=  Nested Classses  =-----*/
   
    /**
     * A convenient builder for a NeighborListBasedWeightedNetwork.
    * @author ShayPalachy
    */
   public static class NeighborListBasedWeightedNetworkBuilder {
      
      private Int2DoubleMap[] tmpNeighborLists;
      private int tmpNumOfNodes;
      private boolean finalized = false;
       
      private NeighborListBasedWeightedNetworkBuilder(int numOfNodes){
         this.tmpNumOfNodes = numOfNodes;
         tmpNeighborLists = new Int2DoubleMap[numOfNodes];
      }
       
       /**
       * @param numOfNodes The number of nodes in the NeighborListBasedWeightedNetwork to build.
       * @return a builder for a NeighborListBasedWeightedNetwork of the given size.
       */
      public static NeighborListBasedWeightedNetworkBuilder getNewBuilder(int numOfNodes){
         return new NeighborListBasedWeightedNetworkBuilder(numOfNodes);
      }
      
      private void addWeightedNeighbor(int i, int j, double weight){
         if (tmpNeighborLists[i] == null){
            tmpNeighborLists[i] = new Int2DoubleOpenHashMap();
            tmpNeighborLists[i].defaultReturnValue(0);
         }
         tmpNeighborLists[i].put(j, weight);
      }
      
      /**
       * @param i
       * @param j
       * @param weight
       * @return this NeighborListBasedWeightedNetworkBuilder object.
       */
      public NeighborListBasedWeightedNetworkBuilder addWeightedEdge(int i, int j, double weight){
         if (!finalized){
            addWeightedNeighbor(i,j,weight);
            addWeightedNeighbor(j,i,weight);
         }
         return this;
      }
       
       /**
       * @return a NeighborListBasedWeightedNetwork with the edge that were added to this builder so far.
       */
      public NeighborListBasedWeightedNetwork buildNetwork(){
         finalized = true;
         return new NeighborListBasedWeightedNetwork(this.tmpNumOfNodes, this.tmpNeighborLists);
       }
    }
   
   
   private int numOfNodes;
   private Int2DoubleMap[] neighborLists;

   /*----=  Constructors  =-----*/
   
   /**
    * @param numOfNodes The number of nodes in this network.
    * @param neighborLists 
    */
   NeighborListBasedWeightedNetwork(int numOfNodes, Int2DoubleMap[] neighborLists){
      this.numOfNodes = numOfNodes;
      this.neighborLists = neighborLists;
   }
   

   /*----=  Instance Methods  =-----*/

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getNetworkSize()
    */
   @Override
   public int getNetworkSize() {
      return numOfNodes;
   }


   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getNeighborIndices(int)
    */
   @Override
   public IntSet getNeighborIndices(int nodeIndex) {
      return neighborLists[nodeIndex].keySet();
   }


   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#edgeExists(int, int)
    */
   @Override
   public boolean edgeExists(int i, int j) {
      return neighborLists[i].keySet().contains(j);
   }

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getEdgeWeight(int, int)
    */
   @Override
   public double getEdgeWeight(int i, int j) {
         return neighborLists[i].get(j);
   }


   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.WeightedNetwork#getSparseEdgeWeights(int)
    */
   @Override
   public Int2DoubleMap getSparseEdgeWeights(int nodeIndex) {
      return neighborLists[nodeIndex];
   }


   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.WeightedNetwork#getDenseEdgeWeights(int)
    */
   @Override
   public double[] getDenseEdgeWeights(int nodeIndex) {
      double[] weightsArray = new double[numOfNodes];
      if (neighborLists[nodeIndex] != null){         
         for (int index : neighborLists[nodeIndex].keySet()){
            weightsArray[index] = neighborLists[nodeIndex].get(index);
         }
      }
      return weightsArray;
   }
}
