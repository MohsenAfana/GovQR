package ps.gov.mtit.govqr;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ps.gov.mtit.govqr.models.SSOUserClass;


public class AppZone {

    public static SSOUserClass mCurrentUser;

    //public static String TOKEN_URL = "http://elogin.gov.ps/sso-auth/token/get";
    //public static String SSO_SECRET = "dc6a4cb1da75420f463805ccb2898be7";
    //public static String SSO_CLIENTID = "4e732ced3463d06de0ca9a15b6153677";

    public static String TOKEN_URL = "https://ssoidp.gov.ps/sso/module.php/sspoauth2/token.php";
    public static String SSO_SECRET = "_8f7ccd0606ee18a90586a397b54435e188ce0196f8";
    public static String SSO_CLIENTID = "gov_qr_app";

//    public static String TOKEN_URL = "https://ssoidp.gov.ps/sso/module.php/sspoauth2/token.php";
//    public static String SSO_SECRET = "_cd14029a9b8b3a3847413423229e6e27d86d57d108";
//    public static String SSO_CLIENTID = "_81f9668dca30b7e05166eb73ff00ae2d99ce844e1d";


    //public static String BASE_URL = "https://eapp.gov.ps/epayment/c_service";
    public static String BASE_URL = "https://bonds.eapp.gov.ps/bonds/index.php/Api1";


    public static String passMd5Encryption(String password) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

}
