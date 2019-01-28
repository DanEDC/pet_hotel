public enum Service {

    WASHING("Washing"),
    HAIRCUT("Haircut"),
    TAMING("Personal taming"),
    TRANSPORT("Transport");

    String serviceName;

    Service(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    @Override
    public String toString() {
        return serviceName;
    }
}
