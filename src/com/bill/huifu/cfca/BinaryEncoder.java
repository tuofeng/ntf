package com.bill.huifu.cfca;

public abstract interface BinaryEncoder extends Encoder
{
  public abstract byte[] encode(byte[] paramArrayOfByte)
    throws EncoderException;
}

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.BinaryEncoder
 * JD-Core Version:    0.6.2
 */