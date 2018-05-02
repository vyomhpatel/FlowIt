package b12app.vyom.com.flowit.model;

import com.google.gson.Gson;

import java.util.List;

public class FcmResponse {


    /**
     * multicast_id : 108
     * success : 1
     * failure : 0
     * results : [{"message_id":"1:08"}]
     */

    private long multicast_id;
    private int success;
    private int failure;
    private int canonical_ids;
    private List<ResultsBean> results;

    public FcmResponse() {
    }

    public FcmResponse(long multicast_id, int success, int failure, int canonical_ids, List<ResultsBean> results) {
        this.multicast_id = multicast_id;
        this.success = success;
        this.failure = failure;
        this.canonical_ids = canonical_ids;
        this.results = results;
    }

    public long getMulticast_id() {
        return multicast_id;
    }

    public void setMulticast_id(int multicast_id) {
        this.multicast_id = multicast_id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * message_id : 1:08
         */

        private String message_id;



        public String getMessage_id() {
            return message_id;
        }

        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }
    }
}
