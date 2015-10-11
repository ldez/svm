package at.stefl.commons.network;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import at.stefl.commons.network.mac.MACAddress;

// http://www.iana.org/assignments
public class Assignments {

    // http://www.iana.org/assignments/ethernet-numbers
    public static class Ethernet {

        public static final short TYPE_IPV4 = (short) 0x0800;
        public static final short TYPE_ARP = (short) 0x0806;
        public static final short TYPE_IPV6 = (short) 0x86DD;

        private Ethernet() {}
    }

    // http://www.iana.org/assignments/arp-parameters/arp-parameters.xml
    public static class ARP {

        public static final short HARDWARE_TYPE_ETHERNET = 1;

        public static final short OPERATION_REQUEST = 1;
        public static final short OPERATION_REPLY = 2;

        public static final byte ETHERNET_ADDRESS_LENGTH = MACAddress.SIZE;

        public static final byte IPV4_ADDRESS_LENGTH = 4;
        public static final byte IPV6_ADDRESS_LENGTH = 16;

        private static final Map<Short, Byte> hardwareLengthMap = new HashMap<Short, Byte>();
        private static final Map<Short, Byte> protocolLengthMap = new HashMap<Short, Byte>();

        static {
            hardwareLengthMap.put(HARDWARE_TYPE_ETHERNET, ETHERNET_ADDRESS_LENGTH);

            protocolLengthMap.put(Ethernet.TYPE_IPV4, IPV4_ADDRESS_LENGTH);
            protocolLengthMap.put(Ethernet.TYPE_IPV6, IPV6_ADDRESS_LENGTH);
        }

        public static final byte getHardwareLength(final short hardwareType) {
            return hardwareLengthMap.get(hardwareType);
        }

        public static final byte getProtocolLength(final short protocolType) {
            return protocolLengthMap.get(protocolType);
        }

        private ARP() {}
    }

    public static class IP {

        // http://www.iana.org/assignments/version-numbers/version-numbers.xml
        public static final byte VERSION = 4;

        // http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xml
        public static final byte PROTOCOL_ICMP = 1;
        public static final byte PROTOCOL_TCP = 6;
        public static final byte PROTOCOL_UDP = 17;

        private IP() {}
    }

    public static class IPv4 {

        public static final int FLAG_DONT_FRAGMENT = 2;
        public static final int FLAG_MORE_FRAGMENTS = 4;

        private IPv4() {}
    }

    public static class ICMP {

        public static final byte TYPE_ECHO_REPLY = 0;
        public static final byte TYPE_ECHO = 8;

        private ICMP() {}
    }

    public static class DHCP {

        public static final Charset CHARSET = Charset.forName("ascii");

        public static final int SIZE_CLIENT_HARDWARE_ADDRESS = 16;
        public static final int SIZE_SERVER_NAME = 64;
        public static final int SIZE_FILE = 128;

        public static final byte OPERATION_BOOT_REQUEST = 1;
        public static final byte OPERATION_BOOT_REPLY = 2;

        public static final byte OPTION_SUBNET_MASK = 1;
        public static final byte OPTION_ROUTER = 3;
        public static final byte OPTION_NAME_SERVER = 6;
        public static final byte OPTION_REQUESTED_IP_ADDRESS = 50;
        public static final byte OPTION_MESSAGE_TYPE = 53;
        public static final byte OPTION_SERVER_IDENTIFIER = 54;
        public static final byte OPTION_PAD = 0;
        public static final byte OPTION_END = (byte) 255;

        public static final byte MESSAGE_TYPE_DISCOVER = 1;
        public static final byte MESSAGE_TYPE_OFFER = 2;
        public static final byte MESSAGE_TYPE_REQUEST = 3;
        public static final byte MESSAGE_TYPE_DECLINE = 4;
        public static final byte MESSAGE_TYPE_ACK = 5;
        public static final byte MESSAGE_TYPE_NAK = 6;
        public static final byte MESSAGE_TYPE_RELEASE = 7;
        public static final byte MESSAGE_TYPE_INFORM = 8;

        public static final int MAGIC_COOKIE_DHCP = 0x63825363;

        private DHCP() {}
    }

    public static class UDP {

        public static final int PORT_DNS = 53;
        public static final int PORT_DHCP_SERVER = 67;
        public static final int PORT_DHCP_CLIENT = 68;

        private UDP() {}
    }

    public static class TCP {

        public static final int PORT_TELNET = 23;
        public static final int PORT_DNS = 53;

        public static final int FLAG_FIN = 0x01;
        public static final int FLAG_SYN = 0x02;
        public static final int FLAG_RST = 0x04;
        public static final int FLAG_PSH = 0x08;
        public static final int FLAG_ACK = 0x10;
        public static final int FLAG_URG = 0x20;

        // http://www.iana.org/assignments/tcp-parameters/tcp-parameters.xml
        public static final byte OPTION_END = 0;
        public static final byte OPTION_NO = 1;
        public static final byte OPTION_MAXIMUM_SEGMENT_SIZE = 2;
        public static final byte OPTION_WINDOW_SCALE = 3;
        public static final byte OPTION_SACK_PERMITTED = 4;
        public static final byte OPTION_TIMESTAMP = 8;

        private TCP() {}
    }

    public static class Telnet {

        private Telnet() {}
    }

    public static class DNS {

        private DNS() {}
    }

    private Assignments() {}

}