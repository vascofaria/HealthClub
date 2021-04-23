package healthclub;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeSuite;
import static org.testng.Assert.*;

import healthclub.exceptions.InvalidOperationException;
import healthclub.exceptions.InvalidInvocationException;

@Test
public class MemberTest {

	private Member member;
	private Member silverMember;
	private Member goldenMember;

	@DataProvider
	private Object[][] computeValidDataForConstructor()
	  throws InvalidOperationException, InvalidInvocationException {
		return new Object[][] {
				{"member1", new HealthClub("club1"), true},
				{"member2", new HealthClub("club2"), false}
		};
	}

	@Test(dataProvider = "computeValidDataForConstructor")
	public void testConstructorValid(String name, HealthClub club, boolean golden)
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member(name, club, golden, 20, 5);
		// Assert
		assertEquals(member.getName(), name);
		assertEquals(member.getClub(), club);
		assertEquals(member.isGoldenMember(), golden);
		assertEquals(member.isAtTheClub(), false);
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
	}

	@DataProvider
	private Object[][] computeInvalidDataForConstructor()
	  throws InvalidInvocationException {
		return new Object[][] {
				{"", new HealthClub("club1"), true},
				{null, new HealthClub("club2"), false},
				{"member1", null, true}
		};
	}

	@Test(dataProvider = "computeInvalidDataForConstructor")
	public void testConstructorInvalid(String name, HealthClub club, boolean golden)
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act && Assert
		assertThrows(InvalidOperationException.class,
			() -> { new Member(name, club, golden, 20, 5); });
	}

	@BeforeSuite
	public void initMembers()
	  throws InvalidOperationException, InvalidInvocationException {
		this.silverMember = new Member("member1", new HealthClub("club1"), false, 20, 5);
		this.goldenMember = new Member("member2", new HealthClub("club1"), true, 20, 5);
	}

	@Test
	public void conformanceTest1()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true, 20, 5);
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
	}
}
