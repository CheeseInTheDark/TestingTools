package net.jmlproductions.testing.answers;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class EndOfThreadTestAnswer<T extends Thread> implements Answer<T>
{
	private T thread;

	public static <T extends Thread> EndOfThreadTestAnswer<T> stopThread(T thread)
	{
		return new EndOfThreadTestAnswer<T>(thread);
	}

	public EndOfThreadTestAnswer(T thread)
	{
		this.thread = thread;
	}

	@Override
	public T answer(InvocationOnMock invocation) throws Throwable
	{
		thread.interrupt();

		return null;
	}

}

