package b12app.vyom.com.flowit.model;

public class Sender {


    /**
     * notification : {"body":"5x1"," title":"15:10"}
     * to : bk3RNwTe3H0:CI2k_HHwgIpoDKCIZvvDMExUdFQ3P1...
     */

    private NotificationBean notification;
    private String to;

    public Sender( String to,NotificationBean notification) {
        this.notification = notification;
        this.to = to;
    }

    public static class NotificationBean {
        public NotificationBean(String body, String title) {
            this.body = body;
            this.title = title;
        }

        /**
         * body : 5x1
         *  title : 15:10
         */



        private String body;
        private String title;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
