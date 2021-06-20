package com.kw.evcs.web.dto;

import lombok.Data;

import java.util.List;

public class ChargerStatus {

    @Data
    public static class Response {
        private List<ChargerStatus.Header> header;
        private List<ChargerStatus.Items> items;

        public String getResultCode() {
            return header.get(0).getResultCode();
        }

        public List<ChargerStatus.Item> getItemList() {
            return items.get(0).getItem();
        }

        public int getNumOfRows() {
            return header.get(0).getNumOfRows();
        }
    }

    @Data
    public static class Header {
        private String resultCode;
        private String resultMsg;
        private int totalCount;
        private int pageNo;
        private int numOfRows;
    }

    @Data
    public static class Items {
        List<ChargerStatus.Item> item;
    }

    @Data
    public static class Item {
        private String busiId;
        private String statId;
        private String chgerId;
        private int stat;
        private String statUpdDt;
    }
}
