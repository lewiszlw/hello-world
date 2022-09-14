package lewiszlw.dubbo.demo.nacos.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.NetUtils;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Optional;
import java.util.regex.Pattern;

import static org.apache.dubbo.common.constants.CommonConstants.ANYHOST_VALUE;
import static org.apache.dubbo.common.constants.CommonConstants.LOCALHOST_VALUE;

@Configuration
@Slf4j
public class IpSetConfig implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        String registerIp = null;
        NetworkInterface networkInterface = NetUtils.findNetworkInterface();
        Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
        while (addresses.hasMoreElements()) {
            Optional<InetAddress> addressOp = toValidAddress(addresses.nextElement());
            if (addressOp.isPresent()) {
                try {
                    if (addressOp.get().isReachable(100)) {
                        registerIp = addressOp.get().getHostAddress();
                    }
                } catch (IOException e) {
                    // ignore
                }
            }
        }
        if (registerIp != null) {
            log.info("LWZTEST ======= registerIp: {}", registerIp);
            System.setProperty("DUBBO_IP_TO_REGISTRY", registerIp);
        }
    }

    private Optional<InetAddress> toValidAddress(InetAddress address) {
        log.info("LWZTEST ======= toValidAddress: {}", address);
        if (address instanceof Inet6Address) {
            return Optional.empty();
        }
        if (isValidV4Address(address) && address.getHostAddress().startsWith("10")) {
            return Optional.of(address);
        }
        return Optional.empty();
    }

    private boolean isValidV4Address(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }

        String name = address.getHostAddress();
        return (name != null && IP_PATTERN.matcher(name).matches() && !ANYHOST_VALUE.equals(name)
                && !LOCALHOST_VALUE.equals(name));
    }

}
