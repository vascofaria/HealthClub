package healthclub;

import java.util.List;
import java.util.LinkedList;

import healthclub.exceptions.InvalidInvocationException;

public class HealthClub {

	private String name;
	private List<Member> members;
	private List<GroupClass> classes;

	// creates a health club with a given name.
	public HealthClub(String name) throws InvalidInvocationException {
		if (name == null || name.equals("")) {
			throw new InvalidInvocationException();
		}
		this.name    = name;
		this.members = new LinkedList();
		this.classes = new LinkedList();
	}

	// registers a new member and assigns a unique id to the new member
	public void registerMember(Member member) {
		this.members.add(member);
	}

	// register a group class to the health club and assigns a unique id to the new class.
	public void registerGroupClass(GroupClass g) {
		this.classes.add(g);
	}

	// removes a member
	public void removeMember(Member member) {
		this.members.remove(member);
	}

	// removes a group class
	public void removeGroupClass(GroupClass g) {
		this.classes.remove(g);
	}

	// pays the membership cost for the specified member
	public void payMembershipCost(Member member) {}

	// computes the monthly membership cost for the specified member
	/*public float computeMembershipCost(Member m) {
		if (m.getStatus().equals(Member.MemberStatus.INACTIVE)) {
			return 0;
		} else {
			if (m.getAge() == 0) {

			}
		}
	}*/

	// returns the list of members
	public List<Member> getMembers() {
		return this.members;
	}

	// returns the list items of group classes
	public List<GroupClass> getGroupClasses() {
		return this.classes;
	}
	
}
