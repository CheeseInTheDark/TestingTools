package net.jmlproductions.testing.answers;
import static net.jmlproductions.testing.answers.EndOfThreadTestAnswer.stopThread;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class EndOfThreadTestAnswerTest
{
	@Mock
	private Thread thread;
	
	@Mock
	private Object thing;
	
	@Before
	public void setup()
	{
		initMocks(this);
	}
	
	@Test
	public void shouldStopThread()
	{
		when(thing.toString()).thenAnswer(stopThread(thread));
		
		thing.toString();
		
		verify(thread).interrupt();
	}
}
