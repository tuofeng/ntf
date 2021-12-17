/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */ import java.util.Arrays;

/*    */
/*    */ public class VerifyResult
/*    */ {
/*    */   private String code;
/*    */   private String message;
/*    */   private byte[] content;
/*    */ 
/*    */   public VerifyResult()
/*    */   {
/*    */   }
/*    */ 
/*    */   public VerifyResult(String code, String message)
/*    */   {
/* 18 */     this.code = code;
/* 19 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public VerifyResult(VerifyEnum verifyEnum) {
/* 23 */     setCode(verifyEnum.getCode());
/* 24 */     setMessage(verifyEnum.getMessage());
/*    */   }
/*    */ 
/*    */   public String getCode() {
/* 28 */     return this.code;
/*    */   }
/*    */ 
/*    */   public void setCode(String code) {
/* 32 */     this.code = code;
/*    */   }
/*    */ 
/*    */   public String getMessage() {
/* 36 */     return this.message;
/*    */   }
/*    */ 
/*    */   public void setMessage(String message) {
/* 40 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public byte[] getContent() {
/* 44 */     return this.content;
/*    */   }
/*    */ 
/*    */   public void setContent(byte[] content) {
/* 48 */     this.content = content;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 53 */     return "VerifyResult{code='" + this.code + '\'' + ", message='" + this.message + '\'' + ", content=" + 
/* 56 */       Arrays.toString(this.content) +
/* 56 */       '}';
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.VerifyResult
 * JD-Core Version:    0.6.2
 */