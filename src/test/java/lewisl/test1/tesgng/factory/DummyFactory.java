package lewisl.test1.tesgng.factory;

import org.testng.annotations.Factory;

public class DummyFactory {
	@Factory
	public Object[] getInstances() {
		return new DummyCase[] { new DummyCase(), new DummyCase(),
				new DummyCase() };
	}

}
