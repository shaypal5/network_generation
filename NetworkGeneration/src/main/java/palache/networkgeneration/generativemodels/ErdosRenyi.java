package palache.networkgeneration.generativemodels;

import java.util.Arrays;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.function.IntSupplier;

import palache.commons.arrays.ArraysUtils;
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
   
   /**
    * @param n The number of nodes in the resulting network.
    * @param degreeSupplier An object that can supply at least n integers to be used as the degrees of nodes
    * in the network.
    * @return a network generated using the G(n,k) configuration model using the given parameters.
    */
   public static Network getGnkNetworkWithConfigurationModel(int n, IntSupplier degreeSupplier){
      int sumDegrees = 0; //# of edges in the network
      int[] degrees = new int[n];
      for (int i=0; i<n; i++){
         int currentDegree = degreeSupplier.getAsInt();
         sumDegrees += currentDegree;
         degrees[i] = currentDegree;
      }
      if ((sumDegrees % 2) != 0){
         degrees[n-1]++;
      }
      System.out.println("sumDegrees = "+sumDegrees);
      int m = sumDegrees / 2; //# of edges in the network
      Network network;
      if (sumDegrees/(double)n > (Math.log(n)/n) ){ // if network is more likely to be dense
         double[][] edgeMatrix = new double[n][n];
         generateGnkNetworkByDegreeSequence(n, m, degrees, (i,j) -> edgeMatrix[i][j] = 1);
         network = new MatrixBasedWeightedNetwork(n,edgeMatrix);
      } else {
         NeighborListBasedWeightedNetworkBuilder netBuilder = 
               NeighborListBasedWeightedNetworkBuilder.getNewBuilder(n);
         generateGnkNetworkByDegreeSequence(n, m, degrees, (i,j) -> netBuilder.addWeightedEdge(i, j, 1));
         network = netBuilder.buildNetwork();
      }
      return network;
   }
   
   private static void generateGnkNetworkByDegreeSequence(int n, int m, int[] degrees, BiConsumer<Integer,Integer> addEdgeFunction){
      System.out.println("n="+n+", m="+m+", degrees="+Arrays.toString(degrees));
//      throw new RuntimeException("n="+n+", m="+m+", degrees="+degrees);
      int[] stubs = new int[2*m];
      int stubCounter = 0;
      for (int i=0; i<n; i++){
         System.out.println("i="+i+", n="+n+", stubCounter="+stubCounter);
         int degCounter = 0;
         while (degCounter < degrees[i]){
            try {               
               stubs[stubCounter] = i;
            } catch (ArrayIndexOutOfBoundsException e){
               System.out.println("Exception with n="+n+", m="+m+", i="+i+", stubCounter="+stubCounter);
               return;
            }
            stubCounter++;
            degCounter++;
         }
      }
      ArraysUtils.shuffleIntArray(stubs);
      for (int j=0; j<2*m; j+=2){
         addEdgeFunction.accept(stubs[j], stubs[j+1]);
      }
   }
}