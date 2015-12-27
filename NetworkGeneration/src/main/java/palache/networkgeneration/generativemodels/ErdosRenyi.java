package palache.networkgeneration.generativemodels;

import java.util.Random;
import java.util.function.BiConsumer;

import palache.networkgeneration.networks.MatrixBasedWeightedNetwork;
import palache.networkgeneration.networks.NeighborListBasedWeightedNetwork.NeighborListBasedWeightedNetworkBuilder;
import palache.networkgeneration.networks.Network;

/**
 * @author ShayPalachy
 */
public class ErdosRenyi {
   
   private static Random random = new Random();
   
   /*----=  Constructors  =-----*/
   
   private ErdosRenyi(){
      //hiding the constructor, as this is a factory class.
   }
   
   /*----=  Class Methods  =-----*/
   
   /**
    * @param n The number of nodes in the resulting network.
    * @param p The probability with which an edge is included in the graph.
    * @return a network generated using the Edos-Renyi G(n,p) model using the given parameters.
    */
   public static Network getGnpNetwork(int n, double p){
      Network network;
      if (p > (Math.log(n)/n) ){ // if network is more likely to be dense
         double[][] edgeMatrix = new double[n][n];
         generateGnpNetwork(n,p, (i,j) -> edgeMatrix[i][j] = 1);
         network = new MatrixBasedWeightedNetwork(n,edgeMatrix);
      } else {
         NeighborListBasedWeightedNetworkBuilder netBuilder = 
               NeighborListBasedWeightedNetworkBuilder.getNewBuilder(n);
         generateGnpNetwork(n,p, (i,j) -> netBuilder.addWeightedEdge(i, j, 1));
         network = netBuilder.buildNetwork();
      }
      return network;
   }
   
   private static void generateGnpNetwork(int n, double p, BiConsumer<Integer,Integer> addEdgeFunction){
      for (int i=0; i<n; i++){
         for (int j=0; j<n; j++){
            if (random.nextDouble() <= p){
               addEdgeFunction.accept(i, j);
            }
         }
      }
   }
   
}