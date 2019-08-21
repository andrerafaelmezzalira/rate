package rate.service;

import java.util.Date;
import java.util.SortedSet;

import org.prevayler.Transaction;
import org.springframework.stereotype.Service;

import rate.model.Data;
import rate.model.Rate;

@Service
public class RateService extends PrevaylerService {

	private static final long serialVersionUID = 1L;

	public void add(Rate rate) {
		getPrevayler().execute(new Transaction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void executeOn(Object data, Date date) {
				((Data) data).getRates().add(rate);
			}
		});

	}

	public SortedSet<Rate> get() {
		return ((Data) getPrevayler().prevalentSystem()).getRates();
	}

}