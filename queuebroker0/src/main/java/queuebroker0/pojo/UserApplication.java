package queuebroker0.pojo;

import java.math.BigDecimal;

public class UserApplication {
    private String nickname;
    private ApplicationType applicationType;
    private String stockName;
    private BigDecimal priceLimit;
    private int amount;

    public UserApplication() {
    }

    public UserApplication(String nickname, ApplicationType applicationType,
                           String stockName, BigDecimal priceLimit, int amount) {
        this.nickname = nickname;
        this.applicationType = applicationType;
        this.amount = amount;
        this.stockName = stockName;
        this.priceLimit = priceLimit;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public BigDecimal getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(BigDecimal priceLimit) {
        this.priceLimit = priceLimit;
    }
}

