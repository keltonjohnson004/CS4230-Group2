package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.model.ReadBlog;

import java.util.List;
import java.util.ArrayList;

import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainPageRepositoryTests {




	@Mock
	MainPageRepository mockMainPageRepository;



	@Test
	public void GetMainPage_ReturnsCorrectModelViewAttributes() {

		String search = "Test Query";
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

		when(mockMainPageRepository.getAllBlogs(search, tag, pageNo, pageSize)).thenReturn(blogList);

		assertNotNull(mockMainPageRepository.getAllBlogs(search, tag, pageNo, pageSize));
		assertEquals(52, mockMainPageRepository.getAllBlogs(search, tag, pageNo, pageSize).get(0).getBlogID());
		assertEquals(45, mockMainPageRepository.getAllBlogs(search, tag, pageNo, pageSize).get(1).getBlogID());

	}
	

}
