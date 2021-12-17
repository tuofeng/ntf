package com.bill.huifu.cfca;


public class SignUtil {
    private static SignUtil instance = null;
    public static final String CHARSET_UTF8 = "UTF-8";

    private SignUtil() {
    }

    public static SignUtil getInstance() {
        if (instance == null) {
            synchronized (SignUtil.class) {
                if (instance == null) {
                    instance = new SignUtil();
                }
            }
        }
        return instance;
    }

    public String signByCFCA(String content, String pfxFilePwd) {
        String MEM_PFX_FILE_PATH = SignUtil.class.getClassLoader().getResource("HF.pfx").getPath();
        return CFCASignature.signature(MEM_PFX_FILE_PATH, pfxFilePwd, content, CHARSET_UTF8).getSign();
    }

    public VerifyResult verifyByCFCA(String signature) {
        String TRUST_CER_PATH = SignUtil.class.getClassLoader().getResource("CFCA_ACS_OCA31.cer").getPath();
        return CFCASignature.verifyChinaPnRSign("NSPOS000001", signature, CHARSET_UTF8, TRUST_CER_PATH);
    }


}
