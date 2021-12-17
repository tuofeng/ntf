/*    */ package com.bill.huifu.cfca;
/*    */ 
/*    */

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */
/*    */

/*    */
/*    */ public class CRLDownload
/*    */ {
/* 19 */   private Logger logger = LoggerFactory.getLogger(CFCASignature.class);
/*    */ 
/* 21 */   private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
/*    */ 
/* 23 */   private String crlURL = "http://crl.cfca.com.cn/RSA/allCRL.crl";
/*    */   private String downloadPath;
/* 27 */   private int scheduleInitDelayTime = 10;
/* 28 */   private int schedulePeriodTime = 7200;
/*    */ 
/*    */   public void start() {
/* 31 */     if (StringUtils.isBlank(this.downloadPath)) {
/* 32 */       this.logger.warn("downloadPath is blank. start cancel.");
/* 33 */       stop();
/* 34 */       return;
/*    */     }
/* 36 */     this.logger.info("start to download crl file. crlUrl={},downloadPaht={}", this.crlURL, this.downloadPath);
/* 37 */     Thread thread = new Thread(new Runnable()
/*    */     {
/*    */       public void run() {
/*    */         try {
/* 41 */           URL httpurl = new URL(CRLDownload.this.crlURL);
/* 42 */           File dirs = new File(CRLDownload.this.downloadPath);
/* 43 */           dirs.mkdirs();
/*    */ 
/* 45 */           File f = new File(CRLDownload.this.downloadPath + File.separator + "crl.crl");
/* 46 */           FileUtils.copyURLToFile(httpurl, f);
/* 47 */           CRLDownload.this.logger.info("download crl file success.");
/*    */         } catch (Exception e) {
/* 49 */           CRLDownload.this.logger.error("download crlURL error.", e);
/*    */         }
/* 51 */         CRLDownload.this.logger.info("end to download crl file.");
/*    */       }
/*    */     });
/* 54 */     this.executorService.scheduleAtFixedRate(thread, this.scheduleInitDelayTime, this.schedulePeriodTime, TimeUnit.SECONDS);
/*    */   }
/*    */ 
/*    */   public void stop() {
/* 58 */     if (this.executorService != null)
/* 59 */       this.executorService.shutdown();
/*    */   }
/*    */ 
/*    */   public String getDownloadPath()
/*    */   {
/* 64 */     return this.downloadPath;
/*    */   }
/*    */ 
/*    */   public void setDownloadPath(String downloadPath) {
/* 68 */     this.downloadPath = downloadPath;
/*    */   }
/*    */ 
/*    */   public String getCrlURL() {
/* 72 */     return this.crlURL;
/*    */   }
/*    */ 
/*    */   public void setCrlURL(String crlURL) {
/* 76 */     this.crlURL = crlURL;
/*    */   }
/*    */ 
/*    */   public int getScheduleInitDelayTime() {
/* 80 */     return this.scheduleInitDelayTime;
/*    */   }
/*    */ 
/*    */   public void setScheduleInitDelayTime(int scheduleInitDelayTime) {
/* 84 */     this.scheduleInitDelayTime = scheduleInitDelayTime;
/*    */   }
/*    */ 
/*    */   public int getSchedulePeriodTime() {
/* 88 */     return this.schedulePeriodTime;
/*    */   }
/*    */ 
/*    */   public void setSchedulePeriodTime(int schedulePeriodTime) {
/* 92 */     this.schedulePeriodTime = schedulePeriodTime;
/*    */   }
/*    */ }

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     CRLDownload
 * JD-Core Version:    0.6.2
 */