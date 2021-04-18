package healthclub;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;
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
		member = new Member(name, club, golden);
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
			() -> { new Member(name, club, golden); });
	}

	@BeforeSuite
	public void initMembers()
	  throws InvalidOperationException, InvalidInvocationException {
		this.silverMember = new Member("member1", new HealthClub("club1"), false);
		this.goldenMember = new Member("member2", new HealthClub("club1"), true);
	}

	@Test
	public void conformanceTest1()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
	}

	@Test
	public void conformanceTest2()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest3()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(false);
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest4()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		assertThrows(InvalidOperationException.class,
			() -> { member.suspend(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest5()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		assertThrows(InvalidOperationException.class,
			() -> { member.enter(); });
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest6()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		assertThrows(InvalidOperationException.class,
			() -> { member.exit(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest7()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		assertThrows(InvalidOperationException.class,
			() -> { member.participate(groupClass); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest8()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		assertThrows(InvalidOperationException.class,
			() -> { member.leave(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest9()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.setActive(true);
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest10()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.setActive(false);
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest11()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.suspend();
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.SUSPENDED);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest12()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		assertThrows(InvalidOperationException.class,
			() -> { member.leave(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest13()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		assertThrows(InvalidOperationException.class,
			() -> { member.exit(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest14()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		assertThrows(InvalidOperationException.class,
			() -> { member.participate(groupClass); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest15()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.enter();
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
	}

	@Test
	public void conformanceTest16()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);

		member.setActive(false);
		member.setActive(true);
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest17()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		/*
		member.setActive(false);
		assertThrows(InvalidOperationException.class,
			() -> { member.setActive(false); });
		*/

		member.setActive(false);
		member.setActive(false);
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest18()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);

		member.setActive(false);
		assertThrows(InvalidOperationException.class,
			() -> { member.suspend(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest19()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);

		member.setActive(false);
		assertThrows(InvalidOperationException.class,
			() -> { member.leave(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest20()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);

		member.setActive(false);
		assertThrows(InvalidOperationException.class,
			() -> { member.exit(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest21()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(false);
		assertThrows(InvalidOperationException.class,
			() -> { member.participate(groupClass); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest22()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(false);
		assertThrows(InvalidOperationException.class,
			() -> { member.enter(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.INACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest23()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.enter();
		assertThrows(InvalidOperationException.class,
			() -> { member.setActive(true); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
	}

	@Test
	public void conformanceTest24()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.enter();
		assertThrows(InvalidOperationException.class,
			() -> { member.setActive(false); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
	}

	@Test
	public void conformanceTest25()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.enter();
		assertThrows(InvalidOperationException.class,
			() -> { member.suspend(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
	}

	@Test
	public void conformanceTest26()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.enter();
		assertThrows(InvalidOperationException.class,
			() -> { member.leave(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
	}
	
	@Test
	public void conformanceTest27()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		member.setActive(true);
		member.enter();
		member.exit();
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), false);
	}

	@Test
	public void conformanceTest28()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), true);
	}

	@Test
	public void conformanceTest29()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertThrows(InvalidOperationException.class,
			() -> { member.participate(groupClass); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), false);
	}

	@Test
	public void conformanceTest30()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertThrows(InvalidOperationException.class,
			() -> { member.enter(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), false);
	}

	@Test
	public void conformanceTest31()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		assertThrows(InvalidOperationException.class,
			() -> { member.setActive(true); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), true);
	}
	
	@Test
	public void conformanceTest32()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		assertThrows(InvalidOperationException.class,
			() -> { member.setActive(false); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), true);
	}

	@Test
	public void conformanceTest33()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		assertThrows(InvalidOperationException.class,
			() -> { member.suspend(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), true);
	}

	@Test
	public void conformanceTest34()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		member.leave();
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), false);
	}

	@Test
	public void conformanceTest35()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		assertThrows(InvalidOperationException.class,
			() -> { member.exit(); });
		
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), true);
	}

	@Test
	public void conformanceTest36()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		assertThrows(InvalidOperationException.class,
			() -> { member.participate(groupClass); });
		
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), true);
	}

	@Test
	public void conformanceTest37()
	  throws InvalidOperationException, InvalidInvocationException {
		// Arrange
		// Act
		member = new Member("member1", new HealthClub("club1"), true);
		GroupClass groupClass = new GroupClass(8, 2, 7, new HealthClub("club1"), 1, true);
		member.setActive(true);
		member.enter();
		assertEquals(member.enroll(groupClass), true);
		member.participate(groupClass);
		assertThrows(InvalidOperationException.class,
			() -> { member.enter(); });
		
		// Assert
		assertEquals(member.getStatus(), Member.MemberStatus.ACTIVE);
		assertEquals(member.isAtTheClub(), true);
		assertEquals(member.isAtGroupClass(), true);
	}


}
