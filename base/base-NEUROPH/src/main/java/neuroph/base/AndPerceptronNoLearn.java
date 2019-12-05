package neuroph.base;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.util.NeuralNetworkType;

@SuppressWarnings("rawtypes")
public class AndPerceptronNoLearn extends NeuralNetwork{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void createNetwork(int inputNeuronsCount) {
		this.setNetworkType(NeuralNetworkType.PERCEPTRON);
	}

}
