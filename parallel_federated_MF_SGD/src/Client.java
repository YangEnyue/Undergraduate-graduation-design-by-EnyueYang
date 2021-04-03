import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Client{
	private int userID;
	private HashMap<Integer, Float> user_record;
	private HashSet<Integer> I_u = null;
	private HashSet<Integer> Ip_u = null;
	private int[] Ipp_u;
	private float [][] V_u;
	private float[][] Gradient_V;
	private float p_1, p_2, p_3, p_4; 
	private float p_5;
	

	public void client_initialization(int u) {
		if( u==0 )
			return;
		userID = u;
		// p_1 = p_2 = p_3 = p_4 = 0.5;
		/*p_1 = Data.p_1;
		p_2 = Data.p_2;
		p_3 = Data.p_3;
		p_4 = Data.p_4;*/
		p_5 = (float) 0.5;
		
		p_1 = (float) 1.0;
		p_2 = (float) 0.0;
		p_3 = (float) 1.0;
		p_4 = (float) 0.0;
		// 
		I_u = new HashSet<Integer>();
		
		user_record = Data.TrainData.get(userID);
		if( user_record==null ) {
			// System.out.printf("the client %d has no any record!\n", userID);
		}else {
			for( Map.Entry<Integer, Float> record : user_record.entrySet() ) {
				I_u.add( record.getKey().intValue() );
			}
		}
		// 
		Ip_u = new HashSet<Integer>();
		randomized_response();
		//
		for( int f=0; f < Data.d; f++) {
			Data.U[userID][f] = (float)( (Math.random()-0.5)*0.01 );
		}
	}
	
	public void get_global_index_set() {
		// add I_u into bloom filter
		if( Data.TrainData.containsKey(userID) ) {
			HashMap<Integer, Float> user_record = Data.TrainData.get(userID);
			for( Map.Entry<Integer, Float> record : user_record.entrySet() ) {
				Data.bloomFilter.put(record.getKey());
			}
		}else {
			// System.out.printf("client %d's TrainData is null\n", userID);
		}
		// Note:
		// Due to use com.google.common.hash.BloomFilter.jar, which is easy to implementation bloom filter,
		// so the security aggregation steps omitted here
		// The effect of the filter on the experimental results can be seen in a other code
		// ...
		return;
	}
	
	// Permanent randomized response
	public void randomized_response() {
		for( int itemID = 1; itemID <= Data.m; itemID++ ) {
			if( I_u.contains(itemID)==true ) {
				float p = (float) Math.random(); 
				if( p <= p_1 ) {
					Ip_u.add(itemID);
				}
			}else {
				float p = (float) Math.random(); 
				if( p <= p_2 ) {
					Ip_u.add(itemID);
				}
			}
		}
	}
	// Instantaneous randomized response
	public int[] instantaneous_randomized_response() {
		if( Ip_u.isEmpty() ) {
			// System.out.printf("Ip_u of the client %d is empty\n", userID);
		}
		HashSet<Integer> tmp = new HashSet<Integer>();
		for( int itemID = 1; itemID <= Data.m; itemID++ ) {
			if( Ip_u.contains(itemID)==true ) {
				float p = (float) Math.random(); 
				if( p <= p_3 ) {
					tmp.add(itemID);
				}
			}else {
				float p = (float) Math.random(); 
				if( p <= p_4 ) {
					tmp.add(itemID);
				}
			}
		}
		//
		Ipp_u = new int[tmp.size()];
		int cnt = 0;
		Iterator<Integer> it = tmp.iterator();
		while( it.hasNext() ) {
			Ipp_u[cnt++] = it.next();
		}
		return Ipp_u;	
	}
	
	public int[] get_Ipp_u() {
		return Ipp_u;
	}
	
	// get sub-model from server
	public void get_submodel(float V[][]){
		V_u = new float[Ipp_u.length][Data.d];
		
		for( int i=0; i < Ipp_u.length; i++ ) {
			
			for( int f=0; f < Data.d; f++) {
				V_u[i][f] = V[ Ipp_u[i] ][f];
			}
			
		}
		
	}
	
	public void train() {

		HashMap<Integer, Float> TrainData_u = Data.TrainData.get(userID);
		Gradient_V = new float[Ipp_u.length][Data.d];
		for( int i=0; i < Ipp_u.length; i++ ) {
			for( int f=0; f < Data.d; f++ ) {
				Gradient_V[i][f] = (float) 0.0;
			}
		}
		//
		if( TrainData_u==null ) {
			return;
		}
		
		for( int i=0; i< Ipp_u.length; i++ ) {
			if( TrainData_u.containsKey(Ipp_u[i]) ) {
				int itemID = Ipp_u[i];
				float rating = TrainData_u.get(itemID);
				//
				float pred = 0;
				for( int f = 0; f < Data.d; f++) {
					pred += Data.U[userID][f] * V_u[i][f];
				}
				if( pred>5.0 ) {
					pred = (float) 5.0;
				}else if( pred<1 ) {
					pred = (float) 1.0;
				}
				
				float error = rating- pred;
				
				// Calculate the gradient $V$ and $U$
				for( int f=0; f < Data.d; f++ ) {
					float grad_U_f = (float)(-error * V_u[i][f] + Data.alpha_u *  Data.U[userID][f]);
					float grad_V_f = (float)(-error * Data.U[userID][f] + Data.alpha_v * V_u[i][f]);
				
					Data.U[userID][f] =  Data.U[userID][f] - Data.gamma * grad_U_f;
					// restore gradient $V$
					Gradient_V[i][f] = grad_V_f;
				}
			}
		}
		return;
		
	}
	// Secret Sharing
	public void secret_sharing() {
		for( int i=0; i< Ipp_u.length; i++ ) {
			HashSet<Integer> userIDset = Data.server.item_userIDset.get(Ipp_u[i]);
			Iterator<Integer> iterator = userIDset.iterator();
			while( iterator.hasNext() ) {
				int other_userID = iterator.next();
				if( other_userID != userID ) {
					float p = (float) Math.random(); 
					if( p <= p_5 ) {
						float[] share_gradient = new float[Data.d];
						
						for( int f=0; f<Data.d; f++ ) {
							share_gradient[f] = (float) ( Math.random()-0.5 );
							Gradient_V[i][f] -= share_gradient[f];
						}
						Data.clients[other_userID].receive_secret(share_gradient, Ipp_u[i]);
					}
				}
			}
		}
	}
	public void receive_secret(float[] share_gradient, int itemID) {
		for( int i=0; i<Ipp_u.length; i++) {
			if( Ipp_u[i]==itemID ) {
				for( int f=0; f<Data.d; f++) {
					Gradient_V[i][f] += share_gradient[f];		
				}
			}
		}
	}
	
	
	public float[] Get_Gradient(int itemID) {
		for( int i=0; i < Ipp_u.length; i++) {
			if( Ipp_u[i] == itemID)
				return Gradient_V[i];
		}
		return null;
	}
	
	
}
