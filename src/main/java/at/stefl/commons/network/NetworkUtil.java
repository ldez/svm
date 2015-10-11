package at.stefl.commons.network;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class NetworkUtil {

    public static List<Inet4Address> getLocalAddresses() throws IOException {
        final List<Inet4Address> result = new ArrayList<Inet4Address>();

        final InetAddress localHost = InetAddress.getLocalHost();

        final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            final NetworkInterface interfaze = interfaces.nextElement();

            for (final InterfaceAddress interfaceAddress : interfaze.getInterfaceAddresses()) {
                final InetAddress address = interfaceAddress.getAddress();
                if ((address instanceof Inet4Address) && (!address.equals(localHost))) {
                    result.add((Inet4Address) address);
                }
            }
        }

        return result;
    }

    private NetworkUtil() {}

}