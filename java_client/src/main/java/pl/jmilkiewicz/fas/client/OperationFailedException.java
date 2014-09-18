package pl.jmilkiewicz.fas.client;

public class OperationFailedException extends RuntimeException {
    private final int responseStatus;

    public OperationFailedException(int responseStatus) {
        super("operation failed with status" + responseStatus);
        this.responseStatus = responseStatus;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
}
