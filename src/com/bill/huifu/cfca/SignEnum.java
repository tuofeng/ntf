/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */ public enum SignEnum
/*    */ {
/*  7 */   FAILED("999", "系统异常"), 
/*  8 */   SUCCESS("000", "签名成功");
/*    */ 
/*    */   private String code;
/*    */   private String message;
/*    */ 
/*    */   private SignEnum(String code, String message) {
/* 15 */     this.code = code;
/* 16 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public String getCode() {
/* 20 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 24 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 28 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message) {
/* 32 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.SignEnum
 * JD-Core Version:    0.6.2
 */