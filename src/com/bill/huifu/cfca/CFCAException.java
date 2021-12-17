/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */ public class CFCAException extends RuntimeException
/*    */ {
/*    */   private String code;
/*    */   private String message;
/*    */ 
/*    */   public CFCAException(String code, String message)
/*    */   {
/* 12 */     super(message);
/* 13 */     this.code = code;
/* 14 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public CFCAException(VerifyEnum verifyEnum) {
/* 18 */     super(verifyEnum.getMessage());
/* 19 */     this.code = verifyEnum.getCode();
/* 20 */     this.message = verifyEnum.getMessage();
/*    */   }
/*    */ 
/*    */   public String getCode() {
/* 24 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 28 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMessage()
/*    */   {
/* 33 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message) {
/* 37 */     this.message = message;
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.CFCAException
 * JD-Core Version:    0.6.2
 */