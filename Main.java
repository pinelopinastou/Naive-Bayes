import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args){
	
	
		//Reading training datasets
		ArrayList<Mushroom> TrainingMushrooms = new ArrayList<Mushroom>();
		
		try {
			
			File file = new File("Mushrooms_training.data");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\t");
				String[] tokens = line.split(",");
				Mushroom m1 = new Mushroom();
				m1.setCategory(tokens[0]);
				for (int i=1; i<tokens.length; i++){
					m1.addAttribute(tokens[i]);}
					TrainingMushrooms.add(m1);}
			}
			
		catch(Exception e){
		
        	e.printStackTrace();
			System.out.println("Mushroom training data loading unsuccessful.");
    	}
				
			//Dataset is ready
				
		
		NaiveBayes NB = new NaiveBayes();
		NB.train(TrainingMushrooms);
		//Testing new data
		
		ArrayList<Mushroom> TestingMushrooms = new ArrayList<Mushroom>();
		
		try {
			
			File file = new File("Mushrooms_testing.data");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\t");
				String[] tokens = line.split(",");
				Mushroom m1 = new Mushroom();
				m1.setCategory(tokens[0]);
				for (int i=1; i<tokens.length; i++){
					m1.addAttribute(tokens[i]);}
					TestingMushrooms.add(m1);}
			}
			
		catch(Exception e){
		
        	e.printStackTrace();
			System.out.println("Mushroom training data loading unsuccessful.");
    	}
		
		int total=0;
		int right=0;
		//testing data 
		for (int i=0; i<TestingMushrooms.size(); i++){
			Mushroom m = TestingMushrooms.get(i);
			String PredictedCategory= NB.predict(m);
			String ActualCategory=m.getCategory();
			if (PredictedCategory.equals(ActualCategory)){
				right++;
				}
			total++;}
			
		System.out.println("Accuracy: "+(double) right/total);
		
			
	}
}
