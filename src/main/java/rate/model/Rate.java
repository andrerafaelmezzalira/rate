package rate.model;

import java.io.Serializable;

public class Rate implements Serializable, Comparable<Rate> {

	private static final long serialVersionUID = 1L;

	private Integer origin;

	private Integer target;

	private Double minute;

	public Rate(Integer origin, Integer target, Double minute) {
		this.origin = origin;
		this.target = target;
		this.minute = minute;
	}

	public Double getMinute() {
		return minute;
	}

	public Integer getOrigin() {
		return origin;
	}

	public Integer getTarget() {
		return target;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Rate && obj != null) {
			Rate rate = (Rate) obj;
			return rate.hashCode() == hashCode();
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return Integer.parseInt(String.valueOf(getOrigin() != null ? getOrigin() : 0)
				.concat(String.valueOf(getTarget() != null ? getTarget() : 0)));
	}

	@Override
	public int compareTo(Rate rate) {
		return Integer.valueOf(hashCode()).compareTo(rate.hashCode());
	}
}
