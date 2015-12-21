package palache.networkgeneration.networks;

import java.util.Arrays;

import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import palache.commons.arrays.ArraysUtils;

/**
 * @author ShayPalachy
 *
 */
class MatrixBasedNetwork implements Network {
   
   int numOfNodes;
   double[][] edgeWeights;

   /*----=  Constructors  =-----*/
   
   MatrixBasedNetwork(int numOfNodes, double[][] edgeWeights){
      if (edgeWeights.length != numOfNodes){
         throw new IllegalArgumentException("The first dimension of of the edge weights matrix must be "
               + "identical to the number of nodes in the instantiated network.");
      }
      for (int i=0; i<numOfNodes; i++){
         if (edgeWeights[i].length != numOfNodes){
            throw new IllegalArgumentException("The first dimension of of the edge weights matrix must be "
                  + "identical to the number of nodes in the instantiated network.");
         }         
      }
      this.numOfNodes = numOfNodes;
      this.edgeWeights = edgeWeights;
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
    * @see palache.networkgeneration.networks.Network#getNodeNeighborIndices(int)
    */
   @Override
   public IntSet getNodeNeighborIndices(int nodeIndex) {
      IntSet neighborIndices = new IntOpenHashSet();
      for (int j=0; j<numOfNodes; j++){
         if (edgeWeights[nodeIndex][j] != 0){
            neighborIndices.add(j);
         }
      }
      for (int i=0; i<numOfNodes; i++){
         if (edgeWeights[i][nodeIndex] != 0){
            neighborIndices.add(i);
         }
      }
      return neighborIndices;
   }

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getEdgeWeight(int, int)
    */
   @Override
   public double getEdgeWeight(int i, int j) {
      return edgeWeights[i][j];
   }

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getSparseOutgoingEdgeWeights(int)
    */
   @Override
   public Int2DoubleMap getSparseOutgoingEdgeWeights(int nodeIndex) {
      Int2DoubleMap outEdgeWeights = new Int2DoubleOpenHashMap();
      for (int j=0; j<numOfNodes; j++){
         if (edgeWeights[nodeIndex][j] != 0){
            outEdgeWeights.put(j, edgeWeights[nodeIndex][j]);
         }
      }
      return outEdgeWeights;
   }

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getSparseIngoingEdgeWeights(int)
    */
   @Override
   public Int2DoubleMap getSparseIngoingEdgeWeights(int nodeIndex) {
      Int2DoubleMap inEdgeWeights = new Int2DoubleOpenHashMap();
      for (int i=0; i<numOfNodes; i++){
         if (edgeWeights[i][nodeIndex] != 0){
            inEdgeWeights.put(i, edgeWeights[i][nodeIndex]);
         }
      }
      return inEdgeWeights;
   }

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getDenseOutgoingEdgeWeights(int)
    */
   @Override
   public double[] getDenseOutgoingEdgeWeights(int nodeIndex) {
      return Arrays.copyOf(edgeWeights[nodeIndex], numOfNodes);
   }

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getDenseIngoingEdgeWeights(int)
    */
   @Override
   public double[] getDenseIngoingEdgeWeights(int nodeIndex) {
      double[] inWeights = new double[numOfNodes];
      for (int i=0; i<numOfNodes; i++){
         inWeights[i] = edgeWeights[i][nodeIndex];
      }
      return inWeights;
   }

   /* (non-Javadoc)
    * @see palache.networkgeneration.networks.Network#getEdgesWeightMatrix()
    */
   @Override
   public double[][] getEdgesWeightMatrix() {
      return ArraysUtils.double2dArrayDeepCopy(edgeWeights);
   }   
   
}