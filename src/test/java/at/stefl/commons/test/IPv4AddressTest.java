package at.stefl.commons.test;

import org.junit.Test;

import at.stefl.commons.network.ip.IPv4Address;

public class IPv4AddressTest {

    @Test
    public void should_testName() throws Exception {
        final IPv4Address address = new IPv4Address("192.168.255.254");
        System.out.println(address);
        System.out.println(address.toInetAddress());
    }

}