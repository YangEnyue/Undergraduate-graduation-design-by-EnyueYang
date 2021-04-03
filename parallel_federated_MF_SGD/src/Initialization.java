import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class Initialization {
	public static void initialization() {
		
		Data.bloomFilter = 
				BloomFilter.create(Funnels.integerFunnel(), Data.insertions, Data.fpp);
		
		Data.server = new Server();
		Data.clients = new Client[ Data.n + 1 ];
		Data.U = new float[Data.n + 1][Data.d];
		for( int u=0; u <= Data.n; u++) {
			Data.clients[u] = new Client();
		}
		//
		Server.server_initialization();
		for( int u=0; u <= Data.n; u++) {
			Data.clients[u].client_initialization(u);
			if( u==0 )
				continue;
		}
	}
}
