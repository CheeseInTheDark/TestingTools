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
	private DatagramPacket packetForAnswer;
	
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
		
		packetForAnswer = new DatagramPacket(data, data.length, address, 1234);
	}
	
	@Test
	public void shouldSetPacketData() throws IOException
	{
		DatagramPacket resultingPacket = runAnswer();
		
		assertThat(resultingPacket.getData(), sameInstance(data));
	}
	
	@Test
	public void shouldSetPacketDataLength() throws IOException
	{
		DatagramPacket resultingPacket = runAnswer();
		
		assertThat(resultingPacket.getLength(), is(data.length));
	}
	
	@Test
	public void shouldSetPacketAddress() throws IOException
	{
		DatagramPacket resultingPacket = runAnswer();
		
		assertThat(resultingPacket.getAddress(), sameInstance(address));
	}
	
	@Test
	public void shouldSetPacketPort() throws IOException
	{
		DatagramPacket resultingPacket = runAnswer();
		
		assertThat(resultingPacket.getPort(), is(port));
	}
	
	private DatagramPacket runAnswer() throws IOException
	{
		DatagramPacket resultingPacket = new DatagramPacket(new byte[0], 0);
		
		doAnswer(setReceivedPacketTo(packetForAnswer)).when(socket).receive(argThat(any(DatagramPacket.class)));
		
		socket.receive(resultingPacket);
		
		return resultingPacket;
	}
}
