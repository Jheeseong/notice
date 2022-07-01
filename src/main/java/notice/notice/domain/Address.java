package notice.notice.domain;

import lombok.Getter;

@Getter
public class Address {
    private String zipcode;

    private String address_main;

    private String address_detail;

    protected Address() {
    }

    public Address(String zipcode, String address_main, String address_detail) {
        this.zipcode = zipcode;
        this.address_main = address_main;
        this.address_detail = address_detail;
    }
}
