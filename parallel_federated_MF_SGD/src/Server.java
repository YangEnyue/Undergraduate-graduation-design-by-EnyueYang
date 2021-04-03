import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class Server {
	public static int selected_clients[];
	public static HashSet<Integer> I_t;
	
	// (item, userID_set)
	public static HashMap<Integer, HashSet<Integer>> item_userIDset;
	
	public static void server_process(int iterations) throws IOException {
		// each iterations
		for( int iter = 0; iter <= iterations; iter++ ) {
			if( iter %600 == 0 ) {
			// if( iter % 10 == 0 ) {
				FileWriter fw = new FileWriter(new File(Data.fnOutputData),true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Iter:" + Integer.toString(iter) + "| ");
				bw.close();
				Test.test();
			}
			// add
			/*
			if( Math.abs(Data.RMSE_new-Data.RMSE_old)<0.001 && Math.abs(Data.MAE_new-Data.MAE_old)<0.001 && Math.abs(Data.MAE_new-0.72287834)<0.005 ) {
				
				try {
					FileWriter fw = new FileWriter(new File(Data.fnOutputData),true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write( "MAE_last:" + Float.toString(Data.MAE_new) +  "| RMSE_last:" + Float.toString(Data.RMSE_new) + "\t\n" );
					
					bw.write( "T:" + Integer.toString(iter) + "\t\n" );
					bw.close();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				break;
			}*/
			
			// 1. select k clients
			select_k_clients(Data.k);
			
			// 2. get the real global item index sets of k clients
			get_global_indexset();
			
			// statistics item_userIDset of Ipp_u when the selected clients make the instantaneous randomized response
			statistics_item_userIDset();

			// 3. Response client u and return $V_{I''_u}$
			response_client();

			// 4. select gradient of clients and train
			train();
			
			//
			if( iter % 600 == 0)
				Data.gamma = (float)(Data.gamma * 0.9);	
		}		
	}
	
	
	
	public static void server_initialization() {
		// k clients' index
		selected_clients = new int[Data.k];
		// the global index set in each iterations
		I_t = new HashSet<Integer>();
		// (item, userID_set)
		item_userIDset = new HashMap<Integer, HashSet<Integer>>();
		
		// Initialization the global item latent vector $V$
		Data.V = new float[Data.m + 1][Data.d];
		
		for( int i = 1; i < Data.m+1; i++) {
			for( int f=0; f < Data.d; f++) {
				Data.V[i][f] = (float)( (Math.random()-0.5)*0.01 );
			}
		}
		
	}
	
	private static void select_k_clients(int k) {
		
		int count = 0;
		while( count < k ) {
			int num = (int)( 1 +  Math.random() * Data.n) ; // [1, Data.n]
			boolean flag = true;
			for( int j=0; j < count; j++ ) {
				if( num == selected_clients[j] ) {
					flag = false;
					break;
				}
			}
			if( flag ) {
				selected_clients[count] = num;
				count++;
			}
		}
		return;
	}
	
	private static void get_global_indexset(){
		I_t.clear();
		Data.bloomFilter = 
				BloomFilter.create(Funnels.integerFunnel(), Data.insertions, Data.fpp);
		// 
		for( int i=0; i < Data.k; i++ ) {
			Data.clients[ selected_clients[i] ].get_global_index_set();
		}
		
		for( int itemID=1; itemID <= Data.m; itemID++) {
			if( Data.bloomFilter.mightContain(itemID) )
				I_t.add(itemID);
		}
		return;
	}
	
	public static void statistics_item_userIDset() {
		item_userIDset.clear();
		for( int i=0; i < Data.k; i++ ) {
			int[] Ipp_u = Data.clients[ selected_clients[i] ].instantaneous_randomized_response();
			for( int j =0; j<Ipp_u.length; j++ ) {
				
				if( item_userIDset.containsKey(Ipp_u[j]) ) {
					HashSet<Integer> userIDset = item_userIDset.get(Ipp_u[j]);
					userIDset.add( selected_clients[i] );
					item_userIDset.put(Ipp_u[j], userIDset);
				}else {
					HashSet<Integer> userIDset = new HashSet<Integer>();
					userIDset.add( selected_clients[i] );
					item_userIDset.put(Ipp_u[j], userIDset);
				}
			}
		}
		return;
	}
	
	public static void response_client() {
		for( int i=0; i<Data.k; i++ ) {
			// get Ipp_u
			// Ipp_u[i] = Data.clients[selected_clients[i]].get_Ipp_u();
			
			// send sub-model to k clients
			// clients train parameter locally
			Data.clients[selected_clients[i]].get_submodel(Data.V);
			Data.clients[selected_clients[i]].train();
		}
		// Note:
		// 这一步是无损的，而且由于时间成本，除了在验证密码共享的无损性外，其余实验数据均不运行此步骤
/*
		// Secret Sharing
		for( int i=0; i<Data.k; i++ ) {
			Data.clients[selected_clients[i]].secret_sharing();
		}
*/
		return;
	}
	
	
	public static void train() {
		Iterator<Integer> iterator = I_t.iterator();
		while( iterator.hasNext() ) {
			int itemID = iterator.next();
			float[] gradient = new float[Data.d];
			int count = 0;
			for( int f=0; f < Data.d; f++) {
				gradient[f] = (float) 0.0;
			}
			for( int i=0; i < Data.k; i++ ) {
				if( ! If_contain_item(Data.clients[selected_clients[i]].get_Ipp_u(), itemID) ) {
					continue;
				}
				count++;
				float[] gradient_u = Data.clients[selected_clients[i]].Get_Gradient(itemID);
				for( int f = 0; f < Data.d; f++) {
					gradient[f] += gradient_u[f];
				}
			}
			if( count==0 ) {
				// System.out.print("***\n" );
			}else {
				// update the gradient $V_itemID$
				for( int f = 0; f < Data.d; f++) {
					gradient[f] = gradient[f] / (float)count;
					Data.V[itemID][f] = Data.V[itemID][f] - Data.gamma*gradient[f];
				}
			}
		}
	}
	public static boolean If_contain_item(int[] I_userID, int itemID) {
		for( int i=0; i < I_userID.length; i++ ) {
			if( I_userID[i] == itemID )
				return true;
		}
		return false;
	}

}
