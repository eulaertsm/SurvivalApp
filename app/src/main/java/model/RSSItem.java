package model;

/**
 * Created by maxim on 16/08/15.
 */
public class RSSItem {
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

    }

