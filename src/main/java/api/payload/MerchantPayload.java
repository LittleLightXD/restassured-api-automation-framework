package api.payload;

public class MerchantPayload {

    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String phone;
    private String commission;
    private Integer productLimit;
    private String password;
    private String zoneId;
    private String city;
    private String state;
    private String country;
    private CompanyDetails company;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public Integer getProductLimit() {
        return productLimit;
    }

    public void setProductLimit(Integer productLimit) {
        this.productLimit = productLimit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CompanyDetails getCompany() {
        return company;
    }

    public void setCompany(CompanyDetails company) {
        this.company = company;
    }


    public static class CompanyDetails {

        private String name;
        private String phone;
        private String email;
        private String webAddress;
        private String gstn;
        private String registerNumber;
        private AddressDetails address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWebAddress() {
            return webAddress;
        }

        public void setWebAddress(String webAddress) {
            this.webAddress = webAddress;
        }

        public String getGstn() {
            return gstn;
        }

        public void setGstn(String gstn) {
            this.gstn = gstn;
        }

        public String getRegisterNumber() {
            return registerNumber;
        }

        public void setRegisterNumber(String registerNumber) {
            this.registerNumber = registerNumber;
        }

        public AddressDetails getAddress() {
            return address;
        }

        public void setAddress(AddressDetails address) {
            this.address = address;
        }
    }


    public static class AddressDetails {

        private String buildingInfo;
        private String landmark;
        private String country;
        private String state;
        private String city;
        private String type;
        private String pincode;
        private String streetInfo;

        public String getBuildingInfo() {
            return buildingInfo;
        }

        public void setBuildingInfo(String buildingInfo) {
            this.buildingInfo = buildingInfo;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getStreetInfo() {
            return streetInfo;
        }

        public void setStreetInfo(String streetInfo) {
            this.streetInfo = streetInfo;
        }
    }
}