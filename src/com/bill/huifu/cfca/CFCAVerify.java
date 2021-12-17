/*     */ package com.bill.huifu.cfca;
/*     */ 
/*     */

import cfca.sadk.algorithm.common.PKIException;
import cfca.sadk.lib.crypto.Session;
import cfca.sadk.util.Signature;
import cfca.sadk.x509.certificate.X509Cert;
import cfca.sadk.x509.certificate.X509CertVerifier;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */

/*     */
/*     */ public class CFCAVerify
/*     */ {
/*  22 */   private static Logger logger = LoggerFactory.getLogger(CFCASignature.class);
/*     */ 
/*     */   public static void verifyCerDate(X509Cert userX509Cert, String trustCerPath) throws CFCAException {
/*  25 */     if (trustCerPath != null)
/*  26 */       if (X509CertVerifier.verifyCertDate(userX509Cert)) {
/*  27 */         logger.info("userX509Cert date is valid:" + userX509Cert.getNotBefore() + "---" + userX509Cert.getNotAfter());
/*     */       } else {
/*  29 */         logger.warn("Cert out of date:" + userX509Cert.getNotBefore() + "---" + userX509Cert.getNotAfter());
/*  30 */         throw new CFCAException(VerifyEnum.CERT_EXPRIED);
/*     */       }
/*     */   }
/*     */ 
/*     */   public static void verifyCer(X509Cert userX509Cert, String trustCerPath) throws CFCAException
/*     */   {
/*     */     try {
/*  37 */       if (trustCerPath != null) {
/*  38 */         InputStream inputStream = new FileInputStream(trustCerPath);
/*  39 */         X509CertVerifier.updateTrustCertsMap(new X509Cert(inputStream));
/*  40 */         if (X509CertVerifier.validateCertSign(userX509Cert)) {
/*  41 */           logger.info("userX509Cert is right!");
/*     */         } else {
/*  43 */           logger.warn("userX509Cert is wrong!");
/*  44 */           throw new CFCAException(VerifyEnum.CERT_ILLEGAL);
/*     */         }
/*     */       }
/*     */     } catch (PKIException e) {
/*  48 */       logger.error("verifyCer error:", e);
/*  49 */       throw new CFCAException(VerifyEnum.FAILED);
/*     */     } catch (FileNotFoundException e) {
/*  51 */       logger.error("verifyCer FileNotFoundException error:", e);
/*  52 */       throw new CFCAException(VerifyEnum.FAILED);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void verifyCRL(X509Cert userX509Cert, String crlFilePath) throws CFCAException {
/*     */     try {
/*  58 */       if (crlFilePath != null)
/*  59 */         if (X509CertVerifier.verifyCertByCRLOutLine(userX509Cert, crlFilePath)) {
/*  60 */           logger.info("userX509Cert is valid in crl");
/*     */         } else {
/*  62 */           logger.warn("userX509Cert is revoked in crl");
/*  63 */           throw new CFCAException(VerifyEnum.CERT_REVOKED);
/*     */         }
/*     */     }
/*     */     catch (PKIException e) {
/*  67 */       logger.error("verifyCRL error:", e);
/*  68 */       throw new CFCAException(VerifyEnum.FAILED);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void verifyMer(Signature sigUtil, String merId, byte[] bytes) throws CFCAException
/*     */   {
/*     */     try {
/*  75 */       String subject = sigUtil.getSignerX509CertFromP7SignData(bytes).getSubject();
/*     */ 
/*  77 */       if (StringUtils.isBlank(merId)) {
/*  78 */         logger.warn("merId is blank. merId=" + merId);
/*  79 */         throw new CFCAException(VerifyEnum.MER_FAILED);
/*     */       }
/*  81 */       if (!subject.contains(merId)) {
/*  82 */         logger.warn("subject not contains merId. subject=" + subject + ",merId=" + merId);
/*  83 */         throw new CFCAException(VerifyEnum.MER_FAILED);
/*     */       }
/*     */     }
/*     */     catch (PKIException e) {
/*  87 */       logger.error("verifyMer error:", e);
/*  88 */       throw new CFCAException(VerifyEnum.FAILED);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static VerifyResult verifyP7VerifyMessageAttach(Signature sigUtil, byte[] bytes, Session session) throws CFCAException
/*     */   {
/*     */     try {
/*  95 */       boolean isSign = sigUtil.p7VerifyMessageAttach(bytes, session);
/*  96 */       if (!isSign) {
/*  97 */         throw new CFCAException(VerifyEnum.SIGN_ERROR);
/*     */       }
/*  99 */       logger.info("p7 dig alg with verification: " + sigUtil.getDigestAlgorithm());
/* 100 */       logger.info("p7 cert subject with verification:" + sigUtil.getSignerCert().getSubject());
/* 101 */       logger.info("p7 signature: " + new String(Base64.encodeBase64(sigUtil.getSignature())));
/* 102 */       logger.info("RSA P7 attach verify OK!");
/* 103 */       VerifyResult verifyResult = new VerifyResult(VerifyEnum.SUCCESS);
/* 104 */       verifyResult.setContent(sigUtil.getSourceData());
/* 105 */       return verifyResult;
/*     */     }
/*     */     catch (PKIException e) {
/* 108 */       logger.error("verifyP7VerifyMessageAttach error:", e);
/* 109 */     }throw new CFCAException(VerifyEnum.FAILED);
/*     */   }
/*     */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.CFCAVerify
 * JD-Core Version:    0.6.2
 */