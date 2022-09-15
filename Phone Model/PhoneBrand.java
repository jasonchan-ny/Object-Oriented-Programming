package edu.cuny.csi.csc330.lab4;

public enum PhoneBrand {
    APPLE {
        @Override
        public String getFriendlyName() {
            return "Apple";
        }
    },
    SAMSUNG {
        @Override
        public String getFriendlyName() {
            return "Samsung";
        }
    },
    MOTOROLA {
        @Override
        public String getFriendlyName() {
            return "Motorola";
        }
    };

    public abstract String getFriendlyName();
}
