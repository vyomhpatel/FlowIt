package b12app.vyom.com.flowit.model;

import java.util.List;

public class Employee {

    private List<EmployeesBean> employees;



    public List<EmployeesBean> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeesBean> employees) {
        this.employees = employees;
    }

    public static class EmployeesBean {
        /**
         * empid : 21
         * empfirstname : aamir
         * emplastname : husain
         * empemail : vyomhpatel@gmail.com
         * empmobile : 7325078484
         * empdesignation : MNGR
         * dateofjoining : 2018-04-25 08:51:51
         */

        private String empid;
        private String empfirstname;
        private String emplastname;
        private String empemail;
        private String empmobile;
        private String empdesignation;
        private String dateofjoining;

        public EmployeesBean(String empid, String empfirstname, String emplastname, String empemail, String empmobile, String empdesignation, String dateofjoining) {
            this.empid = empid;
            this.empfirstname = empfirstname;
            this.emplastname = emplastname;
            this.empemail = empemail;
            this.empmobile = empmobile;
            this.empdesignation = empdesignation;
            this.dateofjoining = dateofjoining;
        }

        public String getEmpid() {
            return empid;
        }

        public void setEmpid(String empid) {
            this.empid = empid;
        }

        public String getEmpfirstname() {
            return empfirstname;
        }

        public void setEmpfirstname(String empfirstname) {
            this.empfirstname = empfirstname;
        }

        public String getEmplastname() {
            return emplastname;
        }

        public void setEmplastname(String emplastname) {
            this.emplastname = emplastname;
        }

        public String getEmpemail() {
            return empemail;
        }

        public void setEmpemail(String empemail) {
            this.empemail = empemail;
        }

        public String getEmpmobile() {
            return empmobile;
        }

        public void setEmpmobile(String empmobile) {
            this.empmobile = empmobile;
        }

        public String getEmpdesignation() {
            return empdesignation;
        }

        public void setEmpdesignation(String empdesignation) {
            this.empdesignation = empdesignation;
        }

        public String getDateofjoining() {
            return dateofjoining;
        }

        public void setDateofjoining(String dateofjoining) {
            this.dateofjoining = dateofjoining;
        }
    }
}
