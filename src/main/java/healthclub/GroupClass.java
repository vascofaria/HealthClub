package healthclub;

import java.util.List;

public class GroupClass {

	public GroupClass(int startHour, int duration, int capacity, 
		HealthClub club, int minAge, boolean golden) {}

	// change begin hour of this class group
	public void setBeginHour(int beginHour) {}

	// change minimal age of this group class
	public void setMinAge(int minAge) {}

	// change capacity of this class group
	public void setCapacity(int capacity) {}

	// enrolls a new member to this group class. Returns true if the member is enrolled
	// in this class , false otherwise.
	// public boolean enroll(Member member) {}

	// accessor methods
	// public int getBeginHour() {}
	// public int getDuration() {}
	// public int getCapacity() {}
	// public int getMinAge() {}

	// returns the list of members registered for this group class
	// public List<Member> getMembers() {}

}
