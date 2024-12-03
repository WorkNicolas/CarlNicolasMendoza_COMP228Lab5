package ca.centennialcollege.lab5;

public class Player {
    private int player_id;
    private String first_name;
    private String last_name;
    private String address;
    private String postal_code;
    private String province;
    private String phone_number;

    public Player(int player_id, String first_name, String last_name, String address, String postal_code, String province, String phone_number) {
        this.player_id = player_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.postal_code = postal_code;
        this.province = province;
        this.phone_number = phone_number;
    }

    public int getPlayerId() {
        return player_id;
    }

    public void setPlayerId(int player_id) {
        this.player_id = player_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postal_code;
    }

    public void setPostalCode(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return player_id + " - " + first_name + " " + last_name;
    }
}
