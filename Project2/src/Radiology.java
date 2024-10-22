package src;

public enum Radiology {
    CATSCAN,
    ULTRASOUND,
    XRAY;

    public static Radiology getImagingService(String s){
        return switch (s.toLowerCase()) {
            case "catscan" -> Radiology.CATSCAN;
            case "ultrasound" -> Radiology.ULTRASOUND;
            case "xray" -> Radiology.XRAY;
            default -> {
                System.out.println("Invalid Service");
                yield null;
            }
        };
    }

}
