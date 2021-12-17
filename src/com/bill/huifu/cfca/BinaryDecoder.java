package com.bill.huifu.cfca;

public abstract interface BinaryDecoder extends Decoder
{
  public abstract byte[] decode(byte[] paramArrayOfByte)
    throws DecoderException;
}

/* Location:           C:\Users\rd_lsf_li\Desktop\开发记录\汇付\java_demo\lib\saturn-cfca-1.0.6.jar
 * Qualified Name:     com.huifu.saturn.cfca.BinaryDecoder
 * JD-Core Version:    0.6.2
 */