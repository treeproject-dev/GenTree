package gentrees;

import java.util.Date;

public class Wedding {
	
	public int mid,pidH,pidW;

	private Date weddingDate;

	private Couple couple;
	
	public Wedding() {}

	@Override
	public String toString() {
		return "Wedding [mid=" + mid + ", pidH=" + pidH + ", pidW=" + pidW + "]";
	}

	public Wedding(Date date, Couple c) {

		weddingDate = date;

		couple = c;
	}

	public Date getWeddingDate() {

		return weddingDate;

	}

	public Couple getCouple() {

		return couple;

	}

}