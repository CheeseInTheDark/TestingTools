package net.jmlproductions.testing.matchers;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.net.DatagramPacket;

import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

public class PacketDataMatcherTest
{
	private byte[] data = new byte[0];
	
	private TypeSafeMatcher<DatagramPacket> underTest = PacketDataMatcher.packetWithData(data);
	
	@Test
	public void shouldMatchCorrectPacketData()
	{
		DatagramPacket packetWithCorrectData = new DatagramPacket(data, data.length);
		
		assertThat(underTest.matches(packetWithCorrectData), is(true));
	}
	
	@Test
	public void shouldNotMatchDifferentPacketData()
	{
		DatagramPacket packetWithCorrectData = new DatagramPacket(new byte[1], 1);
		
		assertThat(underTest.matches(packetWithCorrectData), is(false));
	}
	
}
