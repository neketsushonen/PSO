package sourceforge.jswarm_pso.mikeTest1;

import sourceforge.jswarm_pso.FitnessFunction;

public class ClassificationFitness extends FitnessFunction {

	public ClassificationFitness() {
		// TODO Auto-generated constructor stub
	}

	public ClassificationFitness(boolean maximize) {
		super(maximize);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluate(double[] position) {	
		//System.out.println("calculating fitness...");
			int dimensions = Dataset.data.numAttributes()-1;
			//int classesNo = Dataset.data.attribute(Dataset.data.classIndex()).numValues();
			double sum = 0.0;
			for(int i=0;i<Dataset.data.numInstances();i++){				
				double classOfInst = Dataset.data.instance(i).classValue();
				int startPos = (int)classOfInst*dimensions;
				int endPos = (int)startPos+dimensions-1;
				for(int j=startPos;j<=endPos;j++){
					if(Dataset.data.attribute(j%dimensions).isNominal()){
						sum = sum + Utils.nMap.get(Dataset.data.attribute(j%dimensions).name()).get(Dataset.data.instance(i).toString(j%dimensions)) - position[j];
					}else{
						sum = sum + Dataset.data.instance(i).value(j%dimensions) - position[j];
					}
				}
			}
			//System.out.println("fitness ended.");
			return sum/Dataset.data.numInstances();			
	}

}