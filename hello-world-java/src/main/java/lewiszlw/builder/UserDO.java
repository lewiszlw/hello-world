package lewiszlw.builder;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2018/11/6
 */
public class UserDO {

    private Integer id;

    private String name;

    private String address;

    public UserDO() {}

    public UserDO(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**=====================builder=======================*/

    public static UserDOBuilder builder() {
        return new UserDOBuilder();
    }

    static class UserDOBuilder {

        private Integer id;

        private String name;

        private String address;

        public UserDO build() {
            return new UserDO(this.id, this.name, this.address);
        }

        public UserDOBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public UserDOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserDOBuilder address(String address) {
            this.address = address;
            return this;
        }
    }

}
