import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
	public static void test() {
		// the number of test cases
		float mae = 0;
		float rmse = 0;
		
		for( int t = 0; t < Data.num_test; t++) {
			int userID = Data.indexUserTest[t];
			int itemID = Data.indexItemTest[t];
			float rating = Data.ratingTest[t];
			
			// prediction via inner product
			float pred = 0;
			for( int f = 0; f < Data.d; f++) {
				pred += Data.U[userID][f] * Data.V[itemID][f];
			}
			
			// post processing predicted rating
    		if(pred < Data.MinRating) pred = Data.MinRating;
    		if(pred > Data.MaxRating) pred = Data.MaxRating;
    		
    		float err = pred - rating;
    		mae += Math.abs(err);
    		rmse += err * err;
    		
		}
		
		Data.RMSE_old = Data.RMSE_new;
		Data.RMSE_new = (float)0.0;
		Data.MAE_old = Data.MAE_new;
		Data.MAE_new = (float) 0;
			
		
		float MAE = mae / Data.num_test;
		float RMSE = (float) Math.sqrt(rmse / Data.num_test);
		Data.MAE_new = MAE;
		Data.RMSE_new = RMSE;
		
		try {
			FileWriter fw = new FileWriter(new File(Data.fnOutputData),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write( "MAE:" + Float.toString(MAE) +  "| RMSE:" + Float.toString(RMSE) + "\t\n" );
			bw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
