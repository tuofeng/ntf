/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */ import java.util.Arrays;

/*    */
/*    */ public class CryptResult
/*    */ {
/*    */   private String code;
/*    */   private String message;
/*    */   private byte[] content;
/*    */ 
/*    */   public CryptResult()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CryptResult(String code, String message)
/*    */   {
/* 18 */     this.code = code;
/* 19 */     this.message = message;
/*    */   }
/*    */ 
/*    */   public CryptResult(CryptEnum cryptEnum) {
/* 23 */     setCode(cryptEnum.getCode());
/* 24 */     setMessage(cryptEnum.getMessage());
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
/* 53 */     StringBuilder sb = new StringBuilder("CryptResult{");
/* 54 */     sb.append("code='").append(this.code).append('\'');
/* 55 */     sb.append(", message='").append(this.message).append('\'');
/* 56 */     sb.append(", content=").append(Arrays.toString(this.content));
/* 57 */     sb.append('}');
/* 58 */     return sb.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.CryptResult
 * JD-Core Version:    0.6.2
 */