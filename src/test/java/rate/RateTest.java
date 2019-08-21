package rate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.SortedSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import rate.model.Plan;
import rate.model.Rate;
import rate.service.CalculateService;
import rate.service.PlanService;
import rate.service.RateService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RateTest {

	@Autowired
	private PlanService planService;

	@Autowired
	private RateService rateService;

	@Autowired
	private CalculateService calculateService;

	@Test
	public void testRates() {

		SortedSet<Rate> rates = rateService.get();

		if (rates.isEmpty()) {

			rateService.add(new Rate(11, 16, 1.90));
			rateService.add(new Rate(16, 11, 2.90));
			rateService.add(new Rate(11, 17, 1.70));
			rateService.add(new Rate(17, 11, 2.70));
			rateService.add(new Rate(11, 18, 0.90));
			rateService.add(new Rate(18, 11, 1.90));

			rates = rateService.get();

		}

		assertThat(rates.size(), is(6));

	}

	@Test
	public void testPlans() {

		SortedSet<Plan> plans = planService.get();

		if (plans.isEmpty()) {

			planService.add(new Plan("Fala Mais 30", 30, 10.0));
			planService.add(new Plan("Fala Mais 60", 60, 10.0));
			planService.add(new Plan("Fala Mais 120", 120, 10.0));
			plans = planService.get();
		}

		assertThat(plans.size(), is(3));

	}

	@Test
	public void testCalculatePlan() throws Exception {
		assertEquals(calculateService.calculate(11, 16, 20, null), Double.valueOf("38.0"));
		assertEquals(calculateService.calculate(11, 16, 20, 30), Double.valueOf("0.0"));
		assertEquals(calculateService.calculate(11, 17, 80, null), Double.valueOf("136.0"));
		assertEquals(calculateService.calculate(11, 17, 80, 60), Double.valueOf("37.40"));
		assertEquals(calculateService.calculate(18, 11, 200, null), Double.valueOf("380.0"));
		assertEquals(calculateService.calculate(18, 11, 200, 120), Double.valueOf("167.20"));
		try {
			calculateService.calculate(18, 17, 100, null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "rate not found");
		}

	}

}
