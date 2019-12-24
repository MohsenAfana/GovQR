package ps.gov.mtit.govqr.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SSOUserClass implements Parcelable {

    String USERIDENTITY,FULLNAME, NICKNAME, USEREMAIL, USERTELEPHONE, USERMOBILE, USERJOB, MARK_ID, GOV_ID, GOV_NAME, CITY_GOV_ID, CITY_ID, CITY_NAME,
            PART_CITY_ID, PART_ID, PART_NAME, ADDRESS_NAME, MAMRK_TYPE_ID, MAMRK_TYPE_NAME, ADDRESS_DET, TYPE_ID, MARK_PART_ID, STREET;


    public String getUSERIDENTITY() {
        return USERIDENTITY;
    }

    public void setUSERIDENTITY(String USERIDENTITY) {
        this.USERIDENTITY = USERIDENTITY;
    }

    public String getFULLNAME() {
        return FULLNAME;
    }

    public void setFULLNAME(String FULLNAME) {
        this.FULLNAME = FULLNAME;
    }

    public String getNICKNAME() {
        return NICKNAME;
    }

    public void setNICKNAME(String NICKNAME) {
        this.NICKNAME = NICKNAME;
    }

    public String getUSEREMAIL() {
        return USEREMAIL;
    }

    public void setUSEREMAIL(String USEREMAIL) {
        this.USEREMAIL = USEREMAIL;
    }

    public String getUSERTELEPHONE() {
        return USERTELEPHONE;
    }

    public void setUSERTELEPHONE(String USERTELEPHONE) {
        this.USERTELEPHONE = USERTELEPHONE;
    }

    public String getUSERMOBILE() {
        return USERMOBILE;
    }

    public void setUSERMOBILE(String USERMOBILE) {
        this.USERMOBILE = USERMOBILE;
    }

    public String getUSERJOB() {
        return USERJOB;
    }

    public void setUSERJOB(String USERJOB) {
        this.USERJOB = USERJOB;
    }

    public String getMARK_ID() {
        return MARK_ID;
    }

    public void setMARK_ID(String MARK_ID) {
        this.MARK_ID = MARK_ID;
    }

    public String getGOV_ID() {
        return GOV_ID;
    }

    public void setGOV_ID(String GOV_ID) {
        this.GOV_ID = GOV_ID;
    }

    public String getGOV_NAME() {
        return GOV_NAME;
    }

    public void setGOV_NAME(String GOV_NAME) {
        this.GOV_NAME = GOV_NAME;
    }

    public String getCITY_GOV_ID() {
        return CITY_GOV_ID;
    }

    public void setCITY_GOV_ID(String CITY_GOV_ID) {
        this.CITY_GOV_ID = CITY_GOV_ID;
    }

    public String getCITY_ID() {
        return CITY_ID;
    }

    public void setCITY_ID(String CITY_ID) {
        this.CITY_ID = CITY_ID;
    }

    public String getCITY_NAME() {
        return CITY_NAME;
    }

    public void setCITY_NAME(String CITY_NAME) {
        this.CITY_NAME = CITY_NAME;
    }

    public String getPART_CITY_ID() {
        return PART_CITY_ID;
    }

    public void setPART_CITY_ID(String PART_CITY_ID) {
        this.PART_CITY_ID = PART_CITY_ID;
    }

    public String getPART_ID() {
        return PART_ID;
    }

    public void setPART_ID(String PART_ID) {
        this.PART_ID = PART_ID;
    }

    public String getPART_NAME() {
        return PART_NAME;
    }

    public void setPART_NAME(String PART_NAME) {
        this.PART_NAME = PART_NAME;
    }

    public String getADDRESS_NAME() {
        return ADDRESS_NAME;
    }

    public void setADDRESS_NAME(String ADDRESS_NAME) {
        this.ADDRESS_NAME = ADDRESS_NAME;
    }

    public String getMAMRK_TYPE_ID() {
        return MAMRK_TYPE_ID;
    }

    public void setMAMRK_TYPE_ID(String MAMRK_TYPE_ID) {
        this.MAMRK_TYPE_ID = MAMRK_TYPE_ID;
    }

    public String getMAMRK_TYPE_NAME() {
        return MAMRK_TYPE_NAME;
    }

    public void setMAMRK_TYPE_NAME(String MAMRK_TYPE_NAME) {
        this.MAMRK_TYPE_NAME = MAMRK_TYPE_NAME;
    }

    public String getADDRESS_DET() {
        return ADDRESS_DET;
    }

    public void setADDRESS_DET(String ADDRESS_DET) {
        this.ADDRESS_DET = ADDRESS_DET;
    }

    public String getTYPE_ID() {
        return TYPE_ID;
    }

    public void setTYPE_ID(String TYPE_ID) {
        this.TYPE_ID = TYPE_ID;
    }

    public String getMARK_PART_ID() {
        return MARK_PART_ID;
    }

    public void setMARK_PART_ID(String MARK_PART_ID) {
        this.MARK_PART_ID = MARK_PART_ID;
    }

    public String getSTREET() {
        return STREET;
    }

    public void setSTREET(String STREET) {
        this.STREET = STREET;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.USERIDENTITY);
        dest.writeString(this.FULLNAME);
        dest.writeString(this.NICKNAME);
        dest.writeString(this.USEREMAIL);
        dest.writeString(this.USERTELEPHONE);
        dest.writeString(this.USERMOBILE);
        dest.writeString(this.USERJOB);
        dest.writeString(this.MARK_ID);
        dest.writeString(this.GOV_ID);
        dest.writeString(this.GOV_NAME);
        dest.writeString(this.CITY_GOV_ID);
        dest.writeString(this.CITY_ID);
        dest.writeString(this.CITY_NAME);
        dest.writeString(this.PART_CITY_ID);
        dest.writeString(this.PART_ID);
        dest.writeString(this.PART_NAME);
        dest.writeString(this.ADDRESS_NAME);
        dest.writeString(this.MAMRK_TYPE_ID);
        dest.writeString(this.MAMRK_TYPE_NAME);
        dest.writeString(this.ADDRESS_DET);
        dest.writeString(this.TYPE_ID);
        dest.writeString(this.MARK_PART_ID);
        dest.writeString(this.STREET);
    }

    public SSOUserClass() {
    }

    protected SSOUserClass(Parcel in) {
        this.USERIDENTITY = in.readString();
        this.FULLNAME = in.readString();
        this.NICKNAME = in.readString();
        this.USEREMAIL = in.readString();
        this.USERTELEPHONE = in.readString();
        this.USERMOBILE = in.readString();
        this.USERJOB = in.readString();
        this.MARK_ID = in.readString();
        this.GOV_ID = in.readString();
        this.GOV_NAME = in.readString();
        this.CITY_GOV_ID = in.readString();
        this.CITY_ID = in.readString();
        this.CITY_NAME = in.readString();
        this.PART_CITY_ID = in.readString();
        this.PART_ID = in.readString();
        this.PART_NAME = in.readString();
        this.ADDRESS_NAME = in.readString();
        this.MAMRK_TYPE_ID = in.readString();
        this.MAMRK_TYPE_NAME = in.readString();
        this.ADDRESS_DET = in.readString();
        this.TYPE_ID = in.readString();
        this.MARK_PART_ID = in.readString();
        this.STREET = in.readString();
    }

    public static final Creator<SSOUserClass> CREATOR = new Creator<SSOUserClass>() {
        @Override
        public SSOUserClass createFromParcel(Parcel source) {
            return new SSOUserClass(source);
        }

        @Override
        public SSOUserClass[] newArray(int size) {
            return new SSOUserClass[size];
        }
    };
}
