package ir.moke.javaee.api;

import jakarta.ws.rs.ext.ParamConverter;

public class OrderTypeParameterConverter implements ParamConverter<OrderType> {
    @Override
    public OrderType fromString(String value) {
        System.out.println("Parameter Converter: " + value);
        return OrderType.isExists(value) ? OrderType.valueOf(value) : OrderType.ASCEND;
    }

    @Override
    public String toString(OrderType orderType) {
        return orderType.getValue();
    }
}
