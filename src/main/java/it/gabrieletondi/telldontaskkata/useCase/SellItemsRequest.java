package it.gabrieletondi.telldontaskkata.useCase;

import java.util.ArrayList;
import java.util.List;

public class SellItemsRequest {
    private List<SellItemRequest> requests;

    public SellItemsRequest() {
        this.requests = new ArrayList<>();
    }

    public List<SellItemRequest> getRequests() {
        return requests;
    }
}
