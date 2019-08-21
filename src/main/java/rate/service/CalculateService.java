package rate.service;

import java.util.Date;
import java.util.SortedSet;

import org.prevayler.Query;
import org.springframework.stereotype.Service;

import rate.model.Data;
import rate.model.Plan;
import rate.model.Rate;

@Service
public class CalculateService extends PrevaylerService {

	private static final long serialVersionUID = 1L;

	public Double calculate(Integer dddOrigin, Integer dddTarget, Integer minutes, Integer minutesPlan)
			throws Exception {
		Object object = getPrevayler().execute(new Query() {

			@Override
			public Object query(Object data, Date date) throws Exception {

				Rate rateReturn = getRate(data);

				// rate not found
				if (rateReturn == null) {
					return 0.0;
				}

				Plan planReturn = getPlan(data);

				if (planReturn == null) {
					// rate found, but without plan, so calculate
					// minutes * value minute of rate
					return minutes * rateReturn.getMinute();
				}

				int excedent = minutes - planReturn.getMinute();

				if (excedent < 1)
					return 0.0;

				// rate, plan and minutes excedent found , so calculate
				// minutes excedent * (value minute of rate + plan percentage)
				return excedent * (rateReturn.getMinute() + (rateReturn.getMinute() * planReturn.getPercent() / 100));
			}

			private Plan getPlan(Object data) {
				if (minutesPlan != null) {
					SortedSet<Plan> plans = ((Data) data).getPlans();
					Plan plan = new Plan(null, minutesPlan, 0.0);

					for (Plan p : plans) {
						if (p.equals(plan)) {
							return p;
						}
					}
				}
				return null;
			}

			private Rate getRate(Object data) {
				SortedSet<Rate> rates = ((Data) data).getRates();
				Rate rate = new Rate(dddOrigin, dddTarget, null);

				for (Rate r : rates) {
					if (r.equals(rate)) {
						return r;
					}
				}
				return null;
			}
		});
		return new Double(object.toString());
	}
}