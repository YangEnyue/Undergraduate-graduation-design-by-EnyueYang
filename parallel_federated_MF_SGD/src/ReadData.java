import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class ReadData {
	public static void readData() throws IOException {
		// some statistics, start from index "1"
		Data.userRatingNumTrain = new int[Data.n + 1];
		Data.itemRatingNumTrain = new int[Data.m + 1];
		Data.userRatingSumTrain = new float[Data.n + 1];
		Data.itemRatingSumTrain = new float[Data.m + 1];
		
		// add
		// model parameter to learn, start from index "1"
		// Data.U = new float[Data.n + 1][Data.d];
		// Data.V = new float[Data.m + 1][Data.d];
		
        // the number of training records
        Data.num_train = 0;
        BufferedReader brTrain = new BufferedReader(new FileReader(Data.fnTrainData));
        String line = null;
        while( (line = brTrain.readLine()) != null ) {
        	Data.num_train += 1;
        }
        brTrain.close();
        
        //
    	Data.indexUserTrain = new int[Data.num_train]; // start from index "0"
    	Data.indexItemTrain = new int[Data.num_train];
    	Data.ratingTrain = new float[Data.num_train];
        //
    	int id_case=0;
 
    	brTrain = new BufferedReader(new FileReader(Data.fnTrainData));
    	line = null;
    	while( (line = brTrain.readLine()) != null ) {
    		String[] terms = line.split("\\s+|,|;");
    		int userID = Integer.parseInt(terms[0]);
    		int itemID = Integer.parseInt(terms[1]);
    		float rating = Float.parseFloat(terms[2]);
    		
    		Data.indexUserTrain[id_case] = userID;
    		Data.indexItemTrain[id_case] = itemID;
    		Data.ratingTrain[id_case] = rating;
    		id_case += 1;
    		
    		//
    		Data.userRatingSumTrain[userID] += rating;
    		Data.userRatingNumTrain[userID] += 1;
    		Data.itemRatingSumTrain[itemID] += rating;
    		Data.itemRatingNumTrain[itemID] += 1;
    		
    		// add
    		if( Data.TrainData.containsKey(userID) ) {
    			HashMap<Integer, Float> user_record = Data.TrainData.get(userID);
    			user_record.put(itemID, rating);
    			Data.TrainData.put(userID, user_record);   			
    		}else {
    			HashMap<Integer, Float> user_record = new HashMap<Integer, Float>();
    			user_record.put(itemID, rating);
    			Data.TrainData.put(userID, user_record);
    			
    		}
    		
    		// add 
    		if( Data.TrainData_item.containsKey(itemID) ) {
    			HashMap<Integer, Float> item_record = Data.TrainData_item.get(itemID);
    			item_record.put(userID, rating);
    			Data.TrainData_item.put(itemID, item_record);
    		}else {
    			HashMap<Integer, Float> item_record = new HashMap<Integer, Float>();
    			item_record.put(userID, rating);
    			Data.TrainData_item.put(itemID, item_record);
    		}
    	}
    	brTrain.close();
        
		try {
			FileWriter fw = new FileWriter(new File(Data.fnOutputData),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write( "num_train: " + Data.num_train + "\t\n" );
			bw.write( "Finished reading the target training data" + "\t\n" );
			bw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        // the number of test records
        Data.num_test = 0;
        BufferedReader brTest = new BufferedReader(new FileReader(Data.fnTestData));
        line = null;
        while( (line = brTest.readLine()) != null ) {
        	Data.num_test += 1;
        }
        brTest.close();
        
		//
    	Data.indexUserTest = new int[Data.num_test];
    	Data.indexItemTest = new int[Data.num_test];
    	Data.ratingTest = new float[Data.num_test];
    	
    	id_case = 0;
    	brTest = new BufferedReader(new FileReader(Data.fnTestData));
    	line = null;
    	while( (line = brTest.readLine()) != null ) {
    		String[] terms = line.split("\\s+|,|;");
    		int userID = Integer.parseInt(terms[0]);
    		int itemID = Integer.parseInt(terms[1]);
    		float rating = Float.parseFloat(terms[2]);
    		Data.indexUserTest[id_case] = userID;
    		Data.indexItemTest[id_case] = itemID;
    		Data.ratingTest[id_case] = rating;
    		id_case += 1;
    	}
    	brTest.close();
		
    	try {
			FileWriter fw = new FileWriter(new File(Data.fnOutputData),true);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write( "num_test: " + Data.num_test + "\t\n" );
			bw.write( "Finished reading the target test data" + "\t\n" );
			bw.close();
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

