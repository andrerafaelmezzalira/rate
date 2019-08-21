package rate.api;

import java.util.SortedSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rate.service.RateService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class RateApi {

	@Autowired
	private RateService service;

	@RequestMapping(value = "/rates", method = RequestMethod.GET)
	public @ResponseBody SortedSet<?> get() {
		return service.get();
	}
}