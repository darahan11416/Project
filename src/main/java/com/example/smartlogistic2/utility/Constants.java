package com.example.smartlogistic2.utility;

public class Constants
{
    public enum UserRole
    {
        ROLE_COURIER("Courier"),

        ROLE_CUSTOMER("Customer"),

        ROLE_ADMIN("Admin"),

        ROLE_DELIVERY("Delivery");

        private String role;

        private  UserRole(String role)
        {
            this.role = role;
        }

        public String value()
        {
            return role;
        }

    }

    public enum ActiveStatus
    {
        ACTIVE("Active"),
        DEACTIVATED("Deactivated");
        private String status;

        private ActiveStatus(String status)
        {
            this.status = status;
        }

        public String value()
        {
            return status;
        }
    }



}
