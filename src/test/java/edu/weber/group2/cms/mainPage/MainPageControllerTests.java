package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.blogPost.BlogService;
import edu.weber.group2.cms.mainPage.MainPageService;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPageControllerTests {



	@Mock
	Model mockModel;

	@Mock
	Principal mockPrincipal;

	@Mock
	BindingResult mockBindingResult;

	@Mock
	BlogService mockBlogService;

	@Mock
	MainPageService mockMainPageService;

	@InjectMocks
	MainPageController mockMainPageController;



	@Test
	public void GetMainPage_ReturnsCorrectModelViewAttributes() {

		String query = "Test Query";
		String tag = "Test Tag";
		Integer pageNo = 0;
		Integer pageSize = 10;

		
		ReadBlog blog1 = new ReadBlog();
		blog1.setBlogID(52);

		ReadBlog blog2 = new ReadBlog();
		blog2.setBlogID(45);

		List<ReadBlog> blogList = new ArrayList<>();
		blogList.add(blog1);
		blogList.add(blog2);

		List<Tag> tagList = new ArrayList<>();


		when(mockMainPageService.getAllBlogs(query, tag, pageNo, pageSize, mockPrincipal)).thenReturn(blogList);
		when(mockBlogService.getAllTags()).thenReturn(tagList);


		assertNotNull(mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, mockPrincipal));

		assertEquals(blogList, mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, mockPrincipal)
			.getModelMap().getAttribute("blogList"));

		assertEquals("home page", mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, mockPrincipal)
			.getModelMap().getAttribute("page_title"));

		assertEquals(tagList, mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, mockPrincipal)
			.getModelMap().getAttribute("tagList"));

		assertEquals(pageNo - 1, mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, mockPrincipal)
			.getModelMap().getAttribute("prevPage"));

		assertEquals(pageNo + 1, mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, mockPrincipal)
			.getModelMap().getAttribute("nextPage"));

	}
	
	@Test
	public void GetMainPage_EmptyBlogList_ReturnsCorrectPageNumbers() {

		String query = "Test Query";
		String tag = "Test Tag";
		Integer pageNo = 0;
		Integer pageSize = 10;

		
		List<ReadBlog> blogList = new ArrayList<>();

		List<Tag> tagList = new ArrayList<>();

		when(mockMainPageService.getAllBlogs(query, tag, pageNo, pageSize)).thenReturn(blogList);
		when(mockBlogService.getAllTags()).thenReturn(tagList);

		assertEquals(pageNo - 2, mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, null)
			.getModelMap().getAttribute("prevPage"));

		assertEquals(pageNo, mockMainPageController.GetMainPage(mockModel, query, tag, pageNo, pageSize, null)
			.getModelMap().getAttribute("nextPage"));


	}

}
