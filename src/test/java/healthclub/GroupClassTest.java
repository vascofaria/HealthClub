package healthclub;

import healthclub.exceptions.InvalidInvocationException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.*;

@Test
public class GroupClassTest {

	private GroupClass groupClass;

	@AfterMethod
	public void cleanup() {
		this.groupClass = null;
	}

	@Test
	public void testValidMinBeginHour() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(8, 2, 7, healthClub, 1, true);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 8);
		assertEquals(groupClass.getDuration(), 2);
		assertEquals(groupClass.getCapacity(), 7);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 1);
		assertTrue(groupClass.getGolden());

	}

	@Test
	public void testInvalidMinBeginHour() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		try {
			this.groupClass = new GroupClass(7, 3, 8, healthClub, 2, true);
			fail();
		} catch (InvalidInvocationException e) {
			//Assert
			assertNull(this.groupClass);
		}
	}

	@Test
	public void testValidMaxBeginHour() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(10, 4, 8, healthClub, 3, true);
		this.groupClass.setBeginHour(22);
		this.groupClass.setCapacity(9);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 22);
		assertEquals(groupClass.getDuration(), 4);
		assertEquals(groupClass.getCapacity(), 9);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 3);
		assertTrue(groupClass.getGolden());
	}

	@Test
	public void testInValidMaxBeginHour() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		try {
			this.groupClass = new GroupClass(10, 4, 8, healthClub, 3, true);
			this.groupClass.setBeginHour(23);
			this.groupClass.setMinAge(4);

			fail();
		} catch (InvalidInvocationException e) {
			assertNotNull(this.groupClass);
			assertNotNull(this.groupClass.getClub());
			assertEquals(groupClass.getBeginHour(), 10);
			assertEquals(groupClass.getDuration(), 4);
			assertEquals(groupClass.getCapacity(), 8);
			assertSame(groupClass.getClub(), healthClub);
			assertEquals(groupClass.getMinAge(), 3);
			assertTrue(groupClass.getGolden());
		}
	}

	@Test
	public void testInvalidDuration() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		try {
			this.groupClass = new GroupClass(10, 0, 8, healthClub, 3, true);
			fail();
		} catch (InvalidInvocationException e) {
			assertNull(this.groupClass);
		}
	}

	@Test
	public void testValidDuration() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(10, 1, 8, healthClub, 3, true);
		this.groupClass.setCapacity(12);
		this.groupClass.setMinAge(9);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 10);
		assertEquals(groupClass.getDuration(), 1);
		assertEquals(groupClass.getCapacity(), 12);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 9);
		assertTrue(groupClass.getGolden());
	}

	@Test
	public void testValidMinCapacity() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(12, 7, 10, healthClub, 11, true);
		this.groupClass.setCapacity(5);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 12);
		assertEquals(groupClass.getDuration(), 7);
		assertEquals(groupClass.getCapacity(), 5);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 11);
		assertTrue(groupClass.getGolden());
	}


	@Test
	public void testInvalidMinCapacity() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		try {
			this.groupClass = new GroupClass(16, 6, 20, healthClub, 10, true);
			this.groupClass.setBeginHour(11);
			this.groupClass.setCapacity(4);
			fail();
		} catch (InvalidInvocationException e) {
			// Assert
			assertNotNull(this.groupClass);
			assertNotNull(this.groupClass.getClub());
			assertEquals(groupClass.getBeginHour(), 11);
			assertEquals(groupClass.getDuration(), 6);
			assertEquals(groupClass.getCapacity(), 20);
			assertSame(groupClass.getClub(), healthClub);
			assertEquals(groupClass.getMinAge(), 10);
			assertTrue(groupClass.getGolden());
		}
	}

	@Test
	public void testValidMaxCapacity() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(13, 8, 25, healthClub, 12, true);
		this.groupClass.setBeginHour(13);
		this.groupClass.setCapacity(25);
		this.groupClass.setMinAge(12);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 13);
		assertEquals(groupClass.getDuration(), 8);
		assertEquals(groupClass.getCapacity(), 25);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 12);
		assertTrue(groupClass.getGolden());
	}

	@Test
	public void testInvalidMaxCapacity() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		try {
			this.groupClass = new GroupClass(14, 9, 15, healthClub, 15, true);
			this.groupClass.setCapacity(26);
			fail();
		} catch (InvalidInvocationException e) {
			// Assert
			assertNotNull(this.groupClass);
			assertNotNull(this.groupClass.getClub());
			assertEquals(groupClass.getBeginHour(), 14);
			assertEquals(groupClass.getDuration(), 9);
			assertEquals(groupClass.getCapacity(), 15);
			assertSame(groupClass.getClub(), healthClub);
			assertEquals(groupClass.getMinAge(), 15);
			assertTrue(groupClass.getGolden());
		}
	}

	@Test
	public void testInvalidHealthClub() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = null;
		//Act
		try {
			this.groupClass = new GroupClass(15, 10, 13, healthClub, 17, true);
		} catch(InvalidInvocationException e) {
			// Assert
			assertNull(this.groupClass);
		}
	}

	@Test
	public void testValidHealthClub() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(16, 11, 14, healthClub, 10, true);
		this.groupClass.setCapacity(14);
		this.groupClass.setMinAge(18);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 16);
		assertEquals(groupClass.getDuration(), 11);
		assertEquals(groupClass.getCapacity(), 14);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 18);
		assertTrue(groupClass.getGolden());
	}

	@Test
	public void testValidMinMinAge() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(17, 12, 10, healthClub, 10, true);
		this.groupClass.setCapacity(15);
		this.groupClass.setMinAge(0);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 17);
		assertEquals(groupClass.getDuration(), 12);
		assertEquals(groupClass.getCapacity(), 15);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 0);
		assertTrue(groupClass.getGolden());
	}

	@Test
	public void testInvalidMinMinAge() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		try {
			this.groupClass = new GroupClass(13, 12, 10, healthClub, -1, true);
			fail();
		} catch(InvalidInvocationException e) {
			// Assert
			assertNull(this.groupClass);
		}
	}

	@Test
	public void testInvalidMaxMinAge() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		try {
			this.groupClass = new GroupClass(18, 77, 21, healthClub, 19, true);
			this.groupClass.setBeginHour(19);
			this.groupClass.setMinAge(20);
			fail();
		} catch (InvalidInvocationException e) {
			// Assert
			assertNotNull(this.groupClass);
			assertNotNull(this.groupClass.getClub());
			assertEquals(groupClass.getBeginHour(), 19);
			assertEquals(groupClass.getDuration(), 77);
			assertEquals(groupClass.getCapacity(), 21);
			assertSame(groupClass.getClub(), healthClub);
			assertEquals(groupClass.getMinAge(), 19);
			assertTrue(groupClass.getGolden());
		}
	}

	@Test
	public void testValidMaxMinAge() throws InvalidInvocationException {
		// Arrange
		HealthClub healthClub = new HealthClub("club");
		// Act
		this.groupClass = new GroupClass(20, 88, 10, healthClub, 19, true);
		this.groupClass.setCapacity(22);
		this.groupClass.setMinAge(19);
		// Assert
		assertNotNull(this.groupClass);
		assertNotNull(this.groupClass.getClub());
		assertEquals(groupClass.getBeginHour(), 20);
		assertEquals(groupClass.getDuration(), 88);
		assertEquals(groupClass.getCapacity(), 22);
		assertSame(groupClass.getClub(), healthClub);
		assertEquals(groupClass.getMinAge(), 19);
		assertTrue(groupClass.getGolden());
	}
}
