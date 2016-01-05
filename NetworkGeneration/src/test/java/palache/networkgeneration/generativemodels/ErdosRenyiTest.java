package palache.networkgeneration.generativemodels;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.function.IntSupplier;

import org.apache.commons.math3.distribution.ParetoDistribution;
import org.junit.Test;

import palache.networkgeneration.networks.MatrixBasedWeightedNetwork;
import palache.networkgeneration.networks.NeighborListBasedWeightedNetwork;
import palache.networkgeneration.networks.Network;
import palache.networkgeneration.networks.WeightedNetwork;

/**
 * @author ShayPalachy
 *
 */
public class ErdosRenyiTest {
   
   @SuppressWarnings("unused")
   //Only used to init static  members of the ErdosRenyi class.
   private static Network initNet = ErdosRenyi.getGnpNetwork(2, 0);
   
   private static int NUM_OF_BUILDS_FOR_BUILD_TIME = 1000;

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGnpDenseNetworkBuildTime() {
      int n = 100;
      for (int i=0; i<NUM_OF_BUILDS_FOR_BUILD_TIME; i++){         
         ErdosRenyi.getGnpNetwork(n, 0.9);
      }
   }

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGetGnpNetworkWithDenseNetwork() {
      System.out.println("===================testGetGnpNetworkWithDenseNetwork()=================");
      int n = 100;
      Network net = ErdosRenyi.getGnpNetwork(n, 0.9);
      assertTrue(net instanceof MatrixBasedWeightedNetwork);
//      MatrixBasedWeightedNetwork weightedNet = (MatrixBasedWeightedNetwork) net;
//      for (int i=0; i<n; i++){
//         System.out.println(Arrays.toString(weightedNet.getDenseEdgeWeights(i)));
//      }
      System.out.println("=====================================================");
   }

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGnpSparseNetworkBuildTime() {
      int n = 100;
      double p = 0.9*(1.0/n);
      for (int i=0; i<NUM_OF_BUILDS_FOR_BUILD_TIME; i++){          
         ErdosRenyi.getGnpNetwork(n, p);
      }
   }

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGetGnpNetworkWithSparseNetwork() {
      System.out.println("==============testGetGnpNetworkWithSparseNetwork()======================");
      int n = 100;
      double p = 0.9*(1.0/n);
      Network net = ErdosRenyi.getGnpNetwork(n, p);
      assertTrue(net instanceof NeighborListBasedWeightedNetwork);
//      NeighborListBasedWeightedNetwork weightedNet = (NeighborListBasedWeightedNetwork) net;
//      for (int i=0; i<n; i++){
//         System.out.println(Arrays.toString(weightedNet.getDenseEdgeWeights(i)));
//      }
      System.out.println("=====================================================");
   }

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGetGnkNetworkWithConfigurationModel() {
      System.out.println("===========================testGetGnkNetworkWithConfigurationModel==========================");
      int n = 100;
      IntSupplier degreeSupplier = new IntSupplier(){         
         private ParetoDistribution dist = new ParetoDistribution(1,2.4);
         @Override
         public int getAsInt() {
            return Math.max(((int)Math.floor(dist.sample()))-1,0);
         }         
      };
//      ParetoDistribution dist = new ParetoDistribution(1,2.9);
//      for (int d=0; d<1000; d++){
//         System.out.print(degreeSupplier.getAsInt()+",");
//      }
//      System.out.println();
//      for (int d=0; d<1000; d++){
//         System.out.print(dist.sample()+",");
//      }
//      System.out.println();
      Network net = ErdosRenyi.getGnkNetworkWithConfigurationModel(n, degreeSupplier);
      assertTrue(net instanceof WeightedNetwork);
      WeightedNetwork weightedNet = (WeightedNetwork) net;
      for (int i=0; i<n; i++){
         System.out.println(Arrays.toString(weightedNet.getDenseEdgeWeights(i)));
      }
      System.out.println("=====================================================");
   }

}