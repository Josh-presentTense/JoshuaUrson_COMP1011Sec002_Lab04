package models;

public class Player {
    private int player_id;
    private String first_name;
    private String last_name;
    private String address;
    private String postal_code;
    private String province;
    private long phone_number;

    /**
     * Player constructor with 7 parameters
     * @param player_id : int
     * @param first_name : String
     * @param last_name : String
     * @param address : String
     * @param postal_code : String
     * @param province : String
     * @param phone_number : long
     */
    public Player(int player_id, String first_name, String last_name, String address, String postal_code, String province, long phone_number) {
        setPlayer_id(player_id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setAddress(address);
        setPostal_code(postal_code);
        setProvince(province);
        setPhone_number(phone_number);
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }
}
