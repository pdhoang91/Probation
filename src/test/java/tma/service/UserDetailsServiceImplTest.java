package tma.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import tma.configuration.ApplicationConfiguration;
import tma.configuration.SpringMvcInitializer;
import tma.model.UserModelDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcInitializer.class, ApplicationConfiguration.class})
@WebAppConfiguration
public class UserDetailsServiceImplTest {
	
	UserModelDetails userModelDetails;
	
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Mock
	UserRepository userRepository;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
		@Test
		public void testLoadUserByUserName(){
		    Set<String> roles = new HashSet<String>();
			 roles.add("ADMIN");
			 roles.add("USER");
			
			String username = "username";
			userModelDetails = new UserModelDetails(username, "123", roles);
			when(userRepository.getUserByUsername(username)).thenReturn(userModelDetails);
			
			UserDetails user = userDetailsServiceImpl.loadUserByUsername(username);
			assertEquals(username,user.getUsername());
			assertEquals("123",user.getPassword());
			assertEquals(roles.size(),2);
			
		}
}

