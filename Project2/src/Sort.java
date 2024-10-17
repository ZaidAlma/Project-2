package src;



public class Sort {

    private static <E> void swap(List<E> list, int i, int j) {
        E temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public static void sortAppointments(List<Appointment> list, char key) {
        switch (key) {
            case 'd': //date
                for (int i = 0; i < list.getSize() - 1; i++) {
                    int index = i;
                    for (int j = i + 1; i < list.getSize(); j++) {
                        if (list.get(j).getDate().compareTo(list.get(index).getDate()) < 0) {
                            index = j;
                        }
                    }
                    swap(list, i, index);
                }
                break;
            case 'p': //patient
                for (int i = 0; i < list.getSize() - 1; i++) {
                int index = i;
                for (int j = i + 1; i < list.getSize(); j++) {
                    if (list.get(j).getPatient().compareTo(list.get(index).getPatient()) < 0) {
                        index = j;
                    }
                }
                swap(list, i, index);
            }
                break;
            case 't': //timeslot
                for (int i = 0; i < list.getSize() - 1; i++) {
                    int index = i;
                    for (int j = i + 1; i < list.getSize(); j++) {
                        if (list.get(j).timeslot.compareTo(list.get(index).timeslot) < 0) {
                            index = j;
                        }
                    }
                    swap(list, i, index);
                }
                break;

            default:
                try {
                    throw new IllegalAccessException("Invalid Sort Key for Appointments"); //We cannot use the print methods
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

        }
    }

    public static void sortProviders(List<Provider> list){
        for (int i = 0; i < list.getSize() - 1; i++) {
            int index = i;
            for (int j = i + 1; i < list.getSize(); j++) {
                if (list.get(j).getLocation().compareTo(list.get(index).getLocation()) < 0) {
                    index = j;
                }
            }
            swap(list, i, index);
        }
    }
}
