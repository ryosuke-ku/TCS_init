    private static void switchOnAction(TristateCheckBox tristateBox) {
        switch(tristateBox.getState()) {
        case SELECTED:
            System.out.println("Selected"); break;
        case DESELECTED:
            System.out.println("Not Selected"); break;
        case INDETERMINATE:
            System.out.println("Tristate Selected"); break;
        default:
            System.err.println("Unexpected state: " + tristateBox.getState()); break;
        }
    }
