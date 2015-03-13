package net.jmlproductions.testing.matchers;

import java.net.DatagramPacket;
import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class PacketDataMatcher extends TypeSafeMatcher<DatagramPacket>
{
    private byte[] data;
    
    public PacketDataMatcher(byte[] data)
    {
        this.data = data;
    }
    
    public static TypeSafeMatcher<DatagramPacket> packetWithData(byte[] data)
    {
        return new PacketDataMatcher(data);
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendText(" a datagram packet with the data ");
        description.appendValue(data);
    }

    @Override
    protected boolean matchesSafely(DatagramPacket packet)
    {
        return Arrays.equals(packet.getData(), data);
    }
}
