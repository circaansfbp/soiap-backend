package pt.fmbp.soiapbackend.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "K5BmL2pNgszJKzTB3ArUKG";
    public static final String RSA_PRIVATE = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEA8DP6QwfyAWFzprwlfwA5dVIKn9e0XCw9hYOGfYqLTxPRTdvH\n" +
            "IpFNeNrVuohjrYpgbipB3PRdwP6RDauhhmgRuDxW8SMbmNkzYoz7Pf7tWqQOLdKn\n" +
            "i7GXybINCk/Z9UwHsqfIlju3V+upPnGWmCLE3zhbHEFSiZanXdSWqLlxwq80TdxR\n" +
            "yXwJexlZc/YdM0F1GTVJ/U18js/ogS68Rm4QKP1+FXVAXIfnBLRrP+2NNuoRkrA+\n" +
            "eQmTEqRDIwKjT26kDrcdA5gxolhkAFY7D6spnZh2dcn0JKmxQ9Iqi48NFWHE4c7X\n" +
            "1CQ/OdQhQIuiPC406uCSmOme0trBezZOFZjpLQIDAQABAoIBADeX0BQzgo9PPrUY\n" +
            "VpVON/4vPsY/5j5u/s4n4rirmBJ1Q7wBYdVeS8/s1gqWBnvjyu20WgoJhKlmQawo\n" +
            "TPoOnAtH69djiL+XhY0JlByFI+oWjjGMz7pFIBxOWTZJxOhA8IcozgP3TjfLEyCg\n" +
            "qXoMkgQXxv6DH9c4RwQwb64dPXcRIwC3JTQsynY2Nzs3vHRFdcxMW0nJtoikGlv/\n" +
            "xFYdCZXdM6p7GcNleqMB48hNlQpB7QbpavZ3OefepduNCYlh73c5oVzjUDq9yDIf\n" +
            "hRlv/UMzsPQuetuWaJLS74/flJ/cn1qD15Vrf6r9sar5EEsEL7NH1jMUeaffcW5x\n" +
            "BPujMAECgYEA+GOgEnZ3Pxxee5b4UQTRINCPfZOm9MxZ7RAVtfzOJ4DegcWnf2Q5\n" +
            "czMrO4+1//bxFxZLMT+CefwH8sCqvIPEeZ28NMXngFCEGnaVDXggieLzpjSLIwA5\n" +
            "cVGURiDYX3MlgVY4vLoHqkBEjKKxSJBB4lsbBwurzsZBJsvdzo/dfN0CgYEA95Aj\n" +
            "1/okI27ryZX826QlFmoYtVr727P4F4ZCmvtKClzvTB1Tn4KUslM2+paKOsE0iOtF\n" +
            "9xVOHOJZHKJLZMwC3D6ZRupwM4NH0NSyNDEcEEhmXRWCEtzbHcPezBDN03L0/GdY\n" +
            "xVehFjcszS3f1L3wu3tkK4CjrHcc9S9N6KAO8JECgYEAg+XbTZKM1f2JipVrfRJS\n" +
            "vtshJyzt3lZZdlIxRTdefYmoHj9IJ5SgETVdwr4YhjiqY3kaKqev8HjN+7EluP87\n" +
            "qN0C4P8qZG/dBnRryAA/Pxk+CsciD08CMdOBKhAs9dqeUuTnMgiTI2wHOqr1+zNn\n" +
            "WPzKCF7BOAb8984QFa09jx0CgYEAjqnC7iiZgM3gFThZEh4CvWsAuCopb8/DjZLy\n" +
            "eSuM3HH3+O4Bja+Lj0RDL59nIzrJ0ol6tUvSyvRplAW22uxyTYfJfsOFSzpBPYgE\n" +
            "9ezDFmMSklvdrI8XuoxwrTqV0l+x7G6GCptYGlxLdiYUkh2vR+twq10jdTTjPCw5\n" +
            "gnASi/ECgYAl5fpr3pJ2AUxbKNMzhTZFv+C3IOyZ2iE+CGRoOlkSf+f4i0OkOX9f\n" +
            "VV4/Ja5n5wS4BenS8sz3aWc7+3zDfv/Rvidj16AXEpNmNN0ub/5LK3Uf4Afv8LIA\n" +
            "AayLl1cxuSJgtCZHne1cJHh6Ag6TVl8f0YhYQJzTUQru8EMET7RJZQ==\n" +
            "-----END RSA PRIVATE KEY-----";

    public static final String RSA_PUBLIC = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8DP6QwfyAWFzprwlfwA5\n" +
            "dVIKn9e0XCw9hYOGfYqLTxPRTdvHIpFNeNrVuohjrYpgbipB3PRdwP6RDauhhmgR\n" +
            "uDxW8SMbmNkzYoz7Pf7tWqQOLdKni7GXybINCk/Z9UwHsqfIlju3V+upPnGWmCLE\n" +
            "3zhbHEFSiZanXdSWqLlxwq80TdxRyXwJexlZc/YdM0F1GTVJ/U18js/ogS68Rm4Q\n" +
            "KP1+FXVAXIfnBLRrP+2NNuoRkrA+eQmTEqRDIwKjT26kDrcdA5gxolhkAFY7D6sp\n" +
            "nZh2dcn0JKmxQ9Iqi48NFWHE4c7X1CQ/OdQhQIuiPC406uCSmOme0trBezZOFZjp\n" +
            "LQIDAQAB\n" +
            "-----END PUBLIC KEY-----";
}
