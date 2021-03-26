package healthclub;

import java.util.List;

public class Member {

	public Member(String name, HealthClub club, boolean goldenMember) {}

	// should be invoked when this member tries to enter the health club
	public void enter() {}

	// should be invoked when this member leaves the health club
	public void exit () {}

	// returns the name of this member.
	public void getName() {}

	// returns the list of group classes of this member
	// public List<GroupClass> getGroupClasses() {}

	// participates in the specified group class if this member is enrolled in the concerned group class
	public void participate (GroupClass groupClass) {}

	// leaves the current group class where the member is participant. If the member
	// is not participating in any group class, it does nothing.
	public void leave () {}

	// enrolls in the specified group class . If the member is already enrolled, then it does nothing.
	// Returns true if the member was is enrolled in the group class , false otherwise.
	// public boolean enroll(GroupClass gc) {}

	// activate/deactivate the membership of a inactive member
	public void setActive(boolean active) {}

	// suspend an active member that is late in the monthly fee
	public void suspend() {}

	// pays the monthly membership fee
	public void pay() {}

}
