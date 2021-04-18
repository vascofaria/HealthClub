package healthclub;

import java.util.List;
import java.util.LinkedList;

import healthclub.exceptions.InvalidInvocationException;

public class GroupClass {

	private Integer beginHour;
	private Integer duration;
	private Integer capacity;
	private HealthClub club;
	private Integer minAge;
	private boolean golden;
	private List<Member> members;



	public GroupClass(int startHour, int duration, int capacity, HealthClub club,
	  int minAge, boolean golden) throws InvalidInvocationException {
		if (startHour >= 8 && startHour <= 22  &&
		  duration > 0 &&
		  capacity >= 5 && capacity <= 25 &&
		  club != null &&
		  minAge >= 0 && minAge < 20) {

			this.beginHour = startHour;
			this.duration  = duration;
			this.capacity  = capacity;
			this.club      = club;
			this.minAge    = minAge;
			this.golden    = golden;
			this.members   = new LinkedList<>();

		} else {
			throw new InvalidInvocationException();
		}
	}

	// change begin hour of this class group
	public void setBeginHour(int beginHour) throws InvalidInvocationException {
		if (beginHour >= 8 && beginHour <= 22) {
			this.beginHour = beginHour;
		} else {
			throw new InvalidInvocationException();

		}
	}

	// change minimal age of this group class
	public void setMinAge(int minAge) throws InvalidInvocationException {
		if (minAge >= 0 && minAge < 20) {
			this.minAge = minAge;
		} else {
			throw new InvalidInvocationException();

		}
	}

	// change capacity of this class group
	public void setCapacity(int capacity) throws InvalidInvocationException {
		if (capacity >= 5 && capacity <= 25) {
			this.capacity = capacity;
		} else {
			throw new InvalidInvocationException();
		}
	}

	// enrolls a new member to this group class. Returns true if the member is enrolled
	// in this class , false otherwise.
	public boolean enroll(Member member) throws InvalidInvocationException {
		if (member == null) {
			throw new InvalidInvocationException();
		} else if (this.members.contains(member)) {
			return true;
		} else if (this.members.size() == this.capacity) {
			return false;
		} else {
			return this.members.add(member);
		}
	}

	// accessor methods
	public int getBeginHour() { return this.beginHour; }
	public int getDuration()  { return this.duration;  }
	public int getCapacity()  { return this.capacity;  }
	public int getMinAge()    { return this.minAge;    }
	public HealthClub getClub() {
		return club;
	}
	public boolean getGolden() {
		return golden;
	}

	// returns the list of members registered for this group class
	public List<Member> getMembers() { return this.members; }

}
