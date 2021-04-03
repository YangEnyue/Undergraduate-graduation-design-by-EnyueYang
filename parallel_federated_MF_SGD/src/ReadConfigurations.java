import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReadConfigurations {
	public static void readConfigurations(String[] args) {
		// Read the configurations
		for( int k = 0; k < args.length; k++) {
			if( args[k].equals("-d") )
				Data.d = Integer.parseInt(args[++k]);
			//
			else if( args[k].equals("-alpha") )
				Data.alpha = Float.parseFloat(args[++k]);
			else if( args[k].equals("-alpha_u") )
				Data.alpha_u = Float.parseFloat(args[++k]);
			else if( args[k].equals("-alpha_v") )
				Data.alpha_v = Float.parseFloat(args[++k]);
			
			//
			else if( args[k].equals("-gamma") )
				Data.gamma = Float.parseFloat(args[++k]);
			//
			else if( args[k].equals("-fnTrainData") )
				Data.fnTrainData = args[++k];
			else if( args[k].equals("-fnTestData") )
				Data.fnTestData = args[++k];
			else if( args[k].equals("-fnOutputData") )
				Data.fnOutputData = args[++k];
			//
			else if( args[k].equals("-n"))
				Data.n = Integer.parseInt(args[++k]);
			else if( args[k].equals("-m"))
				Data.m = Integer.parseInt(args[++k]);
			else if( args[k].equals("-num_iterations"))
				Data.num_iterations = Integer.parseInt(args[++k]);
			else if( args[k].equals("-MinRating") )
				Data.MinRating = Float.parseFloat(args[++k]);
			else if( args[k].equals("-MaxRating") )
				Data.MaxRating = Float.parseFloat(args[++k]);
			// add
			else if( args[k].equals("-k") )
				Data.k = Integer.parseInt(args[++k]);
			else if ( args[k].equals("-insertions")) 
				Data.insertions = Integer.parseInt(args[++k]);
			else if( args[k].equals("-fpp")) 
				Data.fpp = Float.parseFloat(args[++k]);	
			// 
			else if( args[k].equals("-p_1")) 
				Data.p_1 = Float.parseFloat(args[++k]);	
			else if( args[k].equals("-p_2")) 
				Data.p_2 = Float.parseFloat(args[++k]);	
			else if( args[k].equals("-p_3")) 
				Data.p_3 = Float.parseFloat(args[++k]);	
			else if( args[k].equals("-p_4")) 
				Data.p_4 = Float.parseFloat(args[++k]);
		}
		
		// === Print the configurations
		try {
 			FileWriter fw = new FileWriter(new File(Data.fnOutputData));
 			BufferedWriter bw = new BufferedWriter(fw);
 			bw.write( "d: " + Integer.toString(Data.d) + "\t\n" );
 			bw.close();
 			fw.close();
 			fw = new FileWriter(new File(Data.fnOutputData),true);
 			bw = new BufferedWriter(fw);
 			bw.write( "alpha: " + Float.toString(Data.alpha) + "\t\n" );
 			bw.write( "alpha_u: " + Float.toString(Data.alpha_u) + "\t\n" );
 			bw.write( "alpha_v: " + Float.toString(Data.alpha_v) + "\t\n" );
 			
 			bw.write( "gamma: " + Float.toString(Data.gamma) + "\t\n" );
 			
 			bw.write( "fnTrainData: " + Data.fnTrainData + "\t\n" );
 			bw.write( "fnTestData: " + Data.fnTestData + "\t\n" );
 			bw.write( "fnOutputData: " + Data.fnOutputData + "\t\n" );
 			
 			bw.write( "n: " + Integer.toString(Data.n) + "\t\n" );
 			bw.write( "m: " + Integer.toString(Data.m) + "\t\n" );
 			bw.write( "num_iterations: " + Integer.toString(Data.num_iterations) + "\t\n" );
 			
 			bw.write( "MinRating: " + Float.toString(Data.MinRating) + "\t\n" );
 			bw.write( "MaxRating: " + Float.toString(Data.MaxRating) + "\t\n" );
 			
 			// add
 			bw.write( "k: " + Integer.toString(Data.k) + "\t\n" );
 			bw.write( "insertions: " + Integer.toString(Data.insertions) + "\t\n" );
 			bw.write( "fpp: " + Float.toString(Data.fpp) + "\t\n" );
 
 			
 			
 			bw.close();
 			fw.close();
 		} catch (IOException e1) {
 			// TODO Auto-generated catch block
 			e1.printStackTrace();
 		}
 		Test.test();
	}
}

