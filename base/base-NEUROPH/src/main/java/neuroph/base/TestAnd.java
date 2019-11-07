package neuroph.base;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class TestAnd {
	public static void main(String...strings) {
		DataSet trainingDataSet = new DataSet(2, 1);
		trainingDataSet.add(new DataSetRow("", ""));
		
		NeuralNetwork myPerceptron = new MultiLayerPerceptron(TransferFunctionType.SIGMOID);
	}
}
