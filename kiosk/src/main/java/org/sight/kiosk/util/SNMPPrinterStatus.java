package org.sight.kiosk.util;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public final class SNMPPrinterStatus {

    public static String f(){
        String printerIP = "192.168.0.211"; // 프린터 IP 주소
        String community = "public"; // SNMP 커뮤니티 문자열
        String oid = "1.3.6.1.2.1.43.16.5.1.2.1.1"; // 프린터 상태 관련 OID
        try {
            Address targetAddress = GenericAddress.parse("udp:" + printerIP + "/161");
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new org.snmp4j.smi.OctetString(community));
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(org.snmp4j.mp.SnmpConstants.version2c);

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid)));
            pdu.setType(PDU.GET);

            Snmp snmp = new Snmp(new DefaultUdpTransportMapping());
            snmp.listen();

            // SNMP 요청 보내기
            org.snmp4j.event.ResponseEvent response = snmp.send(pdu, target);
            snmp.close();

            if (response != null && response.getResponse() != null) {
                String hexString = response.getResponse().get(0).getVariable().toString();
                return StringUtils.decodeHexToUtf8(hexString);

            } else {
                throw new RuntimeException("응답이 없습니다.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
