package net.jmlproductions.testing.answers;

import static net.jmlproductions.testing.answers.PacketAnswer.setReceivedPacketTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class PacketAnswerTest
{
	private DatagramPacket packet;
	
	private byte[] data = { 0x00 };
	
	@Mock
	private DatagramSocket socket;
	
	@Mock
	private InetAddress address;
	
	private int port = 1234;
	
	@Before
	public void setup()
	{
		initMocks(this);
		
		packet = new DatagramPacket(data, data.length, address, 1234);
	}
	
	@Test
	public void shouldSetPacketData() throws IOException
	{
		runAnswer();
		
		assertThat(packet.getData(), sameInstance(data));
	}
	
	@Test
	public void shouldSetPacketDataLength() throws IOException
	{
		runAnswer();
		
		assertThat(packet.getLength(), is(data.length));
	}
	
	@Test
	public void shouldSetPacketAddress() throws IOException
	{
		runAnswer();
		
		assertThat(packet.getAddress(), sameInstance(address));
	}
	
	@Test
	public void shouldSetPacketPort() throws IOException
	{
		runAnswer();
		
		assertThat(packet.getPort(), is(port));
	}
	
	private void runAnswer() throws IOException
	{
		doAnswer(setReceivedPacketTo(packet)).when(socket).receive(argThat(any(DatagramPacket.class)));
		
		socket.toString();
	}
}
