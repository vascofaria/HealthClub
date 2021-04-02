package healthclub;

import healthclub.exceptions.InvalidOperationException;

import java.util.ArrayList;
import java.util.List;

public class Member {

	private String name;

	private HealthClub club;

	private boolean goldenMember;

	private boolean atClub;

	private boolean atGroupClass;

	private List<GroupClass> enrolledGroupClasses;

	public enum MemberStatus {ACTIVE, INACTIVE, SUSPENDED}

	private MemberStatus status;

	public Member(String name, HealthClub club, boolean goldenMember) 
	  throws InvalidOperationException {
		if (name == null || name.equals("") || club == null) {
			throw new InvalidOperationException();
		}
		this.name = name;
		this.club = club;
		this.goldenMember = goldenMember;
		this.atClub = false;
		this.atGroupClass = false;
		this.enrolledGroupClasses = new ArrayList<>();
		this.status = MemberStatus.SUSPENDED;
	}

	// should be invoked when this member tries to enter the health club
	public void enter() throws InvalidOperationException {
		if (this.status == MemberStatus.ACTIVE)
			this.atClub = true;
		else
			throw new InvalidOperationException("Non active members can not enter the club!");
	}

	// should be invoked when this member leaves the health club
	public void exit() throws InvalidOperationException {
		if (this.atClub && !this.atGroupClass)
			this.atClub = false;
		else
			throw new InvalidOperationException("Members cannot exit the club if they are not at the club or attending a group class");
	}

	// returns the name of this member.
	public String getName() {
		return this.name;
	}

	// returns the club of this member.
	public HealthClub getClub() {
		return this.club;
	}

	public boolean isGoldenMember() {
		return this.goldenMember;
	}

	public boolean isAtTheClub() {
		return this.atClub;
	}

	public boolean isAtGroupClass() {
		return this.atGroupClass;
	}

	public MemberStatus getStatus() {
		return this.status;
	}

	// returns the list of group classes of this member
	public List<GroupClass> getGroupClasses() throws InvalidOperationException {
		if (this.status != MemberStatus.SUSPENDED)
			return this.enrolledGroupClasses;
		else
			throw new InvalidOperationException("Can not get enrolled group classes of suspended members!");
	}

	// participates in the specified group class if this member is enrolled in the concerned group class
	public void participate(GroupClass groupClass) throws InvalidOperationException {
		if (this.atClub && !this.atGroupClass && this.enrolledGroupClasses.contains(groupClass))
			this.atGroupClass = true;
		else
			throw new InvalidOperationException("Members can not participate in a class if they are not at the club or not enrolled on the group class or are already attending another class");
	}

	// leaves the current group class where the member is participant. If the member
	// is not participating in any group class, it does nothing.
	public void leave() throws InvalidOperationException {
		if (atGroupClass)
			this.atGroupClass = false;
		else
			throw new InvalidOperationException("Member can not leave the group class if they are not attending one");
	}

	// enrolls in the specified group class . If the member is already enrolled, then it does nothing.
	// Returns true if the member was is enrolled in the group class , false otherwise.
	public boolean enroll(GroupClass gc) {
		if (enrolledGroupClasses.contains(gc)) {
			return true;
		} else if (!atGroupClass && this.status == MemberStatus.ACTIVE) {
			return this.enrolledGroupClasses.add(gc);
		}
		return false;
		// Missing exception throw ?? Dont know if it necessary.
	}

	// activate/deactivate the membership of a inactive member
	public void setActive(boolean active) throws InvalidOperationException {
		if (!this.atClub)
			this.status = active ? MemberStatus.ACTIVE : MemberStatus.INACTIVE;
		else
			throw new InvalidOperationException("Members can not changed status while in the club!");
	}

	// suspend an active member that is late in the monthly fee
	public void suspend() throws InvalidOperationException {
		if (!this.atClub && this.status == MemberStatus.ACTIVE)
			this.status = MemberStatus.SUSPENDED;
		else
			throw new InvalidOperationException("Members can only be suspended if they are active and not in the club!");
	}

	// pays the monthly membership fee
	public void pay() throws InvalidOperationException {
		if (this.status == MemberStatus.ACTIVE || this.status == MemberStatus.SUSPENDED)
			this.status = MemberStatus.ACTIVE;
		else
			throw new InvalidOperationException("Only active or suspended members can pay the fee!");

	}

}
