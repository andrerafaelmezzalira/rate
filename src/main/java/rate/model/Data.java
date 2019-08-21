package rate.model;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;

	private SortedSet<Rate> rates = new TreeSet<>();
	private SortedSet<Plan> plans = new TreeSet<>();

	public SortedSet<Plan> getPlans() {
		return plans;
	}

	public SortedSet<Rate> getRates() {
		return rates;
	}

}
