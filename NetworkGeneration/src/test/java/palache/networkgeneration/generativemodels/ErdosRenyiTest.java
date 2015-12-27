package palache.networkgeneration.generativemodels;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import palache.networkgeneration.networks.MatrixBasedWeightedNetwork;
import palache.networkgeneration.networks.NeighborListBasedWeightedNetwork;
import palache.networkgeneration.networks.Network;

/**
 * @author ShayPalachy
 *
 */
public class ErdosRenyiTest {
   
   @SuppressWarnings("unused")
   //Only used to init static  members of the ErdosRenyi class.
   private static Network initNet = ErdosRenyi.getGnpNetwork(2, 0);

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGnpDenseNetworkBuildTime() {
      int n = 100;
      ErdosRenyi.getGnpNetwork(n, 0.9);
   }

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGetGnpNetworkWithDenseNetwork() {
      int n = 100;
      Network net = ErdosRenyi.getGnpNetwork(n, 0.9);
      assertTrue(net instanceof MatrixBasedWeightedNetwork);
      MatrixBasedWeightedNetwork weightedNet = (MatrixBasedWeightedNetwork) net;
      for (int i=0; i<n; i++){
         System.out.println(Arrays.toString(weightedNet.getDenseEdgeWeights(i)));
      }
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
      ErdosRenyi.getGnpNetwork(n, p);
   }

   /**
    * Test method for {@link palache.networkgeneration.generativemodels.ErdosRenyi#getGnpNetwork(int, double)}.
    */
   @SuppressWarnings("static-method")
   @Test
   public void testGetGnpNetworkWithSparseNetwork() {
      int n = 100;
      double p = 0.9*(1.0/n);
      Network net = ErdosRenyi.getGnpNetwork(n, p);
      assertTrue(net instanceof NeighborListBasedWeightedNetwork);
      NeighborListBasedWeightedNetwork weightedNet = (NeighborListBasedWeightedNetwork) net;
      for (int i=0; i<n; i++){
         System.out.println(Arrays.toString(weightedNet.getDenseEdgeWeights(i)));
      }
   }

}