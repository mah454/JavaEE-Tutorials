package ir.moke.javaee.api;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

public class Pagination {

    @QueryParam("offset")
    @DefaultValue("0")
    private Integer offset;
    @DefaultValue("2")
    @QueryParam("size")
    private Integer size;
    @QueryParam("order")
    @DefaultValue("id")
    private String order;
    @QueryParam("type")
    @DefaultValue("ASCEND")
    private OrderType orderType;

    public Integer getOffset() {
        return offset;
    }

    public Integer getSize() {
        return size;
    }

    public String getOrder() {
        return order;
    }

    public OrderType getOrderType() {
        return orderType;
    }
}
