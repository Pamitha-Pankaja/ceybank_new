package com.example.ceybank.responses;

import java.util.List;

public class ApproveGoodRequisitionItemListRequest {

    private List<ItemApproval> items;

    public List<ItemApproval> getItems() {
        return items;
    }

    public void setItems(List<ItemApproval> items) {
        this.items = items;
    }

    public static class ItemApproval {
        private Long itemId;
        private int approvedQuantity;

        public Long getItemId() {
            return itemId;
        }

        public void setItemId(Long itemId) {
            this.itemId = itemId;
        }

        public int getApprovedQuantity() {
            return approvedQuantity;
        }

        public void setApprovedQuantity(int approvedQuantity) {
            this.approvedQuantity = approvedQuantity;
        }
    }
}
