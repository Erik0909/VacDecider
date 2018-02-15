package erik.vacationdecider;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Erik on 31.01.2018.
 */

public class Cities implements Serializable {
    private String city;
    private String hotel;
    private Double currencyValue;
    private String Currency;
    private transient Drawable flag;
    private int avarageExpenditure;
    private int airplaneCost;

    public Cities(String city, String hotel, Drawable flag, String Currency, Double currencyValue, int avarageExpenditure, int airplaneCost) {
        this.city = city;
        this.hotel = hotel;
        this.flag = flag;
        this.Currency = Currency;
        this.currencyValue = currencyValue;
        this.airplaneCost = airplaneCost;
        this.avarageExpenditure = avarageExpenditure;
    }

    public String getCity() {
        return city;
    }

    public String getHotel() {
        return hotel;
    }

    public Drawable getFlag() {
        return flag;
    }
    public String getCurrency() {
        return Currency;
    }

    public Double getCurrencyValue() {
        return currencyValue;
    }

    public int getAvarageExpenditure() {
        return avarageExpenditure;
    }


    public int getAirplaneCost() {
        return airplaneCost;
    }

    public void setCurrencyValue(Double currencyValue) {
        this.currencyValue = currencyValue;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "city='" + city + '\'' +
                ", hotel='" + hotel + '\'' +
                ", currencyValue=" + currencyValue +
                ", Currency='" + Currency + '\'' +
                ", flag=" + flag +
                ", avarageExpenditure=" + avarageExpenditure +
                ", airplaneCost=" + airplaneCost +
                '}';
    }
}
