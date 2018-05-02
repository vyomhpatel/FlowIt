package b12app.vyom.com.flowit.model;

public class Sender {


    public Sender(String to, NotificationBean notification) {
        this.to = to;
        this.notification = notification;
    }

    /**
     * to : eNh8RzL09LY:APA91bEKwVeY-FGl_h-9oTZ7BZQJ79xR_EtPBpnoq3ecuPwTpbLWgrVaTuqjoakDZCuf0SVVsc5QbnOOAWYpHuLH7_QYiwT7LE2XMSA_rokM6NB0HlwfcuY-oYNnZsqxveumhg7tR0G2
     * notification : {"body":"great match!","title":"Portugal vs. Denmark","icon":"myicon"}
     */


    private String to;
    private NotificationBean notification;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public NotificationBean getNotification() {
        return notification;
    }

    public void setNotification(NotificationBean notification) {
        this.notification = notification;
    }

    public static class NotificationBean {
        /**
         * body : great match!
         * title : Portugal vs. Denmark
         * icon : myicon
         */

        private String body;
        private String title;
        private String icon;

        public NotificationBean(String body, String title) {
            this.body = body;
            this.title = title;
        }


        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }
}