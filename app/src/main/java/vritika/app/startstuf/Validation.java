package vritika.app.startstuf;

import android.widget.EditText;

public class Validation {
     private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";


    private static final String REQUIRED_MSG = "required";

        public static boolean hasText(EditText editText) {
       String text = editText.getText().toString().trim();
       if (text.length() == 0) {
          editText.setError(REQUIRED_MSG);
            return false;
           }
       return true;
        }
}