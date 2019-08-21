package rate.api;

import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rate.service.PlanService;

@RestController
public class PlanApi {

	@Autowired
	private PlanService service;

	@RequestMapping(value = "/plans", method = RequestMethod.GET)
	public @ResponseBody SortedSet<?> get() {
		return service.get();
	}
}