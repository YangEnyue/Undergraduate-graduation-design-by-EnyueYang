import java.util.HashMap;
import com.google.common.hash.BloomFilter;

public class Data {
	// Configurations

	// the number of latent demensions
	public static int d =20;
	
	// tradeoff
	public static float alpha = 0.001f;
	public static float alpha_u = 0.001f;
	public static float alpha_v = 0.001f;
	
	// learning rate
	public static float gamma = 0.01f;
	
	// input data files
	public static String fnTrainData = "";
	public static String fnTestData = "";
	public static String fnOutputData = "";
	
	// 
	public static int n = 0;	// number of users
	public static int m = 0;	// numnber of items
	public static int num_train = 0;	// number of the total (user,item) pairs in training data
	public static int num_test = 0;		// number of the total (user,item) pairs in test data
	public static int num_iterations = 0;	// scan number over the whole data
	
	public static float MinRating = 1.0f;	// minimum rating value (0.5 for ML10M, Flixter; 1 for Netflix)
	public static float MaxRating = 5.0f;	// maximum rating value
	
	// training data
	public static HashMap<Integer, HashMap<Integer, Float>> TrainData = new HashMap<Integer, HashMap<Integer, Float>>();
	public static HashMap<Integer, HashMap<Integer, Float>> TrainData_item = new HashMap<Integer, HashMap<Integer, Float>>();
	
	// training data used for uniformly random sampling
	public static int[] indexUserTrain;	// start from index "0"
	public static int[] indexItemTrain; // start from index "0"
	public static float[] ratingTrain;	// start from index "0"
	
	// test data
	public static int[] indexUserTest;
	public static int[] indexItemTest;
	public static float[] ratingTest;
	
	// some statistics, start from index "1"
	public static int[] userRatingNumTrain;
	public static int[] itemRatingNumTrain;
	public static float[] userRatingSumTrain;
	public static float[] itemRatingSumTrain;
	public static int[][] user_graded_rating_number;
	public static int[] user_rating_number;
	
	// model parameters to learn, start from index "1"
	public static float[][] U;
	public static float[][] V;
	
	// add
	
	// the number of users in each iteration
	public static int k = 10;
	
	// bloom filter
	public static int insertions = 10;
	public static float fpp = (float) 0.01;
	public static BloomFilter<Integer> bloomFilter;
	
	//
	public static Server server;
	public static Client[] clients;
	
	// 
	public static float p_1 = (float) 1.0;
	public static float p_2 = (float) 0.001;
	public static float p_3 = (float) 1.0;
	public static float p_4 = (float) 0.001; 
	
	//
	public static float MAE_new = (float) 0.0;
	public static float RMSE_new = (float) 0.0;

	public static float MAE_old = (float) 0.0;
	public static float RMSE_old = (float) 0.0;

}
