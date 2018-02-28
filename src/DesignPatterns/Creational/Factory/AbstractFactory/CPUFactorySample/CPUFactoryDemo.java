package DesignPatterns.Creational.Factory.AbstractFactory.CPUFactorySample;

public class CPUFactoryDemo {

	// This is the Client
	public static void main(String[] args) {
		// Instantiates an EMBER factory
		AbstractFactory factory = AbstractFactory.getFactory(Architecture.EMBER);
		// Instantiates CPU/MMU classes based on the respective factories
		CPU cpu = factory.createCPU();
		MMU cpu2 = factory.createMMU();

		// Instantiates an Enginola factory
		AbstractFactory factory2 = AbstractFactory.getFactory(Architecture.ENGINOLA);
		// Instantiates CPU/MMU classes based on the respective factories
		CPU cpu3 = factory2.createCPU();
		MMU cpu4 = factory2.createMMU();
	}
}

// The AbstractFactory class provides the factory producer method as well
// It creates static, final factories.
abstract class AbstractFactory {
	private static final EmberToolkit EMBER_TOOLKIT = new EmberToolkit();
	private static final EnginolaToolkit ENGINOLA_TOOLKIT = new EnginolaToolkit();

	// Returns a concrete factory object that is an instance of the
	// concrete factory class appropriate for the given architecture.
	static AbstractFactory getFactory(Architecture architecture) {
		AbstractFactory factory = null;
		switch (architecture) {
		case ENGINOLA:
			factory = ENGINOLA_TOOLKIT;
			break;
		case EMBER:
			factory = EMBER_TOOLKIT;
			break;
		}
		return factory;
	}

	// The abstract factory have the methods that are implemented by the factories
	public abstract CPU createCPU();

	public abstract MMU createMMU();
}

// class EmberFactory
class EmberToolkit extends AbstractFactory {
	@Override
	public CPU createCPU() {
		return new EmberCPU();
	}

	@Override
	public MMU createMMU() {
		return new EmberMMU();
	}
}

// class EnginolaFactory
class EnginolaToolkit extends AbstractFactory {
	@Override
	public CPU createCPU() {
		return new EnginolaCPU();
	}

	@Override
	public MMU createMMU() {
		return new EnginolaMMU();
	}
}

// class CPU
abstract class CPU {
}

// class EmberCPU
class EmberCPU extends CPU {
}

// class EnginolaCPU
class EnginolaCPU extends CPU {
}

// class MMU
abstract class MMU {
}

// class EmberMMU
class EmberMMU extends MMU {
}

// class EnginolaMMU
class EnginolaMMU extends MMU {
}

enum Architecture {
	ENGINOLA, EMBER
}
