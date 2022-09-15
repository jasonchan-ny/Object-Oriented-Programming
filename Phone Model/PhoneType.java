package edu.cuny.csi.csc330.lab4;

public enum PhoneType {
    IPHONE {
        @Override
        public PhoneBrand getBrand() {
            return PhoneBrand.APPLE;
        }

        @Override
        public String getFriendlyName() {
            return "iPhone";
        }

        @Override
        public boolean isSupportingCellularData() {
            return true;
        }

		@Override
		public boolean isSupportingWifi() {
			return true;
		}
    },
    
    GALAXY {
        @Override
        public PhoneBrand getBrand() {
            return PhoneBrand.SAMSUNG;
        }

        @Override
        public String getFriendlyName() {
            return "Galaxy";
        }

        @Override
        public boolean isSupportingCellularData() {
            return true;
        }

		@Override
		public boolean isSupportingWifi() {
			return true;
		}
    },

    RAZOR {
        @Override
        public PhoneBrand getBrand() {
            return PhoneBrand.MOTOROLA;
        }

        @Override
        public String getFriendlyName() {
            return "Razr";
        }

        @Override
        public boolean isSupportingCellularData() {
            return false;
        }

		@Override
		public boolean isSupportingWifi() {
			return false;
		}
    };

    public abstract PhoneBrand getBrand();
    public abstract String getFriendlyName();
    public abstract boolean isSupportingCellularData();
    public abstract boolean isSupportingWifi();
}