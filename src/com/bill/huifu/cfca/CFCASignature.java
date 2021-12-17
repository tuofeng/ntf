package com.bill.huifu.cfca;


import cfca.sadk.algorithm.common.PKCS7SignedData;
import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.system.global.SM2ContextConfig;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

public class CFCASignature {
    private static Logger logger = LoggerFactory.getLogger(CFCASignature.class);
    private static String mechanism = "sha256WithRSAEncryption";
    static Map<String, PrivateKey> priKeyMap = new HashMap();
    static Map<String, X509Cert> x509CertMap = new HashMap();
    static X509Cert[] certs = null;
    static PKCS7SignedData p7 = null;

    public CFCASignature() {
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static String sign(String pfxFilePath, String pfxFilePwd, String content, String charset) {
        try {
            PrivateKey e = KeyUtil.getPrivateKeyFromPFX(pfxFilePath, pfxFilePwd);
            X509Cert cert = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePwd);
            Signature sigUtil = new Signature();
            byte[] signature = sigUtil.p7SignMessageAttach(mechanism, content.getBytes(Charset.forName(charset)), e, cert, getSession());
            return new String(Base64.encodeBase64(signature));
        } catch (Exception var8) {
            logger.error("sign error:", var8);
            return null;
        }
    }

    public static SignResult signature(String pfxFilePath, String pfxFilePwd, String content, String charset) {
        return signature(pfxFilePath, pfxFilePwd, mechanism, content, charset);
    }

    public static void loadPfx(String pfxFilePath, String pfxFilePwd) {
        try {
            PrivateKey e = (PrivateKey) priKeyMap.get(pfxFilePath);
            X509Cert cert = (X509Cert) x509CertMap.get(pfxFilePath);
            if (e == null) {
                e = KeyUtil.getPrivateKeyFromPFX(pfxFilePath, pfxFilePwd);
                priKeyMap.put(pfxFilePath, e);
            }

            if (cert == null) {
                cert = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePwd);
                x509CertMap.put(pfxFilePath, cert);
            }
        } catch (PKIException var4) {
            logger.error("cfca init failed:", var4);
        }

    }

    public static SignResult signature(String pfxFilePath, String pfxFilePwd, String mechanism, String content, String charset) {
        try {
            PrivateKey e = (PrivateKey) priKeyMap.get(pfxFilePath);
            X509Cert cert = (X509Cert) x509CertMap.get(pfxFilePath);
            if (e == null) {
                e = KeyUtil.getPrivateKeyFromPFX(pfxFilePath, pfxFilePwd);
                priKeyMap.put(pfxFilePath, e);
            }

            if (cert == null) {
                cert = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePwd);
                x509CertMap.put(pfxFilePath, cert);
            }

            Signature sigUtil = new Signature();
            byte[] signature = sigUtil.p7SignMessageAttach(mechanism, content.getBytes(Charset.forName(charset)), e, cert, getSession());
            SignResult signResult = new SignResult(SignEnum.SUCCESS);
            signResult.setSign(new String(Base64.encodeBase64(signature)));
            return signResult;
        } catch (Exception var10) {
            logger.error("signature error:", var10);
            return new SignResult(SignEnum.FAILED);
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static SignResult signResultWithSM3_SM2(String sm2FilePath, String sm2FilePwd, String content, String charset) {
        return signResultWithSM3_SM2(sm2FilePath, sm2FilePwd, "sm3WithSM2Encryption", content, charset);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static SignResult signResultWithSM3_SM2(String sm2FilePath, String sm2FilePwd, String mechanism, String content, String charset) {
        try {
            SM2PrivateKey e = KeyUtil.getPrivateKeyFromSM2(sm2FilePath, sm2FilePwd);
            X509Cert cert = CertUtil.getCertFromSM2(sm2FilePath);
            Signature sigUtil = new Signature();
            SM2ContextConfig.setSignFormat(1);
            byte[] signature = sigUtil.p7SignMessageAttach(mechanism, content.getBytes(Charset.forName(charset)), e, cert, getSession());
            SignResult signResult = new SignResult(SignEnum.SUCCESS);
            signResult.setSign(new String(Base64.encodeBase64(signature)));
            return signResult;
        } catch (Exception var10) {
            logger.error("signature error:", var10);
            return new SignResult(SignEnum.FAILED);
        }
    }

    public static VerifyResult verifyChinaPnRSign(String huifuId, String signature, String charset, String trustCerPath) {
        try {
            Signature e = new Signature();
            byte[] bytes = Base64.decodeBase64(signature.getBytes(Charset.forName(charset)));
            X509Cert userX509Cert = e.getSignerX509CertFromP7SignData(bytes);
            CFCAVerify.verifyCer(userX509Cert, trustCerPath);
            CFCAVerify.verifyMer(e, huifuId, bytes);
            return CFCAVerify.verifyP7VerifyMessageAttach(e, bytes, getSession());
        } catch (CFCAException var7) {
            return new VerifyResult(var7.getCode(), var7.getMessage());
        } catch (Exception var8) {
            logger.error("verifyChinaPnRSign error:", var8);
            return new VerifyResult(VerifyEnum.FAILED);
        }
    }

    public static VerifyResult verifyMerSign(String merId, String signature, String charset, String trustCerPath) {
        return verifyMerSign(merId, signature, charset, trustCerPath, (String) null);
    }

    public static VerifyResult verifyMerSign(String merId, String signature, String charset, String trustCerPath, String crlFilePath) {
        try {
            Signature e = new Signature();
            byte[] bytes = Base64.decodeBase64(signature.getBytes(Charset.forName(charset)));
            X509Cert userX509Cert = e.getSignerX509CertFromP7SignData(bytes);
            CFCAVerify.verifyCerDate(userX509Cert, trustCerPath);
            CFCAVerify.verifyCer(userX509Cert, trustCerPath);
            CFCAVerify.verifyCRL(userX509Cert, crlFilePath);
            CFCAVerify.verifyMer(e, merId, bytes);
            return CFCAVerify.verifyP7VerifyMessageAttach(e, bytes, getSession());
        } catch (CFCAException var8) {
            return new VerifyResult(var8.getCode(), var8.getMessage());
        } catch (Exception var9) {
            logger.error("verifyMerSign error:", var9);
            return new VerifyResult(VerifyEnum.FAILED);
        }
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static VerifyResult verifySign(String merId, String signature, String charset, String trustCerPath) {
        return verifySign(merId, signature, charset, trustCerPath, (String) null);
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static VerifyResult verifySign(String merId, String signature, String charset, String trustCerPath, String crlFilePath) {
        try {
            Signature e = new Signature();
            byte[] bytes = Base64.decodeBase64(signature.getBytes(Charset.forName(charset)));
            X509Cert userX509Cert = e.getSignerX509CertFromP7SignData(bytes);
            CFCAVerify.verifyCerDate(userX509Cert, trustCerPath);
            CFCAVerify.verifyCer(userX509Cert, trustCerPath);
            CFCAVerify.verifyCRL(userX509Cert, crlFilePath);
            CFCAVerify.verifyMer(e, merId, bytes);
            return CFCAVerify.verifyP7VerifyMessageAttach(e, bytes, getSession());
        } catch (CFCAException var8) {
            return new VerifyResult(var8.getCode(), var8.getMessage());
        } catch (Exception var9) {
            logger.error("verifySign error:", var9);
            return new VerifyResult(VerifyEnum.FAILED);
        }
    }

    public static Session getSession() throws Exception {
        JCrypto.getInstance().initialize("JSOFT_LIB", (Object) null);
        return JCrypto.getInstance().openSession("JSOFT_LIB");
    }

    public static String getMechanism() {
        return mechanism;
    }

    public static void setMechanism(String mechanism) {
        CFCASignature.mechanism = mechanism;
    }
}
