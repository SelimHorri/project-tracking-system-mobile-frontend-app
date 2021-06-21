package com.selimhorri.pack.constant;

import com.selimhorri.pack.R;

abstract public class BackendApiUrlConstant {

    private static final String BASE_API_URL = "https://project-tracking-system-heroku.herokuapp.com/app/api"; // String.valueOf(R.string.base_api_url) + "/app/api"; // https://project-tracking-system-heroku.herokuapp.com

    private BackendApiUrlConstant() {

    }

    public static final class LocationBackendUrl {
        public static final String LOCATION_API_URL = BackendApiUrlConstant.BASE_API_URL + "/locations";
        private LocationBackendUrl() {}
    }

    public static final class DepartmentBackendUrl {
        public static final String DEPARTMENT_API_URL = BackendApiUrlConstant.BASE_API_URL + "/departments";
        private DepartmentBackendUrl() {}
    }

    public static final class CredentialBackendUrl {
        public static final String CREDENTIAL_API_URL = BackendApiUrlConstant.BASE_API_URL + "/credentials";
        private CredentialBackendUrl() {}
    }

    public static final class EmployeeBackendUrl {
        public static final String EMPLOYEE_API_URL = BackendApiUrlConstant.BASE_API_URL + "/employees";
        private EmployeeBackendUrl() {}
    }

    public static final class AssignmentBackendUrl {
        public static final String ASSIGNMENT_API_URL = BackendApiUrlConstant.BASE_API_URL + "/assignments";
        private AssignmentBackendUrl() {}
    }

    public static final class ProjectBackendUrl {
        public static final String PROJECT_API_URL = BackendApiUrlConstant.BASE_API_URL + "/projects";
        private ProjectBackendUrl() {}
    }

    public static final class AuthenticationBackendUrl {
        public static final String AUTHENTICATE_API_URL = BackendApiUrlConstant.BASE_API_URL + "/authenticate";
        private AuthenticationBackendUrl() {}
    }



}
