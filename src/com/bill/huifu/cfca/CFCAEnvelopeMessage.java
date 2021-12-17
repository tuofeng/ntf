
package com.bill.huifu.cfca;


import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.lib.crypto.JCrypto;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.CertUtil;
import cfca.sadk.util.EnvelopeUtil;
import cfca.sadk.util.KeyUtil;
import cfca.sadk.x509.certificate.X509Cert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;


public class CFCAEnvelopeMessage {
    static Logger logger = LoggerFactory.getLogger(CFCAEnvelopeMessage.class);

    public CFCAEnvelopeMessage() {
    }

    /**
     * @deprecated
     */
    @Deprecated
    public static CryptResult encryptEnvelopeMessage(String pfxFilePath, String pfxFilePwd, String srcData, String symAlg, String charset) {
        try {
            X509Cert e = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePwd);
            X509Cert[] certs = new X509Cert[]{e};
            byte[] encryptedData = EnvelopeUtil.envelopeMessage(srcData.getBytes(charset), symAlg, certs, getSession());
            CryptResult cryptResult = new CryptResult(CryptEnum.SUCCESS);
            cryptResult.setContent(encryptedData);
            return cryptResult;
        } catch (Exception var9) {
            logger.error("encryptEnvelopeMessage error:", var9);
            return new CryptResult(CryptEnum.FAILED);
        }
    }

    public static CryptResult encryptEnvelopeMessage(String certFilePath, String srcData, String symAlg, String charset) {
        try {
            X509Cert[] e = new X509Cert[]{new X509Cert(certFilePath)};
            byte[] encryptedData = EnvelopeUtil.envelopeMessage(srcData.getBytes(charset), symAlg, e, getSession());
            CryptResult cryptResult = new CryptResult(CryptEnum.SUCCESS);
            cryptResult.setContent(encryptedData);
            return cryptResult;
        } catch (Exception var7) {
            logger.error("encryptEnvelopeMessage error:", var7);
            return new CryptResult(CryptEnum.FAILED);
        }
    }

    public static CryptResult encryptEnvelopeMessageWithSM2(String sm2FilePath, String srcData, String symAlg, String charset) {
        try {
            X509Cert e = CertUtil.getCertFromSM2(sm2FilePath);
            X509Cert[] certs = new X509Cert[]{e};
            byte[] encryptedData = EnvelopeUtil.envelopeMessage(srcData.getBytes(charset), symAlg, certs, getSession());
            CryptResult cryptResult = new CryptResult(CryptEnum.SUCCESS);
            cryptResult.setContent(encryptedData);
            return cryptResult;
        } catch (Exception var8) {
            logger.error("encryptEnvelopeMessageWithSM2 error:", var8);
            return new CryptResult(CryptEnum.FAILED);
        }
    }

    public static CryptResult decryptEnvelopeMessage(String pfxFilePath, String pfxFilePwd, byte[] encryptedData) {
        try {
            PrivateKey e = KeyUtil.getPrivateKeyFromPFX(pfxFilePath, pfxFilePwd);
            X509Cert x509Cert = CertUtil.getCertFromPFX(pfxFilePath, pfxFilePwd);
            byte[] sourceData = EnvelopeUtil.openEvelopedMessage(encryptedData, e, x509Cert, getSession());
            CryptResult cryptResult = new CryptResult(CryptEnum.SUCCESS);
            cryptResult.setContent(sourceData);
            return cryptResult;
        } catch (Exception var7) {
            logger.error("decryptEnvelopeMessage error:", var7);
            return new CryptResult(CryptEnum.FAILED);
        }
    }

    public static CryptResult decryptEnvelopeMessageWithSM2(String sm2FilePath, String sm2FilePwd, byte[] encryptedData) {
        try {
            SM2PrivateKey e = KeyUtil.getPrivateKeyFromSM2(sm2FilePath, sm2FilePwd);
            X509Cert x509Cert = CertUtil.getCertFromSM2(sm2FilePath);
            byte[] sourceData = EnvelopeUtil.openEvelopedMessage(encryptedData, e, x509Cert, getSession());
            CryptResult cryptResult = new CryptResult(CryptEnum.SUCCESS);
            cryptResult.setContent(sourceData);
            return cryptResult;
        } catch (Exception var7) {
            logger.error("decryptEnvelopeMessageWithSM2 error:", var7);
            return new CryptResult(CryptEnum.FAILED);
        }
    }

    public static Session getSession() throws Exception {
        JCrypto.getInstance().initialize("JSOFT_LIB", (Object) null);
        return JCrypto.getInstance().openSession("JSOFT_LIB");
    }
}