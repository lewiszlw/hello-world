package lewiszlw.jsondiff;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Desc:
 * ------------------------------------
 * Author:zhanglinwei
 * Date:2018/11/1
 * Time:14:57
 */
public class JsonDiff {

    public static Map<String, NewOldValue> compareJson(Class cls, MtpChannelDO mtpChannelDO, MtpChannelDO oldMtpChannelDO)
            throws IllegalAccessException, JsonProcessingException {

        if (!cls.isInstance(mtpChannelDO) || !cls.isInstance(oldMtpChannelDO)) {
            return null;
        }

        Map<String, NewOldValue> result = new HashMap<>();

        Field[] declaredFields = cls.getDeclaredFields();

        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            field.setAccessible(true);

            System.out.println(field.getName());

            Object value = field.get(mtpChannelDO);
            Object oldValue = field.get(oldMtpChannelDO);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(value);
            String oldJson = mapper.writeValueAsString(oldValue);

            if (!Objects.equals(json, oldJson)) {
                result.put(field.getName(), new NewOldValue(json, oldJson));
            }
        }
        return result;
    }

    public static void main(String[] args) throws JsonProcessingException, IllegalAccessException,
            InterruptedException {
        MtpChannelDO mtpChannelDO = new MtpChannelDO();
        mtpChannelDO.setId(1);
        mtpChannelDO.setChannelName("同业分销");

        PriceStockDO priceStockDO = new PriceStockDO();
        priceStockDO.setDate(new Date().toString());
        priceStockDO.setMtPrice(110D);
        mtpChannelDO.setPriceStocks(Collections.singletonList(priceStockDO));

        MtpChannelDO oldMtpChannelDO = new MtpChannelDO();
        oldMtpChannelDO.setId(2);
        oldMtpChannelDO.setChannelName("分享达人");

        Thread.sleep(5000);

        PriceStockDO oldPriceStockDO = new PriceStockDO();
        priceStockDO.setDate(new Date().toString());
        priceStockDO.setMtPrice(111D);
        oldMtpChannelDO.setPriceStocks(Collections.singletonList(oldPriceStockDO));

        Map<String, NewOldValue> diffMap = compareJson(MtpChannelDO.class, mtpChannelDO, oldMtpChannelDO);
        diffMap.forEach((s, newOldValue) -> {
            System.out.println(s);
            System.out.println("new value: " + newOldValue.getNewVal() + " old value: " + newOldValue.getOldVal());
            System.out.println();
        });
    }

}
