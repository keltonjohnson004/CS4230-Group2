package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.mainPage.MainPageService;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerTests {


	@Mock
	BlogService mockBlogService;

	@Mock
	MainPageService mockMainPageService;

	@InjectMocks
	ApiController mockApiController;

	

	@Test
	public void getBlog_CorrectlyReturnsBlog() {
		
		Blog blog = new Blog();
		blog.setId(7614);

		when(mockBlogService.getBlogByID(anyString())).thenReturn(blog);

		assertEquals(7614, mockApiController.getBlog("7614").getId());
		assertNotEquals(7615, mockApiController.getBlog("7614").getId());
	}

	@Test
	public void deleteBlog_CorrectlyReturnsDeletedBlogId() {
		
		Blog blog = new Blog();
		blog.setId(712);

		when(mockBlogService.deleteBlogById(anyString())).thenReturn(blog);

		assertEquals(712, mockApiController.deleteBlog("712").getId());
		assertNotEquals(713, mockApiController.deleteBlog("712").getId());
	}

	@Test
	public void createBlog_NoEmptyStrings_ReturnsBlogFieldsCorrectly() {

		String blogTitle = "Test Title";
		String blogBody = "This is a blog body for testing";
		int authorId = 45;
		String tag = "Test Tag";
		String permission = "Test Permission";
		
		assertNotNull(mockApiController.createBlog(blogTitle, blogBody, authorId, tag, permission));
		assertEquals(blogTitle, mockApiController.createBlog(blogTitle, blogBody, authorId, tag, permission).getBlogName());
		assertEquals(blogBody, mockApiController.createBlog(blogTitle, blogBody, authorId, tag, permission).getBlogBody());
		assertEquals(authorId, mockApiController.createBlog(blogTitle, blogBody, authorId, tag, permission).getAuthorID());
		assertEquals(tag, mockApiController.createBlog(blogTitle, blogBody, authorId, tag, permission).getTags().get(0));
		assertEquals(permission, mockApiController.createBlog(blogTitle, blogBody, authorId, tag, permission).getPermission());
	}

	@Test
	public void createBlog_EmptyPermissionString_ReturnsNullPermission() {

		String blogTitle = "Test Title";
		String blogBody = "This is a blog body for testing";
		int authorId = 45;
		String tag = "Test Tag";
		String permission = "Test Permission";
		
		assertNull(mockApiController.createBlog(blogTitle, blogBody, authorId, tag, "").getPermission());
	}

	@Test
	public void createBlog_EmptyTagString_ReturnsNullTagList() {

		String blogTitle = "Test Title";
		String blogBody = "This is a blog body for testing";
		int authorId = 45;
		String tag = "Test Tag";
		String permission = "Test Permission";
		
		assertNull(mockApiController.createBlog(blogTitle, blogBody, authorId, "", permission).getTags());
	}

	@Test
	public void updateBlog_ReturnsBlogFieldsCorrectly() {

		int blogId = 52;
		String blogTitle = "Test Title";
		String blogBody = "This is a blog body for testing";
		int authorId = 45;
		
		assertNotNull(mockApiController.updateBlog(blogId, blogTitle, blogBody, authorId));
		assertEquals(blogTitle, mockApiController.updateBlog(blogId, blogTitle, blogBody, authorId).getBlogName());
		assertEquals(blogBody, mockApiController.updateBlog(blogId, blogTitle, blogBody, authorId).getBlogBody());
		assertEquals(authorId, mockApiController.updateBlog(blogId, blogTitle, blogBody, authorId).getAuthorID());
	}

	@Test
	public void getBlogs_ReturnsBlogListSuccessfully() {
		
		ReadBlog readBlog1 = new ReadBlog();
		readBlog1.setBlogID(61);

		ReadBlog readBlog2 = new ReadBlog();
		readBlog2.setBlogID(89);

		List<ReadBlog> blogList = new ArrayList<>();
		blogList.add(readBlog1);
		blogList.add(readBlog2);

		int pageNo = 3;

		when(mockMainPageService.getAllBlogs(null, null, pageNo, 10)).thenReturn(blogList);

		assertNotNull(mockApiController.getBlogs(pageNo));
		assertEquals(61, mockApiController.getBlogs(pageNo).get(0).getBlogID());
		assertEquals(89, mockApiController.getBlogs(pageNo).get(1).getBlogID());
	}
}
