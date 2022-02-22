package lewiszlw.dubbo.demo.iface;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-25
 */
public interface HelloService {

    /**
     * 打招呼
     */
    HelloResponseDTO hello(HelloRequestDTO requestDTO);
}
