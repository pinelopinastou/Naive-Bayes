import java.util.*;
import java.math.*;

public class NaiveBayes{
	
	//we have 22 attributes. For each attribute, we have a Map. It contains the possible values of the attribute linked to the possibility of the attribute.
	Map<String,Double> ProbabilityAttributesP[];
	Map<String,Double> ProbabilityAttributesE[];
	//this time the map contains the possible values of the attributes linked to the sum of the values
	Map<String,Integer> SumOfAttributesE[];
	Map<String,Integer> SumOfAttributesP[];
	double ProbOfPoisonous;
	double ProbOfEdible;
	
	public NaiveBayers(){
		
		//initializing Maps
		ProbabilityAttributesP=new HashMap[22];
		ProbabilityAttributesE=new HashMap[22];
		SumOfAttributesP = new HashMap[22];
		SumOfAttributesE = new HashMap[22];
		
		for (int i=0 ; i<22; i++){
			ProbabilityAttributesP[i]=new HashMap<String,Double>();
			ProbabilityAttributesE[i]=new HashMap<String,Double>();
			SumOfAttributesP[i] = new HashMap<String,Integer>();
			SumOfAttributesE[i] = new HashMap<String,Integer>();
		}
	}
	
	public void train(ArrayList<Mushroom> trainingset){

		
		int SumOfPoisonous=0;
		int SumOfEdible=0;
		int TotalSum=0;
		
		//Calculation of the sums of an attribute's possible values
		for (int i=0; i<trainingset.size(); i++){
		
			TotalSum++;
			Mushroom m = trainingset.get(i);
			String category = m.getCategory();
			ArrayList<String> attributes = m.getAttributes();
			
			if (category.equals("p")){
				SumOfPoisonous++;}
			else{
				SumOfEdible++;}
				
			for (int j=0; j<22; j++){
				String attr=attributes.get(j);
				if (category.equals("p")){
					if (SumOfAttributesP[j].containsKey(attr)){
						int currentSum = SumOfAttributesP[j].get(attr);
						currentSum++;
						SumOfAttributesP[j].put(attr,currentSum);}
					else{
						SumOfAttributesP[j].put(attr,1);}
					}
				else {
					if (SumOfAttributesE[j].containsKey(attr)){
						int currentSum = SumOfAttributesE[j].get(attr);
						currentSum++;
						SumOfAttributesE[j].put(attr,currentSum);}
					else{
						SumOfAttributesE[j].put(attr,1);}
					}
				}
			}
			
			//calculating probabilities	
			//probability of an item belonging to a category
			ProbOfPoisonous= (double) SumOfPoisonous/TotalSum;
			ProbOfEdible= (double) SumOfEdible/TotalSum;
			
			//probability of an item's attribute having a certain value, when we know the category of the item
			for (int i=0; i<22; i++){
				
				 Iterator it = SumOfAttributesP[i].entrySet().iterator();
				 
				 while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					int Sum = (int) pair.getValue();
					String Name = (String) pair.getKey();
					double Prob = (double) Sum/SumOfPoisonous;
					ProbabilityAttributesP[i].put(Name,Prob);}
				
				 it = SumOfAttributesE[i].entrySet().iterator();
					
				 while (it.hasNext())  {
					Map.Entry pair = (Map.Entry)it.next();
					int Sum = (int) pair.getValue();
					String Name = (String) pair.getKey();
					double Prob = (double) Sum/SumOfEdible;
					ProbabilityAttributesE[i].put(Name,Prob);}
				}
				
				//this.print();
				
			
			}
		
		//predicting category
		public String predict(Mushroom m){
			
			ArrayList<String> attributes = m.getAttributes();
			double valueP = Bayes(attributes,ProbabilityAttributesP,SumOfAttributesP,ProbOfPoisonous);
			double valueE = Bayes(attributes,ProbabilityAttributesE,SumOfAttributesE,ProbOfEdible);
			String category;
			if (valueP>valueE){
				category="p";}
			else{
				category="e";}
			return category;
			}
			
		public void print(){
		
			System.out.println("Poisonous probability = "+ProbOfPoisonous);
			System.out.println("Probability of attributes in category Poisonous:");
			for (int i=0; i<22; i++){
				
				Iterator it = ProbabilityAttributesP[i].entrySet().iterator();
				System.out.println("Attribute"+(i+1) );
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					System.out.println("Probability of "+pair.getKey()+" :"+pair.getValue());
				}
			}
			
			System.out.println("Edible probability = "+ProbOfPoisonous);
			System.out.println("Probability of attributes in category Edible:");
			for (int i=0; i<22; i++){
				
				Iterator it = ProbabilityAttributesE[i].entrySet().iterator();
				System.out.println("Attribute"+i );
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					System.out.println("Probability of "+pair.getKey()+" :"+pair.getValue());
				}
			}
		}
		
		//Multinomial Bayes
		private double Bayes(ArrayList<String> attr,Map<String,Double> probattr[],Map<String,Integer> sumattr[],double probcat){
			double sum=0;
			sum=sum+Math.log(probcat);
			for (int i=0; i<attr.size(); i++){
				String attrName=attr.get(i);
				//if attrName=null it means that we dont have any data for this attribute's value. The probability is therefore zero.
				if (probattr[i].get(attrName)!=null){
				double prob=(double) probattr[i].get(attrName);
				int noOfInstances = sumattr[i].get(attrName);
				sum=sum+ noOfInstances*Math.log(prob);}
			}
			
			return -sum;
			
			
			}
			
	}
