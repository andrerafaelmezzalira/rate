package rate.model;

import java.io.Serializable;

public class Plan implements Serializable, Comparable<Plan> {

	private static final long serialVersionUID = 1L;

	private String name;

	private Integer minute;

	private Double percent;

	public Plan(String name, Integer minute, Double percent) {
		this.name = name;
		this.minute = minute;
		this.percent = percent;
	}

	public Integer getMinute() {
		return minute;
	}

	public String getName() {
		return name;
	}

	public Double getPercent() {
		return percent;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Plan && obj != null) {
			Plan plan = (Plan) obj;
			return plan.hashCode() == hashCode();
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getMinute().hashCode();
	}

	@Override
	public int compareTo(Plan plan) {
		return Integer.valueOf(hashCode()).compareTo(plan.hashCode());
	}
}
