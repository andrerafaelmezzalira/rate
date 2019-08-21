package rate.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rate.service.CalculateService;

@RestController
public class CalculateApi {

	@Autowired
	private CalculateService service;

	@RequestMapping(value = { "/calculate/{dddOrigin}/{dddTarget}/{minutes}/{minutesPlan}",
			"/calculate/{dddOrigin}/{dddTarget}/{minutes}" }, method = RequestMethod.GET)
	public @ResponseBody Double calculate(@PathVariable Integer dddOrigin, @PathVariable Integer dddTarget,
			@PathVariable Integer minutes, @PathVariable Optional<Integer> minutesPlan) throws Exception {
		return service.calculate(dddOrigin, dddTarget, minutes, minutesPlan.isPresent() ? minutesPlan.get() : null);
	}
}