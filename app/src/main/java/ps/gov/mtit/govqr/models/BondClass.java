package ps.gov.mtit.govqr.models;

import android.os.Parcel;
import android.os.Parcelable;

public class BondClass implements Parcelable {

    String TAGNO, BOND_NO, WALLET_RSD, AMOUNT, AMOUNT_DESC, INSERTED_AT;
    String ACC_NO, EMP_SSN, EMP_NAME, MINS_NAME;

    public String getTAGNO() {
        return TAGNO;
    }

    public void setTAGNO(String TAGNO) {
        this.TAGNO = TAGNO;
    }

    public String getBOND_NO() {
        return BOND_NO;
    }

    public void setBOND_NO(String BOND_NO) {
        this.BOND_NO = BOND_NO;
    }

    public String getEMP_SSN() {
        return EMP_SSN;
    }

    public void setEMP_SSN(String EMP_SSN) {
        this.EMP_SSN = EMP_SSN;
    }

    public String getEMP_NAME() {
        return EMP_NAME;
    }

    public void setEMP_NAME(String EMP_NAME) {
        this.EMP_NAME = EMP_NAME;
    }

    public String getWALLET_RSD() {
        return WALLET_RSD;
    }

    public void setWALLET_RSD(String WALLET_RSD) {
        this.WALLET_RSD = WALLET_RSD;
    }

    public String getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(String AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getAMOUNT_DESC() {
        return AMOUNT_DESC;
    }

    public void setAMOUNT_DESC(String AMOUNT_DESC) {
        this.AMOUNT_DESC = AMOUNT_DESC;
    }

    public String getINSERTED_AT() {
        return INSERTED_AT;
    }

    public void setINSERTED_AT(String INSERTED_AT) {
        this.INSERTED_AT = INSERTED_AT;
    }

    public String getACC_NO() {
        return ACC_NO;
    }

    public void setACC_NO(String ACC_NO) {
        this.ACC_NO = ACC_NO;
    }

    public String getMINS_NAME() {
        return MINS_NAME;
    }

    public void setMINS_NAME(String MINS_NAME) {
        this.MINS_NAME = MINS_NAME;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TAGNO);
        dest.writeString(this.BOND_NO);
        dest.writeString(this.WALLET_RSD);
        dest.writeString(this.AMOUNT);
        dest.writeString(this.AMOUNT_DESC);
        dest.writeString(this.INSERTED_AT);
        dest.writeString(this.ACC_NO);
        dest.writeString(this.EMP_SSN);
        dest.writeString(this.EMP_NAME);
        dest.writeString(this.MINS_NAME);
    }

    public BondClass() {
    }

    protected BondClass(Parcel in) {
        this.TAGNO = in.readString();
        this.BOND_NO = in.readString();
        this.WALLET_RSD = in.readString();
        this.AMOUNT = in.readString();
        this.AMOUNT_DESC = in.readString();
        this.INSERTED_AT = in.readString();
        this.ACC_NO = in.readString();
        this.EMP_SSN = in.readString();
        this.EMP_NAME = in.readString();
        this.MINS_NAME = in.readString();
    }

    public static final Creator<BondClass> CREATOR = new Creator<BondClass>() {
        @Override
        public BondClass createFromParcel(Parcel source) {
            return new BondClass(source);
        }

        @Override
        public BondClass[] newArray(int size) {
            return new BondClass[size];
        }
    };
}
