package com.kw.evcs.web.dto;

import lombok.Data;

import java.util.List;

/**
 * 공공데이터 API 응답을 파싱하기 위한 객체
 */
public class ChargerInfo {

    @Data
    public static class Response {
        private List<Header> header;
        private List<Items> items;

        public String getResultCode() {
            return header.get(0).getResultCode();
        }

        public List<Item> getItemList() {
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
        List<Item> item;
    }

    @Data
    public static class Item {
        private String statNm;
        private String statId;
        private String chgerId;
        private String chgerType;
        private String addr;
        private String lat;
        private String lng;
        private String useTime;
        private String busiId;
        private String busiNm;
        private String busiCall;
        private int stat;
        private String statUpdDt;
        private String powerType;
        private String zcode;
        private String parkingFree;
        private String note;
        private String limitYn;
        private String limitDetail;
        private String delYn;
        private String delDetail;
    }
}
