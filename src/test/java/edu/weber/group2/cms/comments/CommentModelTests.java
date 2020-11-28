package edu.weber.group2.cms.comments;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CommentModelTests {


	@Test
	public void getCommentID_setCommentID_ReturnsCorrectValues() {
		
		CommentModel comment = new CommentModel();
		comment.setCommentID(913);

		assertEquals(913, comment.getCommentID());
		assertNotEquals(912, comment.getCommentID());
	}

	@Test
	public void getCommentorID_setCommentorID_ReturnsCorrectValues() {
		
		CommentModel comment = new CommentModel();
		comment.setCommentorID(571);

		assertEquals(571, comment.getCommentorID());
		assertNotEquals(570, comment.getCommentorID());
	}

	@Test
	public void getBlogID_setBlogID_ReturnsCorrectValues() {
		
		CommentModel comment = new CommentModel();
		comment.setBlogID(1122);

		assertEquals(1122, comment.getBlogID());
		assertNotEquals(1121, comment.getBlogID());
	}

	@Test
	public void getCommentBody_setCommentBody_ReturnsCorrectValues() {
		
		CommentModel comment = new CommentModel();
		comment.setCommentBody("Comment Body Test");

		assertEquals("Comment Body Test", comment.getCommentBody());
		assertNotEquals("comment Body Test", comment.getCommentBody());
	}

}
