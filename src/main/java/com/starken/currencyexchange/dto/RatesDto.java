package com.starken.currencyexchange.dto;

import java.util.Comparator;
import java.util.List;

public class RatesDto implements Comparable<RatesDto> {
        private String date;
        private List<RateDto> rateList;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<RateDto> getRateList() {
            return rateList;
        }

        public void setRateList(List<RateDto> rateList) {
            this.rateList = rateList;
        }

        @Override
        public int compareTo(RatesDto o) {
            return Comparator.comparing(RatesDto::getDate)
                    .compare(this,o);
        }
}
