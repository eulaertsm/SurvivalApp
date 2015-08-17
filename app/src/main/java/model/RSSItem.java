package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maxim on 16/08/15.
 */
public class RSSItem  implements Parcelable {
        private String mTitle;
        private String mLink;
        private String mDescription;

        public String getTitle() {
            return mTitle;
        }
        public String getLink() {
            return mLink;
        }
        public String getDescription() {
            return mDescription;
        }

        public void setTitle(String mTitle) {
            this.mTitle = mTitle;
        }
        public void setLink(String mLink) {
            this.mLink = mLink;
        }
        public void setDescription(String mDescription) {
            this.mDescription = mDescription;
        }

    public RSSItem(){}

    private RSSItem (Parcel in){
        mTitle = in.readString();
        mLink = in.readString();
        mDescription = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mLink);
        dest.writeString(mDescription);
    }

    public static final Parcelable.Creator<RSSItem> CREATOR = new Parcelable.Creator<RSSItem>() {
        public RSSItem createFromParcel(Parcel in) {
            return new RSSItem(in);
        }

        public RSSItem[] newArray(int size) {
            return new RSSItem[size];
        }
    };
}

