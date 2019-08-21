package rate.service;

import java.io.IOException;
import java.io.Serializable;

import org.prevayler.Prevayler;
import org.prevayler.PrevaylerFactory;

import rate.model.Data;

public abstract class PrevaylerService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Prevayler prevayler;

	public PrevaylerService() {
		if (prevayler == null) {
			PrevaylerFactory factory = new PrevaylerFactory();
			factory.configurePrevalenceBase("data");
			factory.configurePrevalentSystem(new Data());
			try {
				prevayler = factory.create();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Prevayler getPrevayler() {
		return prevayler;
	}
}