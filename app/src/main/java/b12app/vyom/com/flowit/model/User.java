package b12app.vyom.com.flowit.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {


    /**
     * msg : success
     * userid : 21
     * userfirstname : aamir
     * userlastname : husain
     * useremail : vyomhpatel@gmail.com
     * appapikey  : 2152a1ad49be08259a1c8609892e9128
     */

    private String msg;
    private String userid;
    private String userfirstname;
    private String userlastname;
    private String useremail;
    private String appapikey;

    public User(String msg, String userid, String userfirstname, String userlastname, String useremail, String appapikey) {
        this.msg = msg;
        this.userid = userid;
        this.userfirstname = userfirstname;
        this.userlastname = userlastname;
        this.useremail = useremail;
        this.appapikey = appapikey;
    }

    protected User(Parcel in) {
        msg = in.readString();
        userid = in.readString();
        userfirstname = in.readString();
        userlastname = in.readString();
        useremail = in.readString();
        appapikey = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }

    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(msg);
        dest.writeString(userid);
        dest.writeString(userfirstname);
        dest.writeString(userlastname);
        dest.writeString(useremail);
        dest.writeString(appapikey);
    }
}
