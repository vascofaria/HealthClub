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
	private Boolean golden;
	private List<Member> members;

	public GroupClass(int startHour, int duration, int capacity, HealthClub club,
	  int minAge, boolean golden) throws InvalidInvocationException {

		// startHour [08-22]x2 + [0-59]x2
		if (!(startHour >= 800 && startHour <= 2200 && startHour % 100 < 60)) {
			throw new InvalidInvocationException();
		}
		else if (!(duration > 0)) {
			throw new InvalidInvocationException();
		}
		else if (!(capacity > 5 && capacity < 25)) {
			throw new InvalidInvocationException();
		}
		else if (club == null) {
			throw new InvalidInvocationException();
		}
		else if (!(minAge >= 0 && minAge < 20)) {
			throw new InvalidInvocationException();
		} else {
			this.beginHour = startHour;
			this.duration  = duration;
			this.capacity  = capacity;
			this.club      = club;
			this.minAge    = minAge;
			this.golden    = golden;
			this.members   = new LinkedList();
		}
	}

	// change begin hour of this class group
	public void setBeginHour(int beginHour) throws InvalidInvocationException {
		if (!(beginHour >= 800 && beginHour <= 2200 && beginHour % 100 < 60)) {
			throw new InvalidInvocationException();
		} else {
			this.beginHour = beginHour;
		}
	}

	// change minimal age of this group class
	public void setMinAge(int minAge) throws InvalidInvocationException {
		if (!(minAge >= 0 && minAge < 20)) {
			throw new InvalidInvocationException();
		} else {
			this.minAge = minAge;
		}
	}

	// change capacity of this class group
	public void setCapacity(int capacity) throws InvalidInvocationException {
		if (!(capacity > 5 && capacity < 25)) {
			throw new InvalidInvocationException();
		} else {
			this.capacity = capacity;
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

	// returns the list of members registered for this group class
	public List<Member> getMembers() { return this.members; }

}
