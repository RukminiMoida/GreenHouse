package com.PG.GreenHouse.model;

/**
 * Created by Akash on 3/30/2018.
 */

public class Events {

    public static class ActivityServiceMessage{
        private String latitude;
        private String longitude;
        private String address;
        private String time;

        public ActivityServiceMessage(String latitude, String longitude, String address, String time) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.address = address;
            this.time = time;
        }

        public String getLatitude() {
            return latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public String getAddress() {
            return address;
        }

        public String getTime() {
            return time;
        }
    }
}
