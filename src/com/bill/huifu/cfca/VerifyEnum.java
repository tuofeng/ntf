/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */ public enum VerifyEnum
/*    */ {
/*  7 */   FAILED("999", "系统异常"), 
/*  8 */   SUCCESS("000", "验签成功"), 
/*  9 */   CERT_EXPRIED("001", "证书过期"), 
/* 10 */   CERT_ILLEGAL("002", "证书非法，不符合X509规范"), 
/* 11 */   MER_FAILED("003", "当前证书不属于颁发者"), 
/* 12 */   SIGN_ERROR("004", "签名无效"), 
/* 13 */   CERT_REVOKED("005", "证书被吊销");
/*    */ 
/*    */   private String code;
/*    */   private String message;
/*    */ 
/*    */   private VerifyEnum(String code, String message) {
/* 20 */     this.code = code;
/* 21 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public String getCode() {
/* 25 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 29 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 33 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message) {
/* 37 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.VerifyEnum
 * JD-Core Version:    0.6.2
 */