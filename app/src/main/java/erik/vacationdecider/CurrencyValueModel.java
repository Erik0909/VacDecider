package erik.vacationdecider;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Erik on 13.02.2018.
 */

public class CurrencyValueModel {
    double mUSD;
    double mTRY;
    double mSEK;
    double mEUR;

    public static CurrencyValueModel fromJson(JSONObject jsonObject) {
        try {
            CurrencyValueModel currValues = new CurrencyValueModel();
            JSONObject values = new JSONObject(jsonObject.getString("rates"));
            currValues.mUSD = values.getDouble("USD");
            currValues.mEUR = values.getDouble("EUR");
            currValues.mTRY = values.getDouble("TRY");
            currValues.mSEK = values.getDouble("SEK");


            return currValues;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getUSD() { return mUSD; }

    public double getTRY() {
        return mTRY;
    }


    public double getSEK() {
        return mSEK;
    }


    public double getEUR() {
        return mEUR;
    }


    @Override
    public String toString() {
        return "CurrencyValueModel{" +
                "mUSD=" + mUSD +
                ", mTRY=" + mTRY +
                ", mSEK=" + mSEK +
                ", mEUR=" + mEUR +
                '}';
    }
}
