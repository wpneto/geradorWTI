package util;

import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

public class GeradorHexaString 
{
	public String converterString(String hexa) 
	{
		byte[] s = DatatypeConverter.parseHexBinary(hexa);
	    return new String(s);
	}
	
	public String converterHexa(String str) 
	{
		byte[] s = str.getBytes(StandardCharsets.UTF_8);
	    return DatatypeConverter.printHexBinary(s);
	}
}
