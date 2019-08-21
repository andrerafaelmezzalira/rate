package rate.service;

import java.util.Date;
import java.util.SortedSet;

import org.prevayler.Transaction;
import org.springframework.stereotype.Service;

import rate.model.Data;
import rate.model.Plan;

@Service
public class PlanService extends PrevaylerService {

	private static final long serialVersionUID = 1L;

	public void add(Plan plan) {
		getPrevayler().execute(new Transaction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void executeOn(Object data, Date date) {
				((Data) data).getPlans().add(plan);
			}
		});

	}

	public SortedSet<Plan> get() {
		return ((Data) getPrevayler().prevalentSystem()).getPlans();
	}

}