package ipcalculator;
public class IpCidr
{
	 private int ipCidr;
	 
	public IpCidr()
	{
		super();
	}
	public IpCidr(int o1,int o2,int o3,int o4)
	{
		super();
		o1=o1&0xff;
		ipCidr=ipCidr|(o1<<24);
		o2=o2&0xff;
		ipCidr=ipCidr|(o2<<16);
		o3=o3&0xff;
		ipCidr=ipCidr|(o3<<8);
		o4=o4&0xff;
		ipCidr=ipCidr|o4;
		
		
	}
	public IpCidr(int ipCidr)
	{
		super();
		this.ipCidr=ipCidr;
	}
	public int getIpCidr(){
		return ipCidr;
	}
	@Override
	public String toString(){
		return ((ipCidr&0xff000000)>>>24)+"."+((ipCidr&0x00ff0000)>>>16)+"."+((ipCidr&0x0000ff00)>>>8)+"."+(ipCidr&0x000000ff);
	}
}