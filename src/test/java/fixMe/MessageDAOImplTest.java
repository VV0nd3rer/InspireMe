package fixMe;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kverchi.dao.MessageDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/beans-web.xml","classpath:/beans-security.xml"})
@WebAppConfiguration
@Transactional
public class MessageDAOImplTest {
	
	@Autowired 
	private MessageDAO messageDAO;
	
	@Test
	public void getMessagesHeadersTest(){
		assertEquals("Vasya", messageDAO.getMessagesHeaders(6, "inbox").get(0).getFrom());
	}
	
	/*@Test
	public void delMessageByIdTest(){
		messageDAO.deleteMessageById(6, 3);
	}*/
	@Test
	public void getMessageTest(){
		assertEquals("qq", messageDAO.getMessage(6, 6).getText());
	}

}
