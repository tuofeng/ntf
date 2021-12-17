/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */ public class SignResult
/*    */ {
/*    */   private String code;
/*    */   private String message;
/*    */   private String sign;
/*    */ 
/*    */   public SignResult(String message, String code, String sign)
/*    */   {
/* 14 */     this.message = message;
/* 15 */     this.code = code;
/* 16 */     this.sign = sign;
/*    */   }
/*    */ 
/*    */   public SignResult(SignEnum signEnum) {
/* 20 */     this.message = signEnum.getMessage();
/* 21 */     this.code = signEnum.getCode();
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
/*    */ 
/*    */   public String getSign() {
/* 41 */     return this.sign;
/*    */   }
/*    */ 
/*    */   public void setSign(String sign) {
/* 45 */     this.sign = sign;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 50 */     return "SignResult{code='" + this.code + '\'' + ", message='" + this.message + '\'' + ", sign='" + this.sign + '\'' + '}';
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.SignResult
 * JD-Core Version:    0.6.2
 */