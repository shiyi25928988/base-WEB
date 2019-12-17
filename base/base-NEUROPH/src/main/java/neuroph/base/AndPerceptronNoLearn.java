package neuroph.base;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.comp.neuron.BiasNeuron;
import org.neuroph.util.LayerFactory;
import org.neuroph.util.NeuralNetworkType;
import org.neuroph.util.NeuronProperties;

/**
 * @author yshi
 *
 */
@SuppressWarnings("rawtypes")
public class AndPerceptronNoLearn extends NeuralNetwork {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void createNetwork(int inputNeuronsCount) {
		this.setNetworkType(NeuralNetworkType.PERCEPTRON);

		NeuronProperties inputNeuronProperties = new NeuronProperties();
		// inputNeuronProperties.setProperty(key, value);
		NeuronProperties outputNeuronProperties = new NeuronProperties();

		Layer inputLayer = LayerFactory.createLayer(inputNeuronsCount, inputNeuronProperties);

		this.addLayer(inputLayer);

		inputLayer.addNeuron(new BiasNeuron());
	}

}
