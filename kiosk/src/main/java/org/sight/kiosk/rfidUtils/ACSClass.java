package org.sight.kiosk.rfidUtils;

public class ACSClass {
	//private PcscReader pcscReader = new PcscReader();
	
	private PcscReader _pcscReader;
	
	public PcscReader getPcscConnection() {return this._pcscReader;}
	public void setPcscConnection(PcscReader pcscConnection) {this._pcscReader = pcscConnection;}
	
	public ACSClass(PcscReader pcscReader)
	{
		this._pcscReader = pcscReader;
	}
	


}
