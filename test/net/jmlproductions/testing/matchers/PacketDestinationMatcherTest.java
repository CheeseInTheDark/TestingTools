package net.jmlproductions.testing.matchers;

import static net.jmlproductions.testing.matchers.PacketDestinationMatcher.packetWithDestination;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.net.DatagramPacket;
import java.net.InetAddress;

import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PacketDestinationMatcherTest
{
	@Mock
	private InetAddress addressToMatch;
	
	private int portToMatch = 1233;
	
	@Mock
	private InetAddress wrongAddress;
	
	private int wrongPort = 12323;
	
	private TypeSafeMatcher<DatagramPacket> underTest;
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
		underTest = packetWithDestination(addressToMatch, portToMatch);
	}
	
	@Test
	public void shouldNotMatchPacketWithDifferentAddress()
	{
		DatagramPacket packetWithDifferentAddress = new DatagramPacket(new byte[0], 0, wrongAddress, portToMatch);
		
		assertThat(underTest.matches(packetWithDifferentAddress), is(false));
	}
	
	@Test
	public void shouldNotMatchPacketWithDifferentPort()
	{
		DatagramPacket packetWithDifferentPort = new DatagramPacket(new byte[0], 0, addressToMatch, wrongPort);
		
		assertThat(underTest.matches(packetWithDifferentPort), is(false));
	}
	
	@Test
	public void shouldMatchPacketWithCorrectAddressAndPort()
	{
		DatagramPacket packetWithSameAddressAndPort = new DatagramPacket(new byte[0], 0, addressToMatch, portToMatch);
		
		assertThat(underTest.matches(packetWithSameAddressAndPort), is(true));
	}
}
