package edu.weber.group2.cms.blogPost.model;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagTests {


	@Test
	public void getName_setName_ReturnsCorrectValues() {
		
		Tag tag = new Tag();
		tag.setName("Tag Test");

		assertEquals("Tag Test", tag.getName());
		assertNotEquals("tag Test", tag.getName());
	}


	@Test
	public void getId_setId_ReturnsCorrectValues() {
		
		Tag tag = new Tag();
		tag.setId(710);

		assertEquals(710, tag.getId());
		assertNotEquals(711, tag.getId());
	}
}
