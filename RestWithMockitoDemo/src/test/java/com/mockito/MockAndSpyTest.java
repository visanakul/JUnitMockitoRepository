package com.mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockAndSpyTest {

	@Test
	public void testArrayListMock() {
		List<String> mockedList = mock(ArrayList.class);
		mockedList.add("one");
		verify(mockedList).add("one");
		// No change in actual object
		assertEquals(0, mockedList.size());

		// when and then is used with mocked object
		when(mockedList.get(0)).thenReturn("one");
		String data = mockedList.get(0);
		assertEquals("one", data);

		// Mock object return null for index 100
		data = mockedList.get(100);
		assertNull(data);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testArrayListSpy() {
		List<String> list = new ArrayList<>();
		List<String> spyList = spy(list);
		spyList.add("one");
		verify(spyList).add("one");
		// Actual object size changed
		assertEquals(1, spyList.size());

		doReturn("one").when(spyList).get(0);
		String data = spyList.get(0);
		assertEquals("one", data);

		// Spy object return IndexOutOfBoundsException as it is real
		data = spyList.get(100);
		assertNull(data);
	}
}
