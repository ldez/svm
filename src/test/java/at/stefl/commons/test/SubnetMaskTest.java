package at.stefl.commons.test;

import at.stefl.commons.network.ip.SubnetMask;

public class SubnetMaskTest {
    
    public static void main(String[] args) {
        String string = "255.255.255.255";
        System.out.println(string);
        SubnetMask mask = new SubnetMask(string);
        System.out.println(mask);
    }
    
}