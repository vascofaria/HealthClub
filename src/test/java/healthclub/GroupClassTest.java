package healthclub;

import healthclub.exceptions.InvalidInvocationException;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

@Test
public class GroupClassTest {

	private GroupClass groupClass;

	@DataProvider
	private Object[][] computeValidDataForConstructor() throws InvalidInvocationException {
		return new Object[][] {
				{8 * 60, 2, 7, new HealthClub("club1"), 1, true},
				{22 * 60, 4, 9, new HealthClub("club2"), 3, false},
				{10 * 60, 1, 12, new HealthClub("club3"), 9, true},
				{12 * 60, 7, 6, new HealthClub("club4"), 11, false},
				{14 * 60, 9, 24, new HealthClub("club5"), 15, true},
				{17 * 60, 12, 15, new HealthClub("club6"), 0, false},
				{20 * 60, 88, 22, new HealthClub("club7"), 19, false}
		};
	}
	
	@Test(dataProvider = "computeValidDataForConstructor")
	public void testConstructorValid(int beginHour, int duration, int capacity, HealthClub club, int minAge, boolean golden) throws InvalidInvocationException {
		// Arrange
		// Act
		groupClass = new GroupClass(beginHour, duration, capacity, club, minAge, golden);
		// Assert
		assertEquals(groupClass.getBeginHour(), beginHour);
		assertEquals(groupClass.getDuration(), duration);
		assertEquals(groupClass.getCapacity(), capacity);
		assertSame(groupClass.getClub(), club);
		assertEquals(groupClass.getMinAge(), minAge);
		assertEquals(groupClass.getGolden(), golden);

	}

	@DataProvider
	private Object[][] computeInvalidDataForConstructor() throws InvalidInvocationException {
		return new Object[][] {
				{8 * 60 - 1, 3, 8, new HealthClub("club1"), 2, true},
				{22 * 60 + 1, 5, 10, new HealthClub("club2"), 4, false},
				{9 * 60, 0, 11, new HealthClub("club3"), 7, true},
				{11 * 60, 6, 5, new HealthClub("club4"), 10, false},
				{13 * 60, 8, 25, new HealthClub("club5"), 12, true},
				{15 * 60, 10, 13, null, 17, false},
				{18 * 60, 50, 20, new HealthClub("club6"), -1, true},
				{19 * 60, 77, 21, new HealthClub("club7"), 20, true},

		};
	}

	@Test(dataProvider = "computeInvalidDataForConstructor")
	public void testConstructorInvalid(int beginHour, int duration, int capacity, HealthClub club, int minAge, boolean golden) throws InvalidInvocationException {
		// Arrange
		// Act
		assertThrows(InvalidInvocationException.class, () -> { new GroupClass(beginHour, duration, capacity, club, minAge, golden); });
		// Assert
	}

}
