package ipcalculator;
public class IpCalculator
{
	private IpCidr ip;
	private IpCidr mask;
	private IpCidr netWork;
	private IpCidr broadcast;
	private IpCidr ini;
	private IpCidr last;
	private int bitsOfNetWork;
	public boolean ERROR;
	public IpCalculator(IpCidr ip,int bits)
	{
		super();
		this.ip=ip;

			this.bitsOfNetWork=bits;
		defineBits();
		calculateMask();
		calculateNetWork();
		calculateBroadcast();
		calculateIniLast();
	}

	public IpCalculator(String ip){
		super();
		int[] ipx=destroyString(ip);
			if(ipx!=null){
				
				this.ip=new IpCidr(ipx[0],ipx[1],ipx[2],ipx[3]);
				if(ipx[0]>255 ||ipx[1]>255||ipx[2]>255 ||ipx[3]>255  ){this.ERROR=true;}
				if(ipx[0]==0 &&ipx[1]==0&&ipx[2]==0&&ipx[3]==0  ){this.ERROR=true;}
				if(ipx[0]==255 &&ipx[1]==255&&ipx[2]==255&&ipx[3]==255  ){this.ERROR=true;}
					this.bitsOfNetWork=ipx[4];
				defineBits();
				calculateMask();
				calculateNetWork();
				calculateBroadcast();
				calculateIniLast();
				
			}else{
				this.ERROR=true;
				}
	}
	
	private int[] destroyString(String ip){
		int[] params=new int[5];
		String aux="";int k=0/*contador de indice*/,c=0/*contador de punto*/,c2=0/*contador de /*/;
		for(int i=0;i<ip.length();i++){
			if(ip.charAt(i)=='.'){c++;}
			if(ip.charAt(i)=='/'){c2++;}
		}	
			if(c!=3|| c2!=1){return null;}
			c=0;c2=0;
		if(ip.charAt(0)>='0' || ip.charAt(0)<='9'){
			for(int i=0;i<ip.length();i++){
				if(ip.charAt(i)=='.'){
					try{
						params[k]=Integer.parseInt(aux);
						}catch(Exception e)
						{
							return null;
						}
					
					aux="";k++;c++;
					}
				if(ip.charAt(i)=='/'){
					try{
						params[k]=Integer.parseInt(aux);
						}catch(Exception e)
						{
							return null;
						}
						aux="";c2++;k++;
					for(int j=i+1;j<ip.length();j++){
						if(ip.charAt(j)>='0'&&ip.charAt(j)<='9'){aux=aux+ip.charAt(j);}else{return null;}
						}
						try{
						params[k]=Integer.parseInt(aux);
						}catch(Exception e)
						{
							return null;
						}
						break;
					}
				if(ip.charAt(i)>='0'&&ip.charAt(i)<='9'){
					aux=aux+ip.charAt(i);
					
				}
				if((ip.charAt(i)<'0' || ip.charAt(i)>'9')&& ip.charAt(i)!='.' && ip.charAt(i)!='/'){return null;}	
				if(c>3 || c2>1){break;}
				if( k>=params.length){break;}
			}	
		}else{
			return null;
		}
		return params;
	}
	private void defineBits(){
		int o1=((ip.getIpCidr())&0xff000000)>>>24;
		if(bitsOfNetWork<0 && bitsOfNetWork>31){this.ERROR=true;}
		if(bitsOfNetWork==0){
			//Class A
			if(o1>=0 && o1<=127){bitsOfNetWork=8;}
			//Class B
			if(o1>=128 && o1<=191){bitsOfNetWork=16;}
			//Class C or other
			if(o1>=192 ){bitsOfNetWork=24;}
		}
	}
	public IpCidr getIp(){
		return ip;
	}
	private void calculateMask(){
		int mask0=0;
		for(int i=0;i<bitsOfNetWork;i++){
			mask0=mask0|0x01;
			mask0=mask0<<1;
		}
		mask0=mask0>>>1;
		mask0=mask0<<32-bitsOfNetWork;
		mask=new IpCidr(mask0);
	}
	private void calculateNetWork(){
		netWork=new IpCidr(mask.getIpCidr()&ip.getIpCidr());
	}
	private void calculateBroadcast(){
		int host0=0;
		for(int i=0;i<32-bitsOfNetWork;i++){
			host0=host0|0x01;
			host0=host0<<1;
		}
		host0=host0>>>1;
		host0=host0|netWork.getIpCidr();
		broadcast=new IpCidr(host0);
	}
	private void calculateIniLast(){
		int i=netWork.getIpCidr()|0x01;
		ini=new IpCidr(i);
		int l=broadcast.getIpCidr()&0xff;
		l=(l>>>1)<<1;
		l=(broadcast.getIpCidr()&0xffffff00)|l;
		last=new IpCidr(l);
		
	}
	@Override
	public String toString(){
	return "Ip Address:\t"+ip.toString()+"/"+bitsOfNetWork+"\nMask:\t\t"+mask.toString()+"\nNetWork:\t"+netWork.toString()+"/"+bitsOfNetWork+"\nBroadcast:\t"+broadcast.toString()+"\nIp Inicial:\t"+ini.toString()+"/"+bitsOfNetWork+"\nIp Final:\t"+last.toString()+"/"+bitsOfNetWork;                 
	}
}