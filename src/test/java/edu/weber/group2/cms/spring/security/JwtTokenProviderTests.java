package edu.weber.group2.cms.spring.security;

import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.UserService;
import edu.weber.group2.cms.user.repository.UserRepository;

import java.util.Date;
import java.util.HashMap;
import java.time.Duration;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import org.springframework.security.core.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.test.util.ReflectionTestUtils;
//import org.springframework.security.oauth2.jwt;

import io.jsonwebtoken.*;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class JwtTokenProviderTests {


	@Mock
	private User mockUser;

	@Mock
	private Date mockDate;

	@Mock
	private UserService mockUserService;

	@Mock
	private UserRepository mockUserRepository;

	@Mock
	private Authentication mockAuthentication;

	@Mock
	private HttpServletRequest mockRequest;

	@InjectMocks
	private JwtTokenProvider mockJwtTokenProvider;


	@Test
	public void validateToken_StringToken_ReturnsTrue() {
		
		ReflectionTestUtils.setField(mockJwtTokenProvider, "secretKey", "secret");

		Date now = new Date();
		Date expiration = new Date(now.getTime() + (long) 6e+8);

		Claims claims = Jwts.claims().setSubject("TestSubject");

		String token = 
             Jwts.builder()//
            .setClaims(claims)//
            .setIssuedAt(now)//
            .setExpiration(expiration)//
            .signWith(SignatureAlgorithm.HS256, "secret")//
            .compact();

		assertEquals(true, mockJwtTokenProvider.validateToken(token));
	}

	@Test
	public void getAuthentication_RequestParameter_NullRequestTokenReturnsNull() {
		
		assertNull(mockJwtTokenProvider.getAuthentication(mockRequest));
	}

	@Test
	public void getAuthentication_StringParameter_ReturnsNull() {
		
		ReflectionTestUtils.setField(mockJwtTokenProvider, "secretKey", "secret");

		Date now = new Date();
		Date expiration = new Date(now.getTime() + (long) 6e+8);

		Claims claims = Jwts.claims().setSubject("TestSubject");

		String token = 
             Jwts.builder()//
            .setClaims(claims)//
            .setIssuedAt(now)//
            .setExpiration(expiration)//
            .signWith(SignatureAlgorithm.HS256, "secret")//
            .compact();

		assertNull(mockJwtTokenProvider.getAuthentication(token));
	}

	@Test
	public void getUsername_StringToken_ReturnsNull() {
		
		ReflectionTestUtils.setField(mockJwtTokenProvider, "secretKey", "secret");

		Date now = new Date();
		Date expiration = new Date(now.getTime() + (long) 6e+8);

		Claims claims = Jwts.claims().setSubject("TestSubject");

		String token = 
             Jwts.builder()//
            .setClaims(claims)//
            .setIssuedAt(now)//
            .setExpiration(expiration)//
            .signWith(SignatureAlgorithm.HS256, "secret")//
            .compact();

		assertNull(mockJwtTokenProvider.getAuthentication(token));
	}

	@Test
	public void getJwtTokenFromRequest_ReturnsNull() {
		
		assertNull(mockJwtTokenProvider.getJwtTokenFromRequest(mockRequest));
	}

	@Test
	public void getJwtTokenFromAuthHeader_ReturnsCorretTokenString() {
		
		String bearerToken = "BearerTokenTest";

		when(mockRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(bearerToken);

		assertNotNull(mockJwtTokenProvider.getJwtTokenFromAuthHeader(mockRequest));
		assertEquals("TokenTest", mockJwtTokenProvider.getJwtTokenFromAuthHeader(mockRequest));
	}

	@Test
	public void getJwtTokenFromAuthHeader_NullTokenString_ReturnsNull() {
		
		String bearerToken = null;

		when(mockRequest.getHeader(HttpHeaders.AUTHORIZATION)).thenReturn(bearerToken);

		assertNull(mockJwtTokenProvider.getJwtTokenFromAuthHeader(mockRequest));
	}

	@Test
	public void getJwtTokenFromCookie_ReturnsCorrectCookieValue() {
	
		Cookie cookie1 = new Cookie("sessionId", "Cookie 1 Value");

		Cookie cookie2 = new Cookie("CookieName2", "Cookie 2 Value");

		Cookie[] cookies = {cookie1, cookie2};
		
		when(mockRequest.getCookies()).thenReturn(cookies);


		assertNotNull(mockJwtTokenProvider.getJwtTokenFromCookie(mockRequest));
		assertEquals("Cookie 1 Value", mockJwtTokenProvider.getJwtTokenFromCookie(mockRequest));
	}

	@Test
	public void getJwtTokenFromCookie_NullCookieArray_ReturnsNull() {
	
		Cookie[] cookies = {};
		
		when(mockRequest.getCookies()).thenReturn(cookies);

		assertNull(mockJwtTokenProvider.getJwtTokenFromCookie(mockRequest));
	}

	@Test
	public void createToken_UserDateParameters_SuccessfullyReturns() {

		Duration duration = Duration.ofHours(24);

		ReflectionTestUtils.setField(mockJwtTokenProvider, "secretKey", "secret");
		ReflectionTestUtils.setField(mockJwtTokenProvider, "ttl", duration);

		Role role = new Role();

		HashMap hashMap = new HashMap();
		hashMap.put(32, role);

		User user = new User();
		user.setFirstName("Bob");
		user.setLastName("Jenkins");
		user.setUserName("UserBob");
		user.setRoles(hashMap);

		Date date = new Date();

		assertNotNull(mockJwtTokenProvider.createToken(user, date));
	}

	@Test
	public void createToken_NullUser_ReturnsNull() {

		User user = null;

		Date date = new Date();

		assertNull(mockJwtTokenProvider.createToken(user, date));
	}

}
