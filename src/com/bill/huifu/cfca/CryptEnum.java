/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */ public enum CryptEnum
/*    */ {
/*  7 */   FAILED("999", "系统异常"), 
/*  8 */   SUCCESS("000", "成功");
/*    */ 
/*    */   private String code;
/*    */   private String message;
/*    */ 
/* 14 */   private CryptEnum(String code, String message) { this.code = code;
/* 15 */     this.message = message; }
/*    */ 
/*    */   public String getCode()
/*    */   {
/* 19 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 23 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 27 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message) {
/* 31 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.CryptEnum
 * JD-Core Version:    0.6.2
 */